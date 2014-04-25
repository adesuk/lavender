package ac.id.itb.ppl.group4.lavender.dao;

import ac.id.itb.ppl.group4.lavender.model.*;
import ac.id.itb.ppl.group4.lavender.nmodel.KetersediaanRuangan;
import java.util.List;

/**
 *
 * @author Edbert
 */
public interface RuanganDao extends Dao<Ruangan, String> {
    List<KetersediaanRuangan> getKetersediaanRuangans();
}
