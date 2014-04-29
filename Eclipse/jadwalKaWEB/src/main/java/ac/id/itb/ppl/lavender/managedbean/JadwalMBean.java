package ac.id.itb.ppl.lavender.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import ac.id.itb.ppl.lavender.bean.JadwalBean;
import ac.id.itb.ppl.lavender.model.Jadwal;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.view.JadwalView;

@ManagedBean(name="jadwalMBean")
@RequestScoped
public class JadwalMBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private JadwalBean jadwalBean;
	
	private Periode periode;
	
	public JadwalMBean() {
		periode = new Periode();
		periode.setIdPeriode(1);
	}
	
	public List<JadwalView> getJadwals() {
		return jadwalBean.findJadwalByPeriode(periode.getIdPeriode());
	}
}
