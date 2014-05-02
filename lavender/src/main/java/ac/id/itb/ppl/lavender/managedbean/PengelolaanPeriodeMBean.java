package ac.id.itb.ppl.lavender.managedbean;

import ac.id.itb.ppl.lavender.dao.jpa.PeriodeDaoImpl;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.util.TipeEksekusi;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Edbert
 */
@Named(value = "pengelolaanPeriode")
@SessionScoped
public class PengelolaanPeriodeMBean implements Serializable {
    @EJB private PeriodeDaoImpl periodeDao;
    private List<Periode> periodes1;
    private Periode selectedPeriode;
    private String keyword;
    private List<Periode> periodes2;
    private boolean renderHidden;
    private Periode periode = new Periode();
    
    //<editor-fold defaultstate="collapsed" desc="Getter dan setter">
    public List<Periode> getPeriodes1() {
        if (periodes1 == null || periodes1.isEmpty()) {
            reloadPeriodes();
        }
        return periodes1;
    }
    
    public Periode getSelectedPeriode() {
        return selectedPeriode;
    }
    
    public void setSelectedPeriode(Periode selectedPeriode) {
        this.selectedPeriode = selectedPeriode;
    }
    
    public String getKeyword() {
        return keyword;
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public List<Periode> getPeriodes2() {
        return periodes2;
    }
    
    public boolean getRenderHidden() {
        return renderHidden;
    }
    
    public Periode getPeriode() {
        return periode;
    }
    
    public Map<String, Character> getTipeJadwals() {
        return new TipeEksekusi().getTipeEksekusis();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Business logic simpel">
    public void reloadPeriodes() {
        periodes1 = periodeDao.findAll();
    }
    
    public void reloadPeriodes2() {
        if (selectedPeriode == null) {
            if (keyword != null) {
                keyword = "";
            }
            periodes2 = periodeDao.findByKeyword(keyword);
        } else {
            periodes2 = new ArrayList<Periode>(1);
            periodes2.add(selectedPeriode);
        }
    }
    
    public void initializePeriode() {
        periode = new Periode();
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Ajaxnya">
    public void cariListener(AjaxBehaviorEvent e) {
        reloadPeriodes2();
        renderHidden = true;
    }
    
    public String tambahPeriode() {
        initializePeriode();
        return "TambahPeriode";
    }
    
    public String savePeriode() {
        periodeDao.save(periode);
        initializePeriode();
        reloadPeriodes2();
        return "PengelolaanPeriode";
    }
    
    public void deletePeriode() {
        for (Periode p : periodes2) {
            if (p.getSelected()) {
                periodeDao.delete(p);
            }
        }
        reloadPeriodes2();
    }
    
    public void handleClose() {
        initializePeriode();
    }
    
    public void onEdit(RowEditEvent event) {
        periodeDao.update((Periode) event.getObject());
    }
    //</editor-fold>
    
}
