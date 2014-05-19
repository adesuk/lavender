package ac.id.itb.ppl.lavender.bean.local;

import ac.id.itb.ppl.lavender.model.SlotWaktu;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author edbert
 */
@Local
public interface SlotWaktuLocal {
    List<SlotWaktu> findAll();
    
    SlotWaktu find(Integer id);
}
