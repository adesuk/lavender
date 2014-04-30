package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.model.SlotWaktu;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Edbert
 */
@Stateless
@LocalBean
public class SlotWaktuDaoImpl extends JpaDao {
    public List<SlotWaktu> findAll() {
        return em.createQuery("select s from SlotWaktu as s").getResultList();
    }
    
    public SlotWaktu find(Integer id) {
        return em.find(SlotWaktu.class, id);
    }
}
