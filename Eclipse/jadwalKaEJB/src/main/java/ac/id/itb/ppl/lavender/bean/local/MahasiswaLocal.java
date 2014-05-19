package ac.id.itb.ppl.lavender.bean.local;

import ac.id.itb.ppl.lavender.model.Mahasiswa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author edbert
 */
@Local
public interface MahasiswaLocal {
    Mahasiswa find(String nim);
    
    List<Mahasiswa> getAllMahasiswaYangIkutDiSelectedPeriode(char tipeJadwal);
}
