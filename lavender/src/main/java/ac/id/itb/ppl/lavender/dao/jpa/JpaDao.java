package ac.id.itb.ppl.lavender.dao.jpa;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Edbert
 */
public abstract class JpaDao {
    //private EntityManagerFactory emf = Persistence.createEntityManagerFactory("lavenderPU");
    @PersistenceContext(unitName = "lavenderPU")
    protected EntityManager em;
    
    public JpaDao() {
    }
}
