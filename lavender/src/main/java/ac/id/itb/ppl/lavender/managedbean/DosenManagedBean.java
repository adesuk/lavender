package ac.id.itb.ppl.lavender.managedbean;

import ac.id.itb.ppl.lavender.dao.jpa.DosenDaoImpl;
import ac.id.itb.ppl.lavender.model.Dosen;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Edbert
 */
@Named(value = "dosenBean")
@SessionScoped
public class DosenManagedBean implements java.io.Serializable{
    @EJB private DosenDaoImpl dosenDao;
    private List<Dosen> dosens;
    
    public DosenManagedBean() {
    }
    
    public List<Dosen> getDosens() {
        if (dosens == null || dosens.isEmpty()) {
            dosens = dosenDao.getDosenWithTopikAndKetersediaan();
        }
        return dosens;
    }
}
