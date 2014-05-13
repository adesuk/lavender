package ac.id.itb.ppl.lavender.managedbean.hazard;

import ac.id.itb.ppl.lavender.dao.KaryaAkhirDao;
import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.Periode;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Edbert
 */
@Named(value = "karAkh")
@SessionScoped
public class KarAkhManagedBean implements java.io.Serializable {
    @Inject
    private KaryaAkhirDao karyaAkhirDao;
    @Inject
    private PeriodeDao periodeDao;
    private List<KaryaAkhir> karyaAkhirs;
    
    public KarAkhManagedBean() {
    }
    
    public List<KaryaAkhir> getKaryaAkhirs() {
        //if (karyaAkhirs == null || karyaAkhirs.isEmpty()) {
        Periode periode = periodeDao.find(1);
        //System.out.println(">>> Periode " + periode.getTipeJadwal() + " +++ " + (int)periode.getTipeJadwal().charValue() + " <<<");
            karyaAkhirs = karyaAkhirDao.findToBeExecutedKaryaAkhirs(periode.getTipeJadwal());
        //}
        return karyaAkhirs;
    }
}
