package ac.id.itb.ppl.lavender.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Init;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import ac.id.itb.ppl.lavender.bean.JadwalBean;
import ac.id.itb.ppl.lavender.bean.PeriodeBean;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.view.JadwalView;
import ac.id.itb.ppl.lavender.util.PeriodeFormat;
import ac.id.itb.ppl.lavender.util.TipeEksekusi;

@ManagedBean(name="jadwalMBean")
@ViewScoped
public class JadwalMBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EJB
	private JadwalBean jadwalBean;
	@EJB
	private PeriodeBean periodeBean;
	
	private Periode periode;
	private TipeEksekusi tipeEksekusi;
	
	@PostConstruct
	public void init() {
		periode = periodeBean.lastPeriode();
		tipeEksekusi = new TipeEksekusi();
	}
	
	public List<JadwalView> getJadwals() {	
		return jadwalBean.findJadwalByPeriode(periode.getIdPeriode());
	}	
	
//	public List<JadwalView> getJadwalsByPeriode(long idPeriode) {
//		return jadwalBean.findJadwalByPeriode(idPeriode);
//	}
	
	public String getPeriodeFormat() {
		return "Periode ".concat(PeriodeFormat.format(periode));
	}

	public String getPeriodeType() {
		return "Jadwal ["
				.concat(tipeEksekusi.getTipeEksekusi(
							periode.getTipeJadwal().charAt(0)))
				.concat("]");			
	}
}
