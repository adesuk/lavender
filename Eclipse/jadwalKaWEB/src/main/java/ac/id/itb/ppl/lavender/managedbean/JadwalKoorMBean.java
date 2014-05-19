package ac.id.itb.ppl.lavender.managedbean;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;

import ac.id.itb.ppl.lavender.bean.JadwalBean;
import ac.id.itb.ppl.lavender.bean.PeriodeBean;
import ac.id.itb.ppl.lavender.formatter.PeriodeFormat;
import ac.id.itb.ppl.lavender.model.Jadwal;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.view.JadwalView;
import ac.id.itb.ppl.lavender.util.StatusJadwal;
import ac.id.itb.ppl.lavender.util.TipeEksekusi;

@ManagedBean(name="jadwalKoorMBean")
@ViewScoped
public class JadwalKoorMBean implements Serializable {
	
	private static final long serialVersionUID = -4836165624248144491L;
	@EJB
	private JadwalBean jadwalBean;
	@EJB
	private PeriodeBean periodeBean;
	
	private Periode periode;
	private TipeEksekusi tipeEksekusi;
	private List<Periode> periodes;
	private List<JadwalView> jadwal;
	
	public JadwalKoorMBean() {
		tipeEksekusi = new TipeEksekusi();
		statusJadwal = new StatusJadwal();
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
        }
        return periode;
    }
    
    public void setPeriode(Periode periode) {
//    	System.out.println("setSelectedPeriode");
        this.periode = periode;
    }

	public List<JadwalView> getJadwalsKoor() {
		return jadwal;
	}

	public void setJadwalsKoor(List<JadwalView> jadwal) {
		this.jadwal = jadwal;
	} 
   
	// Ubah Jadwal
	
	private Integer statusTerlaksana;
	private Integer terlaksana;
	private Integer hasil;
	private StatusJadwal statusJadwal;
	private List<SelectItem> statusTerlaksanaList;
	private List<SelectItem> terlaksanaList;
	private List<SelectItem> hasilList;
	private JadwalView jadwalView;
	
	public void cariStatusJadwal(AjaxBehaviorEvent e) {
		reloadPelaksanaanJadwal();
	}
	
	public void editListener(JadwalView jadwalView) {
		this.jadwalView = jadwalView;
	}
	
	public void reloadPelaksanaanJadwal() {
		jadwal = jadwalBean.findJadwalByPeriodeAndPelaksanaan(getPeriode().getIdPeriode(), statusTerlaksana);
	}
	
	public String valueTerlaksana(Integer status) {
		return statusJadwal.getStatusTerlaksana(status);
	}
	
	public String valueHasil(Integer status) {
		return statusJadwal.getStatusLulus(status);
	}
	
	public void ubahListener() {
//		System.out.println("Tes");
//		System.out.println("Terlasakana : "+ statusJadwal.getStatusTerlaksana(jadwalView.getStatusPelaksanaan()));
//		System.out.println("HAsil : "+ statusJadwal.getStatusLulus(jadwalView.getStatusHasilPelaksanaan()));
		
		Jadwal jd = jadwalBean.find(jadwalView.getIdJadwal());
		jd.setStatusHasilPelaksanaan(new BigInteger(hasil.toString()));
		jd.setStatusPelaksanaan(new BigInteger(terlaksana.toString()));
		
		jadwalBean.edit(jd);
		reloadJadwal();
		
//		System.out.println("Terlasakana : "+ statusJadwal.getStatusTerlaksana(terlaksana));
//		System.out.println("HAsil : "+ statusJadwal.getStatusLulus(hasil));
	}
	
	// getList
	
	public List<SelectItem> getStatusTerlaksanaList() {
		if (statusTerlaksanaList == null || statusTerlaksanaList.isEmpty()) {
			statusTerlaksanaList = new ArrayList<SelectItem>();
			statusTerlaksanaList.add(new SelectItem(new Integer(-1), "-- All --"));
			for(Integer key : statusJadwal.getStatusTerlaksana().keySet()) {
				statusTerlaksanaList.add(new SelectItem(key, statusJadwal.getStatusTerlaksana(key)));
			}
		}
		return statusTerlaksanaList;
	}
	
	public List<SelectItem> getTerlaksanaList() {
		if (terlaksanaList == null || terlaksanaList.isEmpty()) {
			terlaksanaList = new ArrayList<SelectItem>();
			for(Integer key : statusJadwal.getStatusTerlaksana().keySet()) {
				terlaksanaList.add(new SelectItem(key, statusJadwal.getStatusTerlaksana(key)));
			}
		}
		return terlaksanaList;
	}

	public List<SelectItem> getHasilList() {
		if (hasilList == null || hasilList.isEmpty()) {
			hasilList = new ArrayList<SelectItem>();
			for(Integer key : statusJadwal.getStatusLulus().keySet()) {
				hasilList.add(new SelectItem(key, statusJadwal.getStatusLulus(key)));
			}
		}
		return hasilList;
	}
	
	// Setter & Getter
	public Integer getStatusTerlaksana() {
		if (statusTerlaksana == null) {
			statusTerlaksana = -1;
		}
		return statusTerlaksana;
	}

	public void setStatusTerlaksana(Integer statusTerlaksana) {
		this.statusTerlaksana = statusTerlaksana;
	}

	public Integer getTerlaksana() {
		return terlaksana;
	}

	public void setTerlaksana(Integer terlaksana) {
		this.terlaksana = terlaksana;
	}

	public Integer getHasil() {
		return hasil;
	}

	public void setHasil(Integer hasil) {
		this.hasil = hasil;
	}

	public StatusJadwal getStatusJadwal() {
		return statusJadwal;
	}

	public void setStatusJadwal(StatusJadwal statusJadwal) {
		this.statusJadwal = statusJadwal;
	}

	public JadwalView getJadwalView() {
		return jadwalView;
	}

	public void setJadwalView(JadwalView jadwalView) {
		System.out.println(jadwalView.getNama());
		System.out.println("Pelaksanaan : "+ jadwalView.getStatusPelaksanaan());
		this.jadwalView = jadwalView;
		terlaksana = jadwalView.getStatusPelaksanaan();
		hasil = jadwalView.getStatusHasilPelaksanaan();
	}

	
}
