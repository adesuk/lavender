package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.SlotWaktuDao;
import ac.id.itb.ppl.lavender.model.SlotWaktu;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author Edbert
 */
@Stateless
public class SlotWaktuDaoImpl extends JpaDao implements SlotWaktuDao {
    @Override
    public List<SlotWaktu> findAll() {
        return em.createQuery("select s from SlotWaktu as s").getResultList();
    }
    
    @Override
    public SlotWaktu find(Integer id) {
        return em.find(SlotWaktu.class, id);
    }
}
