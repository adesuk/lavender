package ac.id.itb.ppl.lavender.dao;

import ac.id.itb.ppl.lavender.model.Topik;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author edbert
 */
@Local
public interface TopikDao {
    List<Topik> findAll();
    
    Topik find(Integer id);
    
    List<Topik> findAllWithDosens();
}
