package ac.id.itb.ppl.lavender.managedbean.hazard;

import ac.id.itb.ppl.lavender.bean.JadwalKuliahBean;
import ac.id.itb.ppl.lavender.bean.PeriodeBean;
import ac.id.itb.ppl.lavender.bean.local.JadwalKuliahLocal;
import ac.id.itb.ppl.lavender.bean.local.PeriodeLocal;
import ac.id.itb.ppl.lavender.model.*;

import java.io.Serializable;
import java.util.*;

import javax.enterprise.context.SessionScoped;
import javax.ejb.EJB;
import javax.inject.Named;
/**
 *
 * @author edbert
 */
@Named("jadwalKuliah")
@SessionScoped
public class JadwalKuliahMBean implements Serializable {
    @EJB JadwalKuliahBean jkDao;
    @EJB PeriodeBean periodeDao;
    private List<JadwalKuliah> jk;
    
    public List<JadwalKuliah> getJadwalKuliah() {
        return jkDao.findByPeriode(periodeDao.find(1));
    }
}
