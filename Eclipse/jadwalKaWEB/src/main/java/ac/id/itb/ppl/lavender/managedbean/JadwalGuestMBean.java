package ac.id.itb.ppl.lavender.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ac.id.itb.ppl.lavender.model.view.JadwalView;
import ac.id.itb.ppl.lavender.util.TipeEksekusi;

@ManagedBean(name="jadwalMBean")
@ViewScoped
public class JadwalGuestMBean extends JadwalMBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@PostConstruct
	public void init() {
		periode = periodeBean.lastPeriode();
		tipeEksekusi = new TipeEksekusi();
	}
	
	public List<JadwalView> getJadwals() {	
		return jadwalBean.findJadwalByPeriode(periode.getIdPeriode());
	}	

}
