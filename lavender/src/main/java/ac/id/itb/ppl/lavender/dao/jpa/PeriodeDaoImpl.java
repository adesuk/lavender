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
public class PeriodeDaoImpl extends JpaDao {
    public Periode find(Integer id) {
        return em.find(Periode.class, id);
    }
    
    public List<Periode> findAll() {
        Query query = em.createQuery("select p from Periode p order by p.periodeAwal desc");
        List<Periode> periodes = query.getResultList();
        return periodes;
    }
    
    public void save(Periode periode) {
        
    }
    
    public Periode update(Periode periode) {
        return em.merge(periode);
    }
}
