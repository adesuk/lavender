package ac.id.itb.ppl.lavender.managedbean;

import ac.id.itb.ppl.lavender.dao.jpa.KaryaAkhirDaoImpl;
import ac.id.itb.ppl.lavender.dao.jpa.PeriodeDaoImpl;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.Periode;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Edbert
 */
@Named(value = "karAkh")
@SessionScoped
public class KarAkhManagedBean implements java.io.Serializable {
    @EJB
    private KaryaAkhirDaoImpl karyaAkhirDao;
    @EJB
    private PeriodeDaoImpl periodeDao;
    private List<KaryaAkhir> karyaAkhirs;
    
    public KarAkhManagedBean() {
    }
    
    public List<KaryaAkhir> getKaryaAkhirs() {
        //if (karyaAkhirs == null || karyaAkhirs.isEmpty()) {
        Periode periode = periodeDao.find(1);
        //System.out.println(">>> Periode " + periode.getTipeJadwal() + " +++ " + (int)periode.getTipeJadwal().charValue() + " <<<");
            karyaAkhirs = karyaAkhirDao.getToBeExecutedKaryaAkhirs(periode.getTipeJadwal());
        //}
        return karyaAkhirs;
    }
}
