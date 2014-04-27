package ac.id.itb.ppl.lavender.managedbean;

import ac.id.itb.ppl.lavender.dao.jpa.PeriodeDaoImpl;
import ac.id.itb.ppl.lavender.dao.jpa.RuanganDaoImpl;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.Ruangan;
import ac.id.itb.ppl.lavender.util.PeriodeFormat;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;

/**
 *
 * @author Edbert
 */
@Named(value = "generateJadwal")
@SessionScoped
public class GenerateJadwalMBean implements Serializable {
    private static final long serialVersionUID = -91232124123L;
    
    @EJB
    private PeriodeDaoImpl periodeDao;
    @EJB
    private RuanganDaoImpl ruanganDao;
    private List<Periode> periodes;
    private Periode selectedPeriode;
    private List<Ruangan> ruangans;
    private List<Ruangan> selectedRuangans;
    
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
        if (ruangans == null || ruangans.isEmpty()) {
            reloadRuangans();
        }
        return ruangans;
    }
    
    public List<Ruangan> getSelectedRuangans() {
        return selectedRuangans;
    }
    
    public void setSelectedRuangans(List<Ruangan> selectedRuangans) {
        this.selectedRuangans = selectedRuangans;
    }
    // End of getter dan setter
    
    // Business logic
    public void periodeListener(AjaxBehaviorEvent e) {
        // do nothing
    }
    
    public void reloadPeriodes() {
        periodes = periodeDao.findAll();
    }
    
    public void reloadRuangans() {
        ruangans = ruanganDao.findAll();
    }
    
    public String formatnya(Periode periode) {
        return PeriodeFormat.format(periode);
    }
    
    public String generateJadwal() {
        for (Ruangan r : selectedRuangans) {
            System.out.println(">>> " + r.getKdRuangan());
        }
        return "ProsesGenerateJadwal.xhtml";
    }
    // End of business logic
}
