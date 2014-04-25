package ac.id.itb.ppl.lavender.managedbean;

import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.dao.jpa.JadwalDaoImpl;
import ac.id.itb.ppl.lavender.dao.jpa.PeriodeDaoImpl;
import ac.id.itb.ppl.lavender.model.Periode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;

/**
 *
 * @author Edbert
 */
@Named(value = "pengelolaanJadwal")
@SessionScoped
public class PengelolaanJadwal implements java.io.Serializable {
    private static final long serialVersionUID = -91232124123L;
    
    @EJB
    private PeriodeDaoImpl periodeDao;
    //@EJB
    private JadwalDaoImpl jadwalDao;
    private List<Periode> periodes;
    private Periode periode;
    private String iseng;
    
    public String getIseng() { return iseng; }
    
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
    
    public void periodeChanged(ValueChangeEvent e) {
        periode = (Periode) e.getNewValue();
    }
    
    public void reloadPeriodes() {
        periodes = periodeDao.findAll();
    }
    
    public String formatnya(Periode periode) {
        Locale indonesia = new Locale("in", "ID");
        Date date1 = periode.getPeriodeAwal();
        Date date2 = periode.getPeriodeAkhir();
        
        SimpleDateFormat sdf1 = new SimpleDateFormat("d MMM y", indonesia);
        return new StringBuilder()
            .append(sdf1.format(date1))
            .append(" s.d. ")
            .append(sdf1.format(date2))
            .toString();
    }
}
