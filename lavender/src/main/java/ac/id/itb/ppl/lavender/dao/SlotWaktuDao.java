package ac.id.itb.ppl.lavender.dao;

import ac.id.itb.ppl.lavender.model.SlotWaktu;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author edbert
 */
@Local
public interface SlotWaktuDao {
    List<SlotWaktu> findAll();
    
    SlotWaktu find(Integer id);
}
