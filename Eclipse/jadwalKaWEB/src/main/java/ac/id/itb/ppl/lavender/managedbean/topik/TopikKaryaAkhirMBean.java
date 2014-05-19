package ac.id.itb.ppl.lavender.managedbean.topik;

import ac.id.itb.ppl.lavender.bean.local.KaryaAkhirLocal;
import ac.id.itb.ppl.lavender.bean.local.TopikLocal;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.Topik;
import ac.id.itb.ppl.lavender.util.TipeEksekusi;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;

/**
 *
 * @author Edbert
 */
@Named(value = "topikKaryaAkhir")
@SessionScoped
public class TopikKaryaAkhirMBean implements Serializable {
    
    private static final long serialVersionUID = -9001237248551290L;
    private static final Logger LOGGER = Logger.getLogger(TopikKaryaAkhirMBean.class.getName());
    
    @Inject private KaryaAkhirLocal karyaAkhirDao;
    @Inject private TopikLocal topikDao;
    private int tahun = getTahuns().get(0);
    private String judul;
    private List<KaryaAkhir> karyaAkhirs;
    private KaryaAkhir karyaAkhir;
    private List<Topik> topiks;
    private Topik topik;
    
    //<editor-fold defaultstate="collapsed" desc="Business logic">
    public void reloadKaryaAkhirs() {
        karyaAkhirs = karyaAkhirDao.findKaryaAkhir(tahun, judul, judul);
    }
    
    public void reloadTopiks() {
        topiks = topikDao.findAll();
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Ajax">
    public void displayKaryaAkhirs() {
        reloadKaryaAkhirs();
    }
    
    public void edit(KaryaAkhir karyaAkhir) {
        this.karyaAkhir = karyaAkhir;
        reloadTopiks();
        
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("EditTopikKaryaAkhir.xhtml");
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, null, ioe);
        }
    }
    
    public void save() {
        karyaAkhirDao.update(karyaAkhir);
        
        reloadKaryaAkhirs();
        
        RequestContext.getCurrentInstance().execute("showInfo()");
    }
    
    public void cancel() {
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("PenentuanTopikKaryaAkhir.xhtml");
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, null, ioe);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getter dan setter">
    public List<KaryaAkhir> getKaryaAkhirs() {
        return karyaAkhirs;
    }
    
    public KaryaAkhir getKaryaAkhir() {
        return karyaAkhir;
    }
    
    public List<Integer> getTahuns() {
        List<Integer> tahuns = new ArrayList<Integer>();
        Calendar c = Calendar.getInstance();
        for (int i = (c.get(Calendar.YEAR) - 6); i < c.get(Calendar.YEAR); i++) {
            tahuns.add(i);
        }
        return tahuns;
    }
    
    public int getTahun() {
        return tahun;
    }
    
    public void setTahun(int tahun) {
        this.tahun = tahun;
    }
    
    public String getJudul() {
        return judul;
    }
    
    public void setJudul(String judul) {
        this.judul = judul;
    }
    
    public List<Topik> getTopiks() {
        return topiks;
    }
    
    public Topik getTopik() {
        return topik;
    }
    
    public void setTopik(Topik topik) {
        this.topik = topik;
    }
    
    public String getTipeStatusKaryaName(Character tipeStatusKarya) {
        return new TipeEksekusi().getName(tipeStatusKarya);
    }
    //</editor-fold>
}
