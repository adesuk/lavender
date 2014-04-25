package ac.id.itb.ppl.lavender.dao;

import ac.id.itb.ppl.lavender.model.Periode;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author edbert
 */
@Remote
public interface PeriodeDao {
    Periode find(Integer id);
    List<Periode> findAll();
}
