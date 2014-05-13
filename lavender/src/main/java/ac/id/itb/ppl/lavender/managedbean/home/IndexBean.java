package ac.id.itb.ppl.lavender.managedbean.home;

import ac.id.itb.ppl.lavender.dao.JadwalDao;
import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.model.Jadwal;
import java.io.Serializable;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author edbert
 */
@Named("index")
@SessionScoped
public class IndexBean implements Serializable {
    
    @Inject private JadwalDao jadwalDao;
    @Inject private PeriodeDao periodeDao;
    private List<Jadwal> jadwal;
    
    public void init() {
        jadwal = jadwalDao.findJadwalByPeriodeAndVersi(null, null);
    }
    public List<Jadwal> getJadwal() {
        return jadwal;
    }
}
