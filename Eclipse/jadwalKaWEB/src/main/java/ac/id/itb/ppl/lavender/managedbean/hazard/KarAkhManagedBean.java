package ac.id.itb.ppl.lavender.managedbean.hazard;

import ac.id.itb.ppl.lavender.bean.KaryaAkhirBean;
import ac.id.itb.ppl.lavender.bean.PeriodeBean;
import ac.id.itb.ppl.lavender.bean.local.KaryaAkhirLocal;
import ac.id.itb.ppl.lavender.bean.local.PeriodeLocal;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.Periode;

import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.ejb.EJB;
import javax.inject.Named;

/**
 *
 * @author Edbert
 */
@Named(value = "karAkh")
@SessionScoped
public class KarAkhManagedBean implements java.io.Serializable {
    @EJB
    private KaryaAkhirBean karyaAkhirDao;
    @EJB
    private PeriodeBean periodeDao;
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
