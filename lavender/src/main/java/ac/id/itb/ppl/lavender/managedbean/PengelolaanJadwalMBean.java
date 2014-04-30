package ac.id.itb.ppl.lavender.managedbean;

import ac.id.itb.ppl.lavender.dao.jpa.JadwalDaoImpl;
import ac.id.itb.ppl.lavender.dao.jpa.KaryaAkhirDaoImpl;
import ac.id.itb.ppl.lavender.dao.jpa.PeriodeDaoImpl;
import ac.id.itb.ppl.lavender.dao.jpa.RuanganDaoImpl;
import ac.id.itb.ppl.lavender.dao.jpa.SlotWaktuDaoImpl;
import ac.id.itb.ppl.lavender.model.*;
import ac.id.itb.ppl.lavender.util.PeriodeFormat;
import ac.id.itb.ppl.lavender.util.SlotWaktuFormat;
import ac.id.itb.ppl.lavender.util.TipeEksekusi;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 *
 * @author Edbert
 */
@Named(value = "pengelolaanJadwal")
@SessionScoped
public class PengelolaanJadwalMBean implements java.io.Serializable {
    private static final long serialVersionUID = -91232124123L;
    
    @EJB private PeriodeDaoImpl periodeDao;
    @EJB private JadwalDaoImpl jadwalDao;
    @EJB private RuanganDaoImpl ruanganDao;
    @EJB private KaryaAkhirDaoImpl karyaAkhirDao;
    @EJB private SlotWaktuDaoImpl slotWaktuDao;
    private List<Periode> periodes;
    private Periode periode;
    private List<Jadwal> jadwal;
    private Jadwal selectedJadwal;
    private List<Ruangan> ruangans;
    private Jadwal newJadwal;
    private String typedNim;
    //private List<SlotWaktu> slotWaktus;
    
    public PengelolaanJadwalMBean() {
        initializeJadwal();
    }
    
    // Getter dan setter
    public List<Periode> getPeriodes() {
        if (periodes == null || periodes.isEmpty()) {
            reloadPeriodes();
        }
        return periodes;
    }
    
    public Periode getSelectedPeriode() {
        if (periode == null && (periodes != null && !periodes.isEmpty())) {
            periode = periodes.get(0);
        }
        return periode;
    }
    
    public void setSelectedPeriode(Periode periode) {
        this.periode = periode;
    }
    
    public List<Jadwal> getJadwal() {
        return jadwal;
    }
    
    public Jadwal getSelectedJadwal() {
        return selectedJadwal;
    }
    
    public void setSelectedJadwal(Jadwal selectedJadwal) {
        this.selectedJadwal = selectedJadwal;
    }
    
    public List<Ruangan> getRuangans() {
        if (ruangans == null || ruangans.isEmpty()) {
            ruangans = ruanganDao.findAll();
        }
        return ruangans;
    }
    
    public Jadwal getNewJadwal() {
        return newJadwal;
    }
    
    public void setNewJadwal(Jadwal newJadwal) {
        this.newJadwal = newJadwal;
    }
    
    public List<SlotWaktu> getSlotWaktus() {
        return slotWaktuDao.findAll();
    }
    
    public String getTypedNim() {
        return typedNim;
    }
    
    public void setTypedNim(String typedNim) {
        this.typedNim = typedNim;
    }
    // End of getter dan setter
    
    // Business logic simpel
    public void periodeChanged(ValueChangeEvent e) {
        periode = (Periode) e.getNewValue();
    }
    
    public void reloadPeriodes() {
        periodes = periodeDao.findAll();
    }
    
    public void reloadJadwal() {
        jadwal = jadwalDao.findJadwalByPeriode(getSelectedPeriode());
    }
    
    public String formatnya(Periode periode) {
        return PeriodeFormat.format(periode);
    }
    
    public void initializeJadwal() {
        newJadwal = new Jadwal();
        KaryaAkhir k = new KaryaAkhir();
        k.setMahasiswa(new Mahasiswa());
        newJadwal.setKaryaAkhir(k);
        newJadwal.setIdPeriode(getSelectedPeriode());
        newJadwal.setRuangan(new Ruangan());
    }
    
    public String getTipeJadwalRealName(char tipeJadwal) {
        Map<Character, String> temp = new TipeEksekusi().getTipeEksekusis();
        return temp.get(tipeJadwal);
    }
    
    public String createJadwal() {
        initializeJadwal();
        System.out.println("Kepanggil, gan!");
        return "FormJadwal";
    }
    
    public String formatSlotWaktu(SlotWaktu slot) {
        return SlotWaktuFormat.format(slot);
    }
    
    public String saveJadwal() {
        Mahasiswa m = new Mahasiswa(typedNim);
        KaryaAkhir ka = karyaAkhirDao.findByOwner(m);
        newJadwal.setKaryaAkhir(ka);
        jadwalDao.save(newJadwal);
        return "PengelolaanJadwal";
    }
    // End of business logic
    
    // Ajax
    private boolean renderHidden = false;
    
    public void periodeListener(AjaxBehaviorEvent e) {
        // do nothing, tapi kepakenya ini
    }
    
    public void cariListener(AjaxBehaviorEvent e) {
        reloadJadwal();
        renderHidden = true;
    }
    
    public boolean getRenderHidden() {
        return renderHidden;
    }
    
    public List<String> completeNim(String query) {
        List<Mahasiswa> results = karyaAkhirDao
            .getAllMahasiswaYangIkutDiSelectedPeriode(
                getSelectedPeriode().getTipeJadwal(), query);
        List<String> strs = new ArrayList<String>(results.size());
        for (Mahasiswa m : results) {
            strs.add(m.getNim());
        }
        return strs;
    }
    // End of ajax
}
