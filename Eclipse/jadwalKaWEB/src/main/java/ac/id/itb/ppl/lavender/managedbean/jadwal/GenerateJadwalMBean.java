package ac.id.itb.ppl.lavender.managedbean.jadwal;

import ac.id.itb.ppl.lavender.bean.local.DosenLocal;
import ac.id.itb.ppl.lavender.bean.local.JadwalLocal;
import ac.id.itb.ppl.lavender.bean.local.KaryaAkhirLocal;
import ac.id.itb.ppl.lavender.bean.local.PeriodeLocal;
import ac.id.itb.ppl.lavender.bean.local.RuanganLocal;
import ac.id.itb.ppl.lavender.bean.local.SlotWaktuLocal;
import ac.id.itb.ppl.lavender.genetika.ControlerGenerateJadwal;
import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.Ruangan;
import ac.id.itb.ppl.lavender.model.SlotWaktu;
import ac.id.itb.ppl.lavender.formatter.PeriodeFormat;
import ac.id.itb.ppl.lavender.model.Jadwal;
import ac.id.itb.ppl.lavender.util.AllConstants;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.inject.Inject;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.component.datatable.DataTable;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Edbert
 */
@Named(value = "generateJadwal")
@SessionScoped
public class GenerateJadwalMBean implements Serializable {
    private static final long serialVersionUID = -9123212412884303L;
    private static final Logger LOGGER = Logger.getLogger(GenerateJadwalMBean.class.getName());
    
    @Inject private PeriodeLocal periodeDao;
    @Inject private RuanganLocal ruanganDao;
    @Inject private DosenLocal dosenDao;
    @Inject private SlotWaktuLocal slotWaktuDao;
    @Inject private KaryaAkhirLocal karyaAkhirDao;
    @Inject private JadwalLocal jadwalDao;
    private List<Periode> periodes;
    private Periode periode;
    private List<Ruangan> ruangans;
    boolean buttonRender = false;
    
    // Business logic
    public void handlePeriodeChange() {
        if (periode == null) {
            ruangans = new ArrayList<Ruangan>(0);
            setButtonRender(false);
        } else {
            char status = periodeDao.findStatusJadwal(periode);
            if (status == AllConstants.SEDANG_DIGENERATE) {
                ruangans = new ArrayList<Ruangan>(0);
                setButtonRender(false);
            } else {
                reloadRuangans();
                setButtonRender(true);
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
    
    @Asynchronous
    public void generateJadwal() {
        //System.out.println(">>> Masuk generate jadwal <<<");
        if (periode == null) {
            FacesContext.getCurrentInstance()
                .addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Periode harus dipilih!", "Pilih periode yang akan digenerate jadwalnya!"));
            return;
        }
        
        List<Ruangan> selectedRuangans = new ArrayList<Ruangan>();
        for (Ruangan r : ruangans) {
            if (r.getSelected()) {
                selectedRuangans.add(r);
                //System.out.println(">>> ada yang kepilih <<<");
            }
        }
        
        if (selectedRuangans.isEmpty()) {
            FacesContext.getCurrentInstance()
                .addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Harus ada ruangan yang dipilih!", "Pilih ruangan yang akan digunakan untuk pelaksanaan seminar/sidang minimal 1!"));
            return;
        }
        
        ControlerGenerateJadwal cgj = new ControlerGenerateJadwal();
        List<Dosen> dosens = dosenDao.findDosenWithTopikAndKetersediaan(periode);
        List<KaryaAkhir> karyaAkhirs = karyaAkhirDao.findToBeExecutedKaryaAkhirs(periode.getTipeJadwal());
        List<SlotWaktu> slotWaktus = slotWaktuDao.findAll();
        
        
        // ganti status lagi generate jadwal ke tabel periode
        periodeDao.changeGenerateStatusInProgress(periode);
        setButtonRender(false);
        //RequestContext.getCurrentInstance().execute("showDialogTunggu()");
        
        //
        
        
//        try {
//            FacesContext.getCurrentInstance().getExternalContext()
//                .redirect("GenerateJadwal.xhtml");
//        } catch (IOException ioe) {
//            LOGGER.log(Level.SEVERE, null, ioe);
//        }
        
        List<Jadwal> jadwal = cgj.callGenetika(dosens, karyaAkhirs, selectedRuangans, slotWaktus, periode);
        
        if (jadwal != null && !jadwal.isEmpty()) {
            for (Jadwal j : jadwal) {
                j.setIdPeriode(periode);
            }
            
            jadwalDao.saveGeneratedJadwal(jadwal);
            reloadRuangans();

            DataTable dataTable = (DataTable) FacesContext.getCurrentInstance().getViewRoot().findComponent(":formGenerate:ruangans");
            dataTable.setValueExpression("sortBy", null);
            dataTable.reset();
            dataTable.resetValue();
            RequestContext.getCurrentInstance().execute("showDialogGenerate('Jadwal sudah digenerate.')");
        } else {
            RequestContext.getCurrentInstance().execute("showDialogGenerate('Jadwal gagal digenerate.')");
        }
        
        // ganti status jadwal jadi udah di-generate
        periodeDao.changeGenerateStatusDone(periode);
        setButtonRender(true);
        
        
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
    
    public boolean getButtonRender() {
        return buttonRender;
    }
    
    public void setButtonRender(boolean buttonRender) {
        this.buttonRender = buttonRender;
    }
    
    // End of getter dan setter
}
