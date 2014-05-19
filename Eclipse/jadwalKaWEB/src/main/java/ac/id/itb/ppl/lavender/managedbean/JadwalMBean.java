package ac.id.itb.ppl.lavender.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ac.id.itb.ppl.lavender.bean.JadwalBean;
import ac.id.itb.ppl.lavender.bean.PeriodeBean;
import ac.id.itb.ppl.lavender.formatter.PeriodeFormat;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.view.JadwalView;
import ac.id.itb.ppl.lavender.util.MyTipeEksekusi;
import ac.id.itb.ppl.lavender.util.TipeEksekusi;

@ManagedBean(name="jadwalMBean")
@ViewScoped
public class JadwalMBean implements Serializable {
	
	private static final long serialVersionUID = -4836165624248144491L;
	@EJB
	private JadwalBean jadwalBean;
	@EJB
	private PeriodeBean periodeBean;
	
	private Periode periode;
	private MyTipeEksekusi tipeEksekusi;
	
	@PostConstruct
	public void init() {
		tipeEksekusi = new MyTipeEksekusi();
		periode = periodeBean.lastPeriode();
	}
		
	public String getPeriodeFormat() {
		return "Periode ".concat(PeriodeFormat.format(periode));
	}

	public String getPeriodeType() {
		return "Jadwal ["
				.concat(tipeEksekusi.getTipeEksekusi(
							periode.getTipeJadwal()))
				.concat("]");			
	}	
		
	public List<JadwalView> getJadwals() {	
		return jadwalBean.findJadwalByPeriode(1);//p.getIdPeriode());
	}   
}
