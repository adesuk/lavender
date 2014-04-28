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
    
    public List<KaryaAkhir> getToBeExecutedKaryaAkhirs(char tipeJadwal) {
        Query query = em.createQuery(
            "select ka from KaryaAkhir as ka "
            + "join fetch ka.mahasiswa as mhs "
            + "join fetch ka.dosenPembimbing as pemb "
            + "where ka.statusKa = :status")
            .setParameter("status", tipeJadwal - 1);
        List<KaryaAkhir> karyaAkhirs = query.getResultList();
        return karyaAkhirs;
    }
    
    public List<String> getAllMahasiswaYangIkutDiSelectedPeriode(
        Periode periode, String query) {
        Query q = em.createNativeQuery("select nim from mahasiswa where nim like ?")
            .setParameter(1, query + "%");
        List<String> nims = q.getResultList();
        return nims;
    }
}
