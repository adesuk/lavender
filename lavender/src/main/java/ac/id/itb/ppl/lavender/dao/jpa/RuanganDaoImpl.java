package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.model.Ruangan;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author Edbert
 */
@Stateless
@LocalBean
public class RuanganDaoImpl extends JpaDao { 
    public List<Ruangan> findAll() {
        Query query = em.createQuery(
            "select r from Ruangan as r");
        List<Ruangan> ruangans = query.getResultList();
        return ruangans;
    }
    
    public Ruangan find(String id) {
        return em.find(Ruangan.class, id);
    }
}
