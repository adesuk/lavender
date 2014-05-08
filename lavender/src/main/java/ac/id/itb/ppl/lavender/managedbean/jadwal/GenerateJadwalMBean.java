package ac.id.itb.ppl.lavender.managedbean.jadwal;

import ac.id.itb.ppl.lavender.dao.DosenDao;
import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.dao.RuanganDao;
import ac.id.itb.ppl.lavender.dao.SlotWaktuDao;
import ac.id.itb.ppl.lavender.genetika.ControlerGenerateJadwal;
import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.Ruangan;
import ac.id.itb.ppl.lavender.model.SlotWaktu;
import ac.id.itb.ppl.lavender.formatter.PeriodeFormat;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Edbert
 */
@Named(value = "generateJadwal")
@SessionScoped
public class GenerateJadwalMBean implements Serializable {
    private static final long serialVersionUID = -9123212412884303L;
    private static final Logger LOGGER = Logger.getLogger(GenerateJadwalMBean.class.getName());
    
    @Inject private PeriodeDao periodeDao;
    @Inject private RuanganDao ruanganDao;
    @Inject private DosenDao dosenDao;
    @Inject private SlotWaktuDao slotWaktuDao;
    private List<Periode> periodes;
    private Periode selectedPeriode;
    private List<Ruangan> ruangans;
    private List<Ruangan> selectedRuangans;
    
    // Business logic
    public void handlePeriodeChange() {
        if (periodes == null || periodes.isEmpty()) {
            ruangans = new ArrayList<Ruangan>(0);
        } else {
            reloadRuangans();
        }
    }
    
    public void reloadPeriodes() {
        periodes = periodeDao.findAll();
    }
    
    public void reloadRuangans() {
        ruangans = ruanganDao.findAll();
    }
    
    public String formatPeriode(Periode periode) {
        return PeriodeFormat.format(periode);
    }
    
    public void generateJadwal() {
        ControlerGenerateJadwal cgj = new ControlerGenerateJadwal();
        List<Dosen> dosens = dosenDao.findDosenWithTopikAndKetersediaan(selectedPeriode);
        List<KaryaAkhir> karyaAkhirs = null;
        List<SlotWaktu> slotWaktus = slotWaktuDao.findAll();
        cgj.callGenetika(dosens, karyaAkhirs, selectedRuangans, slotWaktus, selectedPeriode);
        
        
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("ProsesGenerateJadwal");
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, null, ioe);
        }
    }
    // End of business logic
    
    // Getter dan setter
    public List<Periode> getPeriodes() {
        if (periodes == null || periodes.isEmpty()) {
            reloadPeriodes();
        }
        return periodes;
    }
    
    public Periode getSelectedPeriode() {
        if (selectedPeriode == null && (periodes != null && !periodes.isEmpty())) {
            selectedPeriode = periodes.get(0);
        }
        return selectedPeriode;
    }
    
    public void setSelectedPeriode(Periode selectedPeriode) {
        this.selectedPeriode = selectedPeriode;
    }
    
    public List<Ruangan> getRuangans() {
        return ruangans;
    }
    
    public List<Ruangan> getSelectedRuangans() {
        return selectedRuangans;
    }
    
    public void setSelectedRuangans(List<Ruangan> selectedRuangans) {
        this.selectedRuangans = selectedRuangans;
    }
    // End of getter dan setter
}
