package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.model.Periode;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author edbert
 */
@Stateless
@LocalBean
public class PeriodeDaoImpl {
    @PersistenceContext(unitName = "lavenderPU")
    protected EntityManager em;
    
    public Periode find(Integer id) {
        return em.find(Periode.class, id);
    }
    
    public List<Periode> findAll() {
        Query query = em.createQuery("select p from Periode p");
        List<Periode> periodes = query.getResultList();
        return periodes;
    }
}
