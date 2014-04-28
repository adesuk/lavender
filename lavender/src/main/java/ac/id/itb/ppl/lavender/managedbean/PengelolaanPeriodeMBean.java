package ac.id.itb.ppl.lavender.managedbean;

import ac.id.itb.ppl.lavender.dao.jpa.PeriodeDaoImpl;
import ac.id.itb.ppl.lavender.model.Periode;
import java.io.Serializable;
import java.util.List;
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
    @EJB
    private PeriodeDaoImpl periodeDao;
    private List<Periode> periodes;
    private Periode selectedPeriode;
    private boolean renderHidden;
    private Periode periode = new Periode();
    
    // getter dan setter
    public List<Periode> getPeriodes() {
//        if (periodes == null || periodes.isEmpty()) {
//            reloadPeriodes();
//        }
        return periodes;
    }
    
    public Periode getSelectedPeriode() {
        return selectedPeriode;
    }
    
    public void setSelectedPeriode(Periode selectedPeriode) {
        this.selectedPeriode = selectedPeriode;
    }
    
    public boolean getRenderHidden() {
        return renderHidden;
    }
    
    public Periode getPeriode() {
        return periode;
    }
    
//    public void setPeriode(Periode periode) {
//        this.periode = periode;
//    }
    // end of getter dan setter
    
    // business logic simpel
    public void reloadPeriodes() {
        periodes = periodeDao.findAll();
    }
    
    public void initializePeriode() {
        periode = new Periode();
    }
    // End of business logic
    
    // Ajax
    public void cariListener(AjaxBehaviorEvent e) {
        reloadPeriodes();
        renderHidden = true;
    }
    
    public void savePeriode() {
        System.out.println(">>> ceritanya nyimpen periode baru <<<");
        initializePeriode();
        
        FacesMessage msg = new FacesMessage("Periode telah ditambah");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    public void handleClose() {
        initializePeriode();
    }
    
    public void onEdit(RowEditEvent event) {
        periodeDao.update((Periode) event.getObject());
    }
    // End of ajax
}
