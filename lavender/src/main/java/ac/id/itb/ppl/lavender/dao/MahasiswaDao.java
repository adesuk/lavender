package ac.id.itb.ppl.lavender.dao;

import ac.id.itb.ppl.lavender.model.Mahasiswa;
import javax.ejb.Local;

/**
 *
 * @author edbert
 */
@Local
public interface MahasiswaDao {
    Mahasiswa find(String nim);
}
