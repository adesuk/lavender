package ac.id.itb.ppl.lavender.managedbean;

import ac.id.itb.ppl.lavender.dao.jpa.PeriodeDaoImpl;
import ac.id.itb.ppl.lavender.model.Periode;
import java.io.Serializable;
import java.util.ArrayList;
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
    private List<Periode> periodes1;
    private Periode selectedPeriode;
    private String keyword;
    private List<Periode> periodes2;
    private boolean renderHidden;
    private Periode periode = new Periode();
    
    // Getter dan setter
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
//        if (periodes == null || periodes.isEmpty()) {
//            reloadPeriodes();
//        }
        return periodes2;
    }
    
    public boolean getRenderHidden() {
        return renderHidden;
    }
    
    public Periode getPeriode() {
        return periode;
    }
    // End of getter dan setter
    
    // Business logic simpel
    public void reloadPeriodes() {
        periodes1 = periodeDao.findAll();
    }
    
    public void initializePeriode() {
        periode = new Periode();
    }
    // End of business logic
    
    // Ajax
    public void cariListener(AjaxBehaviorEvent e) {
        //reloadPeriodes();
        if (selectedPeriode == null) {
            if (keyword != null || !keyword.equals("")) {
                periodes2 = periodeDao.findByKeyword(keyword);
            } else {
                periodes2 = periodeDao.findAll();
            }
        } else {
            periodes2 = new ArrayList<Periode>(1);
            periodes2.add(selectedPeriode);
        }
        renderHidden = true;
    }
    
    public String tambahPeriode() {
        initializePeriode();
        return "TambahPeriode";
    }
    
    public void savePeriode() {
        //System.out.println(">>> ceritanya nyimpen periode baru <<<");
        periodeDao.save(periode);
        System.out.println(">>> " + periode.getNamaPeriode() + " <<<");
        System.out.println(">>> " + periode.getPeriodeAwal()+ " <<<");
        System.out.println(">>> " + periode.getPeriodeAkhir()+ " <<<");
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
