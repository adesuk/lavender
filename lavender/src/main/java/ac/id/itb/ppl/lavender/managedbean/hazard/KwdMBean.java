package ac.id.itb.ppl.lavender.managedbean.hazard;

import ac.id.itb.ppl.lavender.dao.jpa.KwdDaoImpl;
import ac.id.itb.ppl.lavender.model.KetersediaanWaktuDosen;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author edbert
 */
@Named(value = "kwdbean")
@SessionScoped
public class KwdMBean implements java.io.Serializable {
    @EJB
    private KwdDaoImpl kwdDao;
    
    public List<KetersediaanWaktuDosen> getKwds() {
        return kwdDao.kwds();
    }
}
