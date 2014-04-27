package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.model.*;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Edbert
 */
@Stateless
@LocalBean
public class KaryaAkhirDaoImpl extends JpaDao {
    public List<KaryaAkhir> findAll() {
        return null;
    }
    
    public KaryaAkhir findByOwner(Mahasiswa mahasiswa) {
        KaryaAkhir ka = (KaryaAkhir) em.createQuery(
            "select k from KaryaAkhir as k where k.nim.nim = :nim")
            .setParameter("nim", mahasiswa.getNim())
            .getSingleResult();
        return ka;
    }
    
    public List<String> getAllMahasiswaYangIkutDiSelectedPeriode(
        Periode periode, String query) {
        Query q = em.createNativeQuery("select nim from mahasiswa where nim like ?")
            .setParameter(1, query + "%");
        List<String> nims = q.getResultList();
        return nims;
    }
}
