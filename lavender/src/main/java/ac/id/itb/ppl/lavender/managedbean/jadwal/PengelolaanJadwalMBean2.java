package ac.id.itb.ppl.lavender.managedbean.jadwal;

import ac.id.itb.ppl.lavender.dao.DosenDao;
import ac.id.itb.ppl.lavender.dao.JadwalDao;
import ac.id.itb.ppl.lavender.dao.KaryaAkhirDao;
import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.dao.RuanganDao;
import ac.id.itb.ppl.lavender.dao.SlotWaktuDao;
import ac.id.itb.ppl.lavender.model.Jadwal;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.Mahasiswa;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.SlotWaktu;
import ac.id.itb.ppl.lavender.formatter.PeriodeFormat;
import ac.id.itb.ppl.lavender.formatter.SlotWaktuFormat;
import ac.id.itb.ppl.lavender.util.TipeEksekusi;
import ac.id.itb.ppl.lavender.formatter.VersiFormat;
import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.Ruangan;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@Named("pengelolaanJadwal2")
@SessionScoped
public class PengelolaanJadwalMBean2 implements Serializable {
    private static final long serialVersionUID = -9023423845123682L;
    private static final Logger LOGGER = Logger.getLogger(PengelolaanJadwalMBean2.class.getName());
    
    @Inject private PeriodeDao periodeDao;
    @Inject private JadwalDao jadwalDao;
    private List<Periode> periodes;
    private Periode periode;
    private List<Jadwal> jadwal;
    private List<Date> jadwalVersions;
    private Date selectedJadwalVersion;
    // tambah jadwal
    @Inject private KaryaAkhirDao karyaAkhirDao;
    @Inject private DosenDao dosenDao;
    @Inject private RuanganDao ruanganDao;
    @Inject private SlotWaktuDao slotWaktuDao;
    private List<Mahasiswa> mahasiswas;
    private Mahasiswa selectedMahasiswa;
    private Jadwal jadwalDetail;
    private List<Dosen> relatedDosenPengujis;
    
    //<editor-fold defaultstate="collapsed" desc="Bagian read jadwal">
    public void initializeJadwal() {
        System.out.println(">>> masuk init jadwal <<<");
        
        jadwalDetail = new Jadwal();
        jadwalDetail.setIdPeriode(getSelectedPeriode());
        //jadwalDetail.setRuangan(new Ruangan());
        List<Dosen> pengujis = new ArrayList<Dosen>(2);
        pengujis.add(null);
        pengujis.add(null);
        jadwalDetail.setDosenPenguji(pengujis);
        reloadMahasiswas();
    }
    
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
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Bagian tambah jadwal">
    public void reloadMahasiswas() {
        System.out.println(">>> reload mahasiswas kepanggil <<<");
        if (getSelectedPeriode() != null) {
            mahasiswas = karyaAkhirDao.getAllMahasiswaYangIkutDiSelectedPeriode(
                getSelectedPeriode().getTipeJadwal());
        }
    }
    
    public void reloadRelatedDosenPengujis() {
        relatedDosenPengujis = dosenDao.findDosenPengujisByMinatTopik(
            jadwalDetail.getKaryaAkhir().getTopik());
    }
    
    public void createJadwal() {
        if (periode == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Periode belum dipilih!"));
            return;
        }
        
        initializeJadwal();
        
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("TambahJadwal.xhtml");
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, null, ioe);
        }
    }
    
    public String saveJadwal() {
        System.out.println(">>> masuk save jadwal 1 <<<");
        //System.out.println(">>> nama mhs " + selectedMahasiswa.getNamaMhs());
        System.out.println(">>> jadwal " + jadwalDetail.getRuangan().getKdRuangan());
        
        KaryaAkhir ka = karyaAkhirDao.findByOwner(selectedMahasiswa);
        jadwalDetail.setKaryaAkhir(ka);
        jadwalDetail.setIdPeriode(getSelectedPeriode());
        
        if (getSelectedJadwalVersion() == null) {
            setSelectedJadwalVersion(
                new Date(System.currentTimeMillis())
            );
        }
        jadwalDetail.setGenerateDate(getSelectedJadwalVersion());
        jadwalDao.save(jadwalDetail);
        
        reloadJadwalVersions();
        reloadJadwal();
        
        return "PengelolaanJadwal?faces-redirect=true";
    }
    
    public void cancelPerubahanJadwal() {
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("PengelolaanJadwal.xhtml");
        } catch (IOException ioe) {
            ioe.printStackTrace(System.out);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Keperluan ajax">
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
    
    public void selectNimListener(AjaxBehaviorEvent e) {
        KaryaAkhir karya = karyaAkhirDao.findByOwner(
            getSelectedMahasiswa()
        );
        jadwalDetail.setKaryaAkhir(karya);
        reloadRelatedDosenPengujis();
    }
    
    public void onRowSelect(SelectEvent e) {
        this.jadwalDetail = ((Jadwal) e.getObject());
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getter dan setter read jadwal">
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
    
    //<editor-fold defaultstate="collapsed" desc="Getter dan setter tambah jadwal">
    public Jadwal getJadwalDetail() {
        return jadwalDetail;
    }
    
    public void setJadwalDetail(Jadwal jadwalDetail) {
        this.jadwalDetail = jadwalDetail;
    }
    
    public List<Mahasiswa> getMahasiswas() {
        return mahasiswas;
    }
    
    public Mahasiswa getSelectedMahasiswa() {
        return selectedMahasiswa;
    }
    
    public void setSelectedMahasiswa(Mahasiswa selectedMahasiswa) {
        this.selectedMahasiswa = selectedMahasiswa;
    }
    
    public List<Dosen> getRelatedDosenPengujis() {
        return relatedDosenPengujis;
    }
    
    public List<Ruangan> getRuangans() {
        return ruanganDao.findAll();
    }
    
    public List<SlotWaktu> getSlotWaktus() {
        return slotWaktuDao.findAll();
    }
    //</editor-fold>
}
