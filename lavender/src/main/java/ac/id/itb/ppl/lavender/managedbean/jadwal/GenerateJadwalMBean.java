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
import ac.id.itb.ppl.lavender.util.AllConstants;
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
    private Periode periode;
    private List<Ruangan> ruangans;
    private List<Ruangan> selectedRuangans;
    boolean buttonRender = false;
    boolean generateRender = false;
    
    // Business logic
    public void handlePeriodeChange() {
        if (periode == null) {
            ruangans = new ArrayList<Ruangan>(0);
            setButtonRender(false);
            setGenerateRender(false);
        } else {
            char status = periodeDao.findStatusJadwal(periode);
            if (status == AllConstants.SEDANG_DIGENERATE) {
                ruangans = new ArrayList<Ruangan>(0);
                setButtonRender(false);
                setGenerateRender(true);
            } else {
                reloadRuangans();
                setButtonRender(true);
                setGenerateRender(false);
            }
        }
    }
    
    public void reloadPeriodes() {
        periodes = periodeDao.findAll();
    }
    
    public void reloadRuangans() {
        if (getPeriode() == null) {
            ruangans = new ArrayList<Ruangan>(0);
        } else {
            ruangans = ruanganDao.findRuanganDanKetersediaanRuangans(getPeriode());
        }
    }
    
    public String formatPeriode(Periode periode) {
        return PeriodeFormat.format(periode);
    }
    
    public void generateJadwal() {
        if (getSelectedRuangans() == null || getSelectedRuangans().isEmpty()) {
            return;
        }
        
        ControlerGenerateJadwal cgj = new ControlerGenerateJadwal();
        List<Dosen> dosens = dosenDao.findDosenWithTopikAndKetersediaan(periode);
        List<KaryaAkhir> karyaAkhirs = null;
        List<SlotWaktu> slotWaktus = slotWaktuDao.findAll();
        
        // ganti status lagi generate jadwal ke tabel periode
        periodeDao.changeGenerateStatusInProgress(periode);
        
        cgj.callGenetika(dosens, karyaAkhirs, getSelectedRuangans(), slotWaktus, periode);
        
        
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("ProsesGenerateJadwal.xhtml");
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, null, ioe);
        }
        
        // ganti status jadwal jadi udah di-generate
    }
    // End of business logic
    
    // Getter dan setter
    public List<Periode> getPeriodes() {
        if (periodes == null || periodes.isEmpty()) {
            reloadPeriodes();
        }
        return periodes;
    }
    
    public Periode getPeriode() {
        return periode;
    }
    
    public void setPeriode(Periode periode) {
        this.periode = periode;
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
    
    public boolean getButtonRender() {
        return buttonRender;
    }
    
    public void setButtonRender(boolean buttonRender) {
        this.buttonRender = buttonRender;
    }
    
    public boolean getGenerateRender() {
        return generateRender;
    }
    
    public void setGenerateRender(boolean generateRender) {
        this.generateRender = generateRender;
    }
    // End of getter dan setter
}
