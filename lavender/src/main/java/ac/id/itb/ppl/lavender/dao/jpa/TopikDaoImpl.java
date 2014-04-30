package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.model.Topik;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Edbert
 */
@Stateless
@LocalBean
public class TopikDaoImpl extends JpaDao {
    public List<Topik> findAll() {
        return em.createQuery("select t from Topik as t").getResultList();
    }
    
    public List<Topik> findAllWithDosens() {
        List<Topik> topiks = em.createQuery(
            "select t from Topik as t join left join fetch t.dosenList as d")
            .getResultList();
        return topiks;
    }
}
