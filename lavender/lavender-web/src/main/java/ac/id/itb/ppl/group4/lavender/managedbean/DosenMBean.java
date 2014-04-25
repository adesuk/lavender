package ac.id.itb.ppl.group4.lavender.managedbean;

import ac.id.itb.ppl.group4.lavender.bean.remote.DosenBean;
import ac.id.itb.ppl.group4.lavender.dao.jpa.DosenDaoImpl;
import ac.id.itb.ppl.group4.lavender.daoz.*;
import ac.id.itb.ppl.group4.lavender.modelz.*;
import java.util.*;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author Edbert
 */
@Named(value = "dosenMBean")
@SessionScoped
public class DosenMBean implements java.io.Serializable {

    @EJB
    private DosenBean dosenBean;
    @EJB
    private OpsiDao opsiDao;
    private List<ac.id.itb.ppl.group4.lavender.model.Dosen> dosens;
    private List<Opsi> opsis;
    private Dosen dosen;
    
    public DosenMBean() { }
    
    public List<ac.id.itb.ppl.group4.lavender.model.Dosen> getDosens() {
        if (dosens == null || dosens.isEmpty()) {
//            dosens = dosenDao.getDosens();
              dosens = dosenBean.findAll();
        }
        return dosens;
    }
    
    public List<Opsi> getOpsis() {
        if (opsis == null || opsis.isEmpty()) {
            opsis = opsiDao.getOpsis();
        }
        return opsis;
    }
    
    public Dosen getDosen() {
        return dosen;
    }
    
    public String showKeahlian(Dosen dosen) {
        this.dosen = dosen;
        return "BidangKeahlian";
    }
}
