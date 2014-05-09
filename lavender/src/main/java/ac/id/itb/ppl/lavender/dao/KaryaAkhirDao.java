package ac.id.itb.ppl.lavender.dao;

import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.Mahasiswa;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author edbert
 */
@Local
public interface KaryaAkhirDao {
    List<KaryaAkhir> findAll();
    
    KaryaAkhir findByOwner(Mahasiswa mahasiswa);
    
    List<KaryaAkhir> getToBeExecutedKaryaAkhirs(char tipeJadwal);
    
    List<KaryaAkhir> getAllMahasiswaYangAkanIkutSeminar(char tipeJadwal);
    
    List<KaryaAkhir> findKaryaAkhir(int tahunMasuk, String jenjang, String judul);
    
    KaryaAkhir update(KaryaAkhir karyaAkhir);
}
