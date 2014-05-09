package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.JadwalDao;
import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.Jadwal;
import ac.id.itb.ppl.lavender.model.Periode;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Edbert
 */
@Stateless
public class JadwalDaoImpl extends JpaDao implements JadwalDao {
    @Override
    public List<Jadwal> findJadwalByPeriodeAndVersi(Periode periode, Date versi) {
        if (periode == null) {
            throw new NullPointerException("Periode tidak boleh null");
        }
        
        Query query = em.createQuery(
            "select j from Jadwal as j join fetch j.slotWaktu as s where j.idPeriode.idPeriode = :id and j.generateDate = :versi")
            .setParameter("id", periode.getIdPeriode())
            .setParameter("versi", versi, TemporalType.TIMESTAMP);
        List<Jadwal> result = query.getResultList();
        return result;
    }
    
    @Override
    public Jadwal find(Integer id) {
        return em.find(Jadwal.class, id);
    }
    
    @Override
    public List<Date> findJadwalVersions(Periode periode) {
        Query query = em.createNativeQuery(
            "select distinct generate_date from jadwal where id_periode = ?")
            .setParameter(1, periode.getIdPeriode());
        List<java.sql.Timestamp> temp = query.getResultList();
        List<Date> versions = new ArrayList<Date>(temp.size());
        for (java.sql.Timestamp t : temp) {
            Date version = new Date(t.getTime());
            versions.add(version);
        }
        return versions;
    }
    
    @Override
    public void save(Jadwal jadwal) {
        Query query = em.createNativeQuery("select max(id_jadwal) from jadwal");
        List<BigDecimal> temp = query.getResultList();
        System.out.println(temp);
        if (temp == null || temp.isEmpty() || temp.get(0) == null) {
            jadwal.setIdJadwal(1);
        } else {
            jadwal.setIdJadwal(Integer.valueOf(temp.get(0).toString()) + 1);
        }
        //jadwal.setIdJadwal(1);
        em.persist(jadwal);
    }
    
    @Override
    public Jadwal update(Jadwal jadwal) {
        return em.merge(jadwal);
    }
}
