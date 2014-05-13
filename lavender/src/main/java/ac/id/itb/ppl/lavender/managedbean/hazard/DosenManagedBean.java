package ac.id.itb.ppl.lavender.managedbean.hazard;

import ac.id.itb.ppl.lavender.dao.DosenDao;
import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.model.Dosen;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Edbert
 */
@Named(value = "dosenBean")
@SessionScoped
public class DosenManagedBean implements java.io.Serializable{
    @Inject private DosenDao dosenDao;
    @Inject private PeriodeDao periodeDao;
    private List<Dosen> dosens;
    
    public DosenManagedBean() {
    }
    
    public List<Dosen> getDosens() {
        return dosenDao.findDosenWithTopikAndKetersediaan(periodeDao.find(1));
    }
}
