package ac.id.itb.ppl.lavender.dao;

import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.Ruangan;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Edbert
 */
@Local
public interface RuanganDao {
    List<Ruangan> findAll();
    
    Ruangan find(String id);
    
    List<Ruangan> findRuanganYangDipakai(Periode periode);
            
    List<Ruangan> findRuanganDanKetersediaanRuangans(Periode periode);
}
