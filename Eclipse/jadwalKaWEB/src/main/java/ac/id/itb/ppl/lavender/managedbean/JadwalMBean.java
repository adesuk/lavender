package ac.id.itb.ppl.lavender.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.AjaxBehaviorEvent;

import ac.id.itb.ppl.lavender.bean.JadwalBean;
import ac.id.itb.ppl.lavender.bean.PeriodeBean;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.view.JadwalView;
import ac.id.itb.ppl.lavender.util.PeriodeFormat;
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
	private TipeEksekusi tipeEksekusi;
	private List<Periode> periodes;
	private List<JadwalView> jadwal;
	
	public JadwalMBean() {
		tipeEksekusi = new TipeEksekusi();
	}
		
	public String getPeriodeFormat() {
		return "Periode ".concat(PeriodeFormat.format(periode));
	}

	public String getPeriodeType() {
		return "Jadwal ["
				.concat(tipeEksekusi.getTipeEksekusi(
							periode.getTipeJadwal().charAt(0)))
				.concat("]");			
	}	
		
	public List<JadwalView> getJadwals() {	
		periode = periodeBean.lastPeriode();
		return jadwalBean.findJadwalByPeriode(1);//p.getIdPeriode());
	}
	
	public void reloadPeriodes() {
		periodes = periodeBean.findAll();
	}
	
	public String formatnya(Periode periode) {
		return PeriodeFormat.format(periode);
	}
	 
	public String getTipeJadwalRealName(char tipeJadwal) {
		return tipeEksekusi.getTipeEksekusi(tipeJadwal);
	}
	    
	public void cariListener(AjaxBehaviorEvent e) {
		reloadJadwal();
	}
	    
	public void reloadJadwal() {
		System.out.println("Periode : "+ getPeriode().getIdPeriode());
		jadwal = jadwalBean.findJadwalByPeriode(getPeriode().getIdPeriode());
	}
	
	 // Getter dan setter
    public List<Periode> getAllPeriodes() {
        if (periodes == null || periodes.isEmpty()) {
            reloadPeriodes();
        }
        return periodes;
    }
	
	public Periode getPeriode() {
        if (periode == null && (periodes != null && !periodes.isEmpty())) {
            periode = periodes.get(0);
            System.out.println("if getSelectedPeriode");
        }
        System.out.println("not if getSelectedPeriode");
        return periode;
    }
    
    public void setPeriode(Periode periode) {
    	System.out.println("setSelectedPeriode");
        this.periode = periode;
    }

	public List<JadwalView> getJadwalsKoor() {
		return jadwal;
	}

	public void setJadwalsKoor(List<JadwalView> jadwal) {
		this.jadwal = jadwal;
	} 
   
}
