package ac.id.itb.ppl.lavender.managedbean.hazard;

import ac.id.itb.ppl.lavender.dao.*;
import ac.id.itb.ppl.lavender.model.*;
import java.io.Serializable;
import java.util.*;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
/**
 *
 * @author edbert
 */
@Named("jadwalKuliah")
@SessionScoped
public class JadwalKuliahMBean implements Serializable {
    @Inject JadwalKuliahDao jkDao;
    @Inject PeriodeDao periodeDao;
    private List<JadwalKuliah> jk;
    
    public List<JadwalKuliah> getJadwalKuliah() {
        return jkDao.findByPeriode(periodeDao.find(1));
    }
}
