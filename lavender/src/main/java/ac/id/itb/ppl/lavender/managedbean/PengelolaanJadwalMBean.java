package ac.id.itb.ppl.lavender.managedbean;

import ac.id.itb.ppl.lavender.dao.jpa.JadwalDaoImpl;
import ac.id.itb.ppl.lavender.dao.jpa.KaryaAkhirDaoImpl;
import ac.id.itb.ppl.lavender.dao.jpa.PeriodeDaoImpl;
import ac.id.itb.ppl.lavender.dao.jpa.RuanganDaoImpl;
import ac.id.itb.ppl.lavender.model.*;
import ac.id.itb.ppl.lavender.util.PeriodeFormat;
import java.util.List;
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
    
    @EJB
    private PeriodeDaoImpl periodeDao;
    @EJB
    private JadwalDaoImpl jadwalDao;
    @EJB
    private RuanganDaoImpl ruanganDao;
    @EJB
    private KaryaAkhirDaoImpl karyaAkhirDao;
    private List<Periode> periodes;
    private Periode periode;
    private List<Jadwal> jadwal;
    private Jadwal selectedJadwal;
    private List<Ruangan> ruangans;
    private Jadwal newJadwal;
    
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
        newJadwal.setIdPeriode(new Periode());
        newJadwal.setRuangan(new Ruangan());
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
        List<String> results = karyaAkhirDao.getAllMahasiswaYangIkutDiSelectedPeriode(null, query);
        return results;
    }
    // End of ajax
}
