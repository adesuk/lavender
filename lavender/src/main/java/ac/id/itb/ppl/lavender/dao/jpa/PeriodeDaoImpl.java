package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.util.AllConstants;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.*;

/**
 *
 * @author edbert
 */
@Stateless
public class PeriodeDaoImpl extends JpaDao implements PeriodeDao {
    
    @Override
    public Periode find(Integer id) {
        return em.find(Periode.class, id);
    }
    
    @Override
    public List<Periode> findAll() {
        Query query = em.createQuery("select p from Periode as p order by p.periodeAwal desc");
        List<Periode> periodes = query.getResultList();
        return periodes;
    }
    
    @Override
    public List<Periode> findByKeyword(String keyword) {
        Query query = em.createQuery(
            "select p from Periode as p where lower(p.namaPeriode) like lower(:keyword) order by p.periodeAwal desc")
            .setParameter("keyword", keyword + "%");
        List<Periode> periodes = query.getResultList();
        return periodes;
    }
    
    @Override
    public void save(Periode periode) {
        Query query = em.createNativeQuery("select max(id_periode) from periode");
        List<BigDecimal> temp = query.getResultList();
        periode.setIdPeriode(Integer.valueOf(temp.get(0).toString()) + 1);
        periode.setStatusJadwal(AllConstants.BELUM_DIGENERATE);
        em.persist(periode);
    }
    
    @Override
    public Periode update(Periode periode) {
        return em.merge(periode);
    }
    
    @Override
    public void delete(Periode periode) {
        Periode p = em.merge(periode);
        em.remove(p);
    }
    
    @Override
    public void changeGenerateStatusInProgress(Periode periode) {
        periode.setStatusJadwal(AllConstants.SEDANG_DIGENERATE);
        em.merge(periode);
    }
    
    @Override
    public void changeGenerateStatusDone(Periode periode) {
        periode.setStatusJadwal(AllConstants.SUDAH_DIGENERATE);
        em.merge(periode);
    }
    
    @Override
    public char findStatusJadwal(Periode periode) {
        Query query = em.createNativeQuery(
            "select status_jadwal from periode where id_periode = ?")
            .setParameter(1, periode.getIdPeriode());
        
        String result = (String) query.getSingleResult();
        
        return result.charAt(0);
    }
    
    /*@Override
    public List<Periode> findUnfinishedPeriodes() {
        Date now = new Date(System.currentTimeMillis());
        Query query = em.createQuery(
            "select p from Periode p where p.periodeAkhir >= :now order by p.periodeAkhir asc")
            .setParameter("now", now, TemporalType.TIMESTAMP);
        List<Periode> periodes = query.getResultList();
        return periodes;
    }*/
}
