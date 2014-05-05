package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.TopikDao;
import ac.id.itb.ppl.lavender.model.Topik;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Edbert
 */
@Stateless
public class TopikDaoImpl extends JpaDao implements TopikDao {
    @Override
    public List<Topik> findAll() {
        return em.createQuery("select t from Topik as t").getResultList();
    }
    
    @Override
    public List<Topik> findAllWithDosens() {
        List<Topik> topiks = em.createQuery(
            "select t from Topik as t join left join fetch t.dosenList as d")
            .getResultList();
        return topiks;
    }
}
