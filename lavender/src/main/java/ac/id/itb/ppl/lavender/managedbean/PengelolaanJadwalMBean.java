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
import ac.id.itb.ppl.lavender.util.VersiFormat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Edbert
 */
@Named(value = "pengelolaanJadwal")
@SessionScoped
public class PengelolaanJadwalMBean implements Serializable {
    @EJB private PeriodeDaoImpl periodeDao;
    @EJB private JadwalDaoImpl jadwalDao;
    @EJB private RuanganDaoImpl ruanganDao;
    @EJB private KaryaAkhirDaoImpl karyaAkhirDao;
    @EJB private SlotWaktuDaoImpl slotWaktuDao;
    private List<Periode> periodes;
    private Periode periode;
    private List<Jadwal> jadwal;
    private Jadwal jadwalDetail;
    private List<Ruangan> ruangans;
    private String typedNim;
    private List<Date> jadwalVersions;
    private Date selectedJadwalVersion;
    
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
            //periode = periodes.get(0);
        }
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
    
    public List<Ruangan> getRuangans() {
        if (ruangans == null || ruangans.isEmpty()) {
            ruangans = ruanganDao.findAll();
        }
        return ruangans;
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
    
    public String formatnya(Periode periode) {
        return PeriodeFormat.format(periode);
    }
    
    public void initializeJadwal() {
        typedNim = null;
        jadwalDetail = new Jadwal();
        KaryaAkhir k = new KaryaAkhir();
        k.setMahasiswa(new Mahasiswa());
        jadwalDetail.setKaryaAkhir(k);
        jadwalDetail.setIdPeriode(getSelectedPeriode());
        jadwalDetail.setRuangan(new Ruangan());
        System.out.println("Masuk inisialisasi jadwal");
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
    
    public String createJadwal() {
        initializeJadwal();
        return "TambahJadwal";
    }
    
    public String editJadwal() {
        
        System.out.println(">>>>> " + jadwalDetail.getKaryaAkhir().getMahasiswa().getNim());
        return "UbahJadwal";
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
    
    public String saveJadwal() {
        Mahasiswa m = new Mahasiswa(typedNim);
        KaryaAkhir ka = karyaAkhirDao.findByOwner(m);
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
    
    public String updateJadwal() {
        System.out.println("Masuk update jadwal");
        return "PengelolaanJadwal?faces-redirect=true";
    }
    // End of business logic
    
    // Ajax
    private boolean renderHidden = false;
    
    public void periodeListener(AjaxBehaviorEvent e) {
        reloadJadwalVersions();
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
    
    public void onRowSelect(SelectEvent e) {
        this.jadwalDetail = ((Jadwal) e.getObject());
    }
    // End of ajax
}
