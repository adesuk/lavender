package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.model.Periode;
import java.math.BigDecimal;
import java.util.Calendar;
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
    public Periode find(Integer id) {
        return em.find(Periode.class, id);
    }
    
    public List<Periode> findAll() {
        Query query = em.createQuery("select p from Periode as p order by p.periodeAwal desc");
        List<Periode> periodes = query.getResultList();
        return periodes;
    }
    
    public List<Periode> findByKeyword(String keyword) {
        Query query = em.createQuery(
            "select p from Periode as p where lower(p.namaPeriode) like lower(:keyword) order by p.periodeAwal desc")
            .setParameter("keyword", keyword + "%");
        List<Periode> periodes = query.getResultList();
        return periodes;
    }
    
    public void save(Periode periode) {
        Query query = em.createNativeQuery("select max(id_periode) from periode");
        List<BigDecimal> temp = query.getResultList();
        periode.setIdPeriode(Integer.valueOf(temp.get(0).toString()) + 1);
        em.persist(periode);
    }
    
    public Periode update(Periode periode) {
        return em.merge(periode);
    }
    
    public void delete(Periode periode) {
        Periode p = em.merge(periode);
        em.remove(p);
    }
}
