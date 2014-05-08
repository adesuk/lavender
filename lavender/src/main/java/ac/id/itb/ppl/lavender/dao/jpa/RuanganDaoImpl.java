package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.RuanganDao;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.Ruangan;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author Edbert
 */
@Stateless
public class RuanganDaoImpl extends JpaDao implements RuanganDao { 
    @Override
    public List<Ruangan> findAll() {
        Query query = em.createQuery(
            "select r from Ruangan r");
        List<Ruangan> ruangans = query.getResultList();
        return ruangans;
    }
    
    @Override
    public Ruangan find(String id) {
        return em.find(Ruangan.class, id);
    }
    
    
}
