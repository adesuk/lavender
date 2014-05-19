package ac.id.itb.ppl.lavender.managedbean.hazard;

import ac.id.itb.ppl.lavender.bean.local.DosenLocal;
import ac.id.itb.ppl.lavender.bean.local.PeriodeLocal;
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
    @Inject private DosenLocal dosenDao;
    @Inject private PeriodeLocal periodeDao;
    private List<Dosen> dosens;
    
    public DosenManagedBean() {
    }
    
    public List<Dosen> getDosens() {
        return dosenDao.findDosenWithTopikAndKetersediaan(periodeDao.find(1));
    }
}
