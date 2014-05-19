package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.JadwalDao;
import ac.id.itb.ppl.lavender.model.*;
import java.math.BigDecimal;
import java.util.*;
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
        //em.getTransaction().begin();
        em.getEntityManagerFactory().getCache().evictAll();
        Query query = em.createQuery(
            "select j from Jadwal as j join fetch j.slotWaktu as s where j.idPeriode.idPeriode = :id and j.generateDate = :versi")
            .setParameter("id", periode.getIdPeriode())
            .setParameter("versi", versi, TemporalType.TIMESTAMP);
        List<Jadwal> result = query.getResultList();
        //em.getTransaction().commit();
        return result;
    }
    
    @Override
    public Jadwal find(Integer id) {
        return em.find(Jadwal.class, id);
    }
    
     @Override
    public Jadwal findComplete(Integer id) {
        Query query = em.createQuery(
            "select j from Jadwal as j join fetch j.slotWaktu as s where j.idJadwal = :id")
            .setParameter("id", id);
        Jadwal jadwal = (Jadwal) query.getSingleResult();
//        for (Dosen d : jadwal.getDosenPenguji()) {
//            System.out.println(">>> " + d.getNamaDosen() + " <<<");
//        }
        
        return jadwal;
    }
    
    @Override
    public List<Date> findJadwalVersions(Periode periode) {
        Query query = em.createNativeQuery(
            "select distinct generate_date from jadwal where id_periode = ? order by generate_date desc")
            .setParameter(1, periode.getIdPeriode());
        List<java.sql.Timestamp> temp = query.getResultList();
        List<Date> versions = new ArrayList<Date>(temp.size());
        for (java.sql.Timestamp t : temp) {
            Date version = new Date(t.getTime());
            versions.add(version);
        }
        return versions;
    }
    
    /*@Override
    public List<Jadwal> findJadwalByPeriode(Periode periode) {
        List<Date> versions = findJadwalVersions(periode);
        
        if (versions == null || versions.isEmpty()) {
            return new ArrayList<Jadwal>();
        }
        
        return findJadwalByPeriodeAndVersi(periode, versions.get(0));
    }*/
    
    @Override
    public void save(Jadwal jadwal) {
        Query query = em.createNativeQuery("select max(id_jadwal) from jadwal");
        List<BigDecimal> temp = query.getResultList();
        if (temp == null || temp.isEmpty() || temp.get(0) == null) {
            jadwal.setIdJadwal(1);
        } else {
            jadwal.setIdJadwal(Integer.valueOf(temp.get(0).toString()) + 1);
            
        }
        List<Dosen> pengujis = jadwal.getDosenPenguji();
        em.persist(jadwal);
        if (pengujis == null) return;
        int x = 1;
        for (Dosen p : pengujis) {
            query = em.createNativeQuery(
                "insert into menguji (id_jadwal, inisial_dosen, status_penguji) values (?, ?, ?)")
                .setParameter(1, jadwal.getIdJadwal())
                .setParameter(2, (String) p.getInisialDosen())
                .setParameter(3, x++);
            query.executeUpdate();
        }
    }
    
    @Override
    public void saveGeneratedJadwal(List<Jadwal> jadwal) {
        if (jadwal == null || jadwal.isEmpty()) {
            return;
        }
        
        Query query = em.createNativeQuery("select max(id_jadwal) from jadwal");
        List<BigDecimal> temp = query.getResultList();
        Integer id = temp == null || temp.isEmpty() || temp.get(0) == null 
            ? 1 
            :Integer.valueOf(temp.get(0).toString()) + 1;
        int x;
        for (Jadwal jad : jadwal) {
            List<Dosen> pengujis = jad.getDosenPenguji();
            em.persist(jadwal);
            if (pengujis == null) return;
            x = 1;
            for (Dosen p : pengujis) {
                query = em.createNativeQuery(
                    "insert into menguji (id_jadwal, inisial_dosen, status_penguji) values (?, ?, ?)")
                    .setParameter(1, id++)
                    .setParameter(2, (String) p.getInisialDosen())
                    .setParameter(3, x++);
                query.executeUpdate();
            }
        }
    }
    
    
    @Override
    public Jadwal update(Jadwal jadwal) {
        Query query = em.createNativeQuery(
            "select id_jadwal, inisial_dosen from menguji where id_jadwal = ?")
            .setParameter(1, jadwal.getIdJadwal());
        List<Object[]> temp = query.getResultList();
        for (Object[] o : temp) {
            query = em.createNativeQuery(
                "delete from menguji where id_jadwal = ? and inisial_dosen = ?")
                .setParameter(1, jadwal.getIdJadwal())
                .setParameter(2, (String) o[1]);
            query.executeUpdate();
        }
        Jadwal ja = em.merge(jadwal);
        for (Dosen p : jadwal.getDosenPenguji()) {
            query = em.createNativeQuery(
                "insert into menguji (id_jadwal, inisial_dosen) values (?, ?)")
                .setParameter(1, jadwal.getIdJadwal())
                .setParameter(2, (String) p.getInisialDosen());
            query.executeUpdate();
        }
        
        return ja;
    }
    
    @Override
    public boolean delete(Jadwal jadwal) {
        if (jadwal == null) {
            return false;
        }
        
        Query query = em.createNativeQuery("delete from menguji where id_jadwal = ?")
            .setParameter(1, jadwal.getIdJadwal());
        query.executeUpdate();
        
        query = em.createNativeQuery("delete from jadwal where id_jadwal = ?")
            .setParameter(1, jadwal.getIdJadwal());
        return query.executeUpdate() != 0;
    }
}
