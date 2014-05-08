package ac.id.itb.ppl.lavender.managedbean.jadwal;

import ac.id.itb.ppl.lavender.dao.DosenDao;
import ac.id.itb.ppl.lavender.dao.KaryaAkhirDao;
import ac.id.itb.ppl.lavender.dao.MahasiswaDao;
import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.dao.RuanganDao;
import ac.id.itb.ppl.lavender.dao.SlotWaktuDao;
import ac.id.itb.ppl.lavender.formatter.PeriodeFormat;
import ac.id.itb.ppl.lavender.formatter.SlotWaktuFormat;
import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.Jadwal;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.Mahasiswa;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.Ruangan;
import ac.id.itb.ppl.lavender.model.SlotWaktu;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author edbert
 */
@Named("tambahJadwal")
@SessionScoped
public class TambahJadwalMBean implements Serializable {
    private static final long serialVersionUID = -9123219421583234L;
    
    @Inject private PeriodeDao periodeDao;
    @Inject private KaryaAkhirDao karyaAkhirDao;
    @Inject private MahasiswaDao mahasiswaDao;
    @Inject private RuanganDao ruanganDao;
    @Inject private DosenDao dosenDao;
    @Inject private SlotWaktuDao slotWaktuDao;
    private Periode selectedPeriode;
    private Mahasiswa selectedMahasiswa;
    private Jadwal jadwalDetail;
    private List<Mahasiswa> mahasiswas;
    private String idPeriode;
    private List<Dosen> relatedDosenPengujis;
    
    //<editor-fold defaultstate="collapsed" desc="Business logic">
    public void init() {
        System.out.println("masuk init jadwal");
        jadwalDetail = new Jadwal();
        jadwalDetail.setIdPeriode(getSelectedPeriode());
        jadwalDetail.setRuangan(new Ruangan());
        List<Dosen> pengujis = new ArrayList<Dosen>(2);
        pengujis.add(null);
        pengujis.add(null);
        jadwalDetail.setDosenPenguji(pengujis);
        selectedPeriode = periodeDao.find(Integer.parseInt(idPeriode));
        reloadMahasiswas();
    }
    
    public void reloadMahasiswas() {
        if (getSelectedPeriode() != null) {
            mahasiswas = karyaAkhirDao.getAllMahasiswaYangIkutDiSelectedPeriode(
                getSelectedPeriode().getTipeJadwal());
        }
    }
    
    public void reloadRelatedDosenPengujis() {
        relatedDosenPengujis = dosenDao.findDosenPengujisByMinatTopik(
            jadwalDetail.getKaryaAkhir().getTopik());
    }
    
    public String formatPeriode(Periode periode) {
        return PeriodeFormat.format(periode);
    }
    
    public String formatSlotWaktu(SlotWaktu slot) {
        return SlotWaktuFormat.format(slot);
    }
    
    public void saveJadwal() {
        System.out.println(">>> Nyimpen jadwal, gan! <<<");
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
    
    //<editor-fold defaultstate="collapsed" desc="Getter dan setter">
    public String getIdPeriode() {
        return idPeriode;
    }
    
    public void setIdPeriode(String idPeriode) {
        this.idPeriode = idPeriode;
    }
    
    public Periode getSelectedPeriode() {
        return selectedPeriode;
    }
    
    public void setSelectedPeriode(Periode selectedPeriode) {
        this.selectedPeriode = selectedPeriode;
    }
    
    public Mahasiswa getSelectedMahasiswa() {
        return selectedMahasiswa;
    }
    
    public void setSelectedMahasiswa(Mahasiswa selectedMahasiswa) {
        this.selectedMahasiswa = selectedMahasiswa;
    }
    
    public Jadwal getJadwalDetail() {
        return jadwalDetail;
    }
    
    public List<Mahasiswa> getMahasiswas() {
        return mahasiswas;
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
    
    
    
    //<editor-fold defaultstate="" desc="Ajax">
    public void selectNimListener(AjaxBehaviorEvent e) {
        KaryaAkhir karya = karyaAkhirDao.findByOwner(
            getSelectedMahasiswa()
        );
        jadwalDetail.setKaryaAkhir(karya);
        reloadRelatedDosenPengujis();
    }
    //</editor-fold>
}
