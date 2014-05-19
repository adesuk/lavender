package ac.id.itb.ppl.lavender.dao;

import ac.id.itb.ppl.lavender.model.Jadwal;
import ac.id.itb.ppl.lavender.model.Periode;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author edbert
 */
@Local
public interface JadwalDao {
    List<Jadwal> findJadwalByPeriodeAndVersi(Periode periode, Date versi);
    
    Jadwal find(Integer id);
    
    Jadwal findComplete(Integer id);
    
    List<Date> findJadwalVersions(Periode periode);
    
    //List<Jadwal> findJadwalByPeriode(Periode periode);
    
    void save(Jadwal jadwal);
    
    void saveGeneratedJadwal(List<Jadwal> jadwal);
    
    Jadwal update(Jadwal jadwal);
    
    boolean delete(Jadwal jadwal);
}
