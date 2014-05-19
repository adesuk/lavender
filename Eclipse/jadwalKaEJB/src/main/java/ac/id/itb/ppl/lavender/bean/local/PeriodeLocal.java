package ac.id.itb.ppl.lavender.bean.local;

import ac.id.itb.ppl.lavender.model.Periode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author edbert
 */
@Local
public interface PeriodeLocal {
    
    Periode find(Integer id);
    
    List<Periode> findAll();
    
    List<Periode> findByKeyword(String keyword);
    
    void save(Periode periode);
    
    Periode update(Periode periode);
    
    void delete(Periode periode);
    
    void changeGenerateStatusInProgress(Periode periode);
    
    void changeGenerateStatusDone(Periode periode);
    
    char findStatusJadwal(Periode periode);
    
    //List<Periode> findUnfinishedPeriodes();
}
