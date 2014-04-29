package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.model.KetersediaanWaktuDosen;
import ac.id.itb.ppl.lavender.model.Periode;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author edbert
 */
@Stateless
@LocalBean
public class KwdDaoImpl extends JpaDao {
    public List<KetersediaanWaktuDosen> kwds() {
        Query q;
        Periode p = em.find(Periode.class, 1);
        q = em.createQuery(
            "select k from KetersediaanWaktuDosen as k where k.tanggalDsnSedia between :start and :end")
            .setParameter("start", p.getPeriodeAwal(), TemporalType.DATE)
            .setParameter("end", p.getPeriodeAkhir(), TemporalType.DATE);
        
        return q.getResultList();
    }
}
