package ac.id.itb.ppl.lavender.managedbean.hazard;

import ac.id.itb.ppl.lavender.bean.DosenBean;
import ac.id.itb.ppl.lavender.bean.PeriodeBean;
import ac.id.itb.ppl.lavender.bean.local.DosenLocal;
import ac.id.itb.ppl.lavender.bean.local.PeriodeLocal;
import ac.id.itb.ppl.lavender.model.Dosen;

import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.ejb.EJB;
import javax.inject.Named;

/**
 *
 * @author Edbert
 */
@Named(value = "dosenBean")
@SessionScoped
public class DosenManagedBean implements java.io.Serializable{
    @EJB private DosenBean dosenDao;
    @EJB private PeriodeBean periodeDao;
    private List<Dosen> dosens;
    
    public DosenManagedBean() {
    }
    
    public List<Dosen> getDosens() {
        return dosenDao.findDosenWithTopikAndKetersediaan(periodeDao.find(1));
    }
}
