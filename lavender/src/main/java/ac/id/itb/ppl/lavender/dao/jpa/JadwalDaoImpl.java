package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.model.Jadwal;
import ac.id.itb.ppl.lavender.model.Periode;
import java.math.BigDecimal;
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
public class JadwalDaoImpl extends JpaDao {
    public List<Jadwal> findJadwalByPeriode(Periode periode) {
        if (periode == null) {
            throw new NullPointerException("Periode tidak boleh null");
        }
        
        Query query = em.createQuery(
            "select j from Jadwal as j join fetch j.slotWaktu as s where j.idPeriode.idPeriode = :id")
            .setParameter("id", periode.getIdPeriode());
        List<Jadwal> result = query.getResultList();
        return result;
    }
    
    public Jadwal find(Integer id) {
        return em.find(Jadwal.class, id);
    }
    
    public void save(Jadwal jadwal) {
        Query query = em.createNativeQuery("select max(id_jadwal) from jadwal");
        List<BigDecimal> temp = query.getResultList();
        jadwal.setIdJadwal(Integer.valueOf(temp.get(0).toString()) + 1);
        //jadwal.setIdJadwal(1);
        em.persist(jadwal);
    }
}
