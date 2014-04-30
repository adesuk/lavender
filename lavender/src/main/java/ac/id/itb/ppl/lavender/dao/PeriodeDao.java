package ac.id.itb.ppl.lavender.dao;

import ac.id.itb.ppl.lavender.model.Periode;
import java.util.List;

/**
 *
 * @author edbert
 */
public interface PeriodeDao {
    Periode find(Integer id);
    List<Periode> findAll();
}
