package ac.id.itb.ppl.lavender.bean.local;

import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.Ruangan;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Edbert
 */
@Local
public interface RuanganLocal {
    List<Ruangan> findAll();
    
    Ruangan find(String id);
    
    List<Ruangan> findRuanganYangDipakai(Periode periode);
            
    List<Ruangan> findRuanganDanKetersediaanRuangans(Periode periode);
}
