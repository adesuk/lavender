package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.DosenDao;
import ac.id.itb.ppl.lavender.model.*;
import java.util.*;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Edbert
 */
@Stateless
public class DosenDaoImpl extends JpaDao {
    public List<Dosen> getDosenWithTopikAndKetersediaan() {
        Query query = em.createQuery(
            "select distinct dosen from Dosen as dosen left join fetch dosen.bidangKeahlian as bk"
            );
        List<Dosen> dosens = query.getResultList();
        return dosens;
    }
}
