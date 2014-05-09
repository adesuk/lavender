package ac.id.itb.ppl.lavender.managedbean.hazard;

import ac.id.itb.ppl.lavender.dao.jpa.KaryaAkhirDaoImpl;
import ac.id.itb.ppl.lavender.dao.jpa.PeriodeDaoImpl;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Edbert
 */
@Named(value = "topikKaryaAkhir")
@SessionScoped
public class TopikKaryaAkhirMBean implements Serializable {
    @EJB private KaryaAkhirDaoImpl karyaAkhirDao;
    @EJB private PeriodeDaoImpl periodeDao;
    private List<KaryaAkhir> karyaAkhirs;
    
    //<editor-fold defaultstate="" desc="Getter dan setter">
    public List<KaryaAkhir> getKaryaAkhirs() {
        return karyaAkhirs;
    }
    
    public void reloadKaryaAkhirs() {
        //karyaAkhirs = karyaAkhirDao.getAllMahasiswaYangAkanIkutSeminar();
    }
    //</editor-fold>
}
