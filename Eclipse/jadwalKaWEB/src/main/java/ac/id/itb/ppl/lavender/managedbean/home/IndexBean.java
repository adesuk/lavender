package ac.id.itb.ppl.lavender.managedbean.home;

import ac.id.itb.ppl.lavender.bean.JadwalBean;
import ac.id.itb.ppl.lavender.bean.PeriodeBean;
import ac.id.itb.ppl.lavender.bean.local.JadwalLocal;
import ac.id.itb.ppl.lavender.bean.local.PeriodeLocal;
import ac.id.itb.ppl.lavender.model.Jadwal;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.ejb.EJB;
import javax.inject.Named;

/**
 *
 * @author edbert
 */
@Named("index")
@SessionScoped
public class IndexBean implements Serializable {
    
    @EJB private JadwalBean jadwalDao;
    @EJB private PeriodeBean periodeDao;
    private List<Jadwal> jadwal;
    
    public void init() {
        jadwal = jadwalDao.findJadwalByPeriodeAndVersi(null, null);
    }
    public List<Jadwal> getJadwal() {
        return jadwal;
    }
}
