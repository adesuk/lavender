package ac.id.itb.ppl.lavender.managedbean.jadwal;

import ac.id.itb.ppl.lavender.dao.JadwalDao;
import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.model.Jadwal;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.SlotWaktu;
import ac.id.itb.ppl.lavender.formatter.PeriodeFormat;
import ac.id.itb.ppl.lavender.formatter.SlotWaktuFormat;
import ac.id.itb.ppl.lavender.util.TipeEksekusi;
import ac.id.itb.ppl.lavender.formatter.VersiFormat;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Edbert
 */
@Named("pengelolaanJadwal")
@SessionScoped
public class PengelolaanJadwalMBean implements Serializable {
    private static final long serialVersionUID = -9023423845123682L;
    
    @Inject private PeriodeDao periodeDao;
    @Inject private JadwalDao jadwalDao;
    private List<Periode> periodes;
    private Periode periode;
    private List<Jadwal> jadwal;
    private Jadwal jadwalDetail;
    private List<Date> jadwalVersions;
    private Date selectedJadwalVersion;
    
    //<editor-fold defaultstate="collapsed" desc="Getter dan setter">
    public List<Periode> getPeriodes() {
        if (periodes == null || periodes.isEmpty()) {
            reloadPeriodes();
        }
        return periodes;
    }
    
    public Periode getSelectedPeriode() {
        return periode;
    }
    
    public void setSelectedPeriode(Periode periode) {
        this.periode = periode;
    }
    
    public List<Jadwal> getJadwal() {
        return jadwal;
    }
    
    public Jadwal getJadwalDetail() {
        return jadwalDetail;
    }
    
    public void setJadwalDetail(Jadwal jadwalDetail) {
        this.jadwalDetail = jadwalDetail;
    }
    
    public List<Date> getJadwalVersions() {
        return jadwalVersions;
    }
    
    public Date getSelectedJadwalVersion() {
        return selectedJadwalVersion;
    }
    
    public void setSelectedJadwalVersion(Date selectedJadwalVersion) {
        this.selectedJadwalVersion = selectedJadwalVersion;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Business logic">
    public void periodeChanged(ValueChangeEvent e) {
        periode = (Periode) e.getNewValue();
    }
    
    public void reloadPeriodes() {
        periodes = periodeDao.findAll();
    }
    
    public void reloadJadwal() {
        if (getSelectedPeriode() != null) {
            jadwal = jadwalDao.findJadwalByPeriodeAndVersi(getSelectedPeriode(), getSelectedJadwalVersion());
        }
    }
    
    public void reloadJadwalVersions() {
        if (getSelectedPeriode() != null) {
            jadwalVersions = jadwalDao.findJadwalVersions(getSelectedPeriode());
            if (jadwalVersions != null && !jadwalVersions.isEmpty()) {
                selectedJadwalVersion = jadwalVersions.get(0);
            }
        } else {
            jadwalVersions = new ArrayList<Date>(0);
        }
    }
    
    public String getTipeJadwalRealName(char tipeJadwal) {
        Map<String, Character> temp = new TipeEksekusi().getTipeEksekusis();
        for (String s : temp.keySet()) {
            if (temp.get(s) == tipeJadwal) {
                return s;
            }
        }
        return null;
    }
    
    public void createJadwal() {
        if (periode == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Periode belum dipilih!"));
            return;
        }
        
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("TambahJadwal.xhtml?idPeriode=" + periode.getIdPeriode());
        } catch (IOException ioe) {
            ioe.printStackTrace(System.out);
        }
    }
    
    public String editJadwal() {
        return "UbahJadwal";
    }
    
    public String formatPeriode(Periode periode) {
        return PeriodeFormat.format(periode);
    }
    
    public String formatSlotWaktu(SlotWaktu slot) {
        return SlotWaktuFormat.format(slot);
    }
    
    public String formatVersiJadwal(Date versi) {
        if (versi == null) {
            return "";
        } else {
            return VersiFormat.format(versi);
        }
    }
    
//    public String saveJadwal() {
//        Mahasiswa m = new Mahasiswa(typedNim);
//        KaryaAkhir ka = karyaAkhirDao.findByOwner(m);
//        jadwalDetail.setKaryaAkhir(ka);
//        jadwalDetail.setIdPeriode(getSelectedPeriode());
//        
//        if (getSelectedJadwalVersion() == null) {
//            setSelectedJadwalVersion(
//                new Date(System.currentTimeMillis())
//            );
//        }
//        jadwalDetail.setGenerateDate(getSelectedJadwalVersion());
//        jadwalDao.save(jadwalDetail);
//        
//        reloadJadwalVersions();
//        reloadJadwal();
//        
//        return "PengelolaanJadwal";
//    }
    
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Ajax">
    public void periodeListener(AjaxBehaviorEvent e) {
        reloadJadwalVersions();
    }
    
    public void cariListener(ActionEvent e) {
        if (periode == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Periode belum dipilih!"));
        } else {
            reloadJadwal();
        }
    }
    
    public void onRowSelect(SelectEvent e) {
        this.jadwalDetail = ((Jadwal) e.getObject());
    }
    //</editor-fold>
}
