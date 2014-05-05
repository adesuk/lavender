package ac.id.itb.ppl.lavender.dao;

import ac.id.itb.ppl.lavender.model.Periode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author edbert
 */
@Local
public interface PeriodeDao {
    Periode find(Integer id);
    
    List<Periode> findAll();
    
    List<Periode> findByKeyword(String keyword);
    
    void save(Periode periode);
    
    Periode update(Periode periode);
    
    void delete(Periode periode);
}
