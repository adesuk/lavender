package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.MahasiswaDao;
import ac.id.itb.ppl.lavender.model.Mahasiswa;
import java.util.List;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 *
 * @author Edbert
 */
@Stateless
public class MahasiswaDaoImpl extends JpaDao implements MahasiswaDao {
    @Override
    public Mahasiswa find(String nim) {
        return em.find(Mahasiswa.class, nim);
    }
    
    @Override
    public List<Mahasiswa> getAllMahasiswaYangIkutDiSelectedPeriode(char tipeJadwal) {
        List<Mahasiswa> mhss = em.createQuery(
            "select m from Mahasiswa as m inner join fetch m.karyaAkhirList as k on k.statusKa = :status")
            .setParameter("status", tipeJadwal - 1) // dikurangin sama satu karena kita pengen ambil yang belum ikut eksekusi di tipe jadwal
            .getResultList();
        return mhss;
    }
}
