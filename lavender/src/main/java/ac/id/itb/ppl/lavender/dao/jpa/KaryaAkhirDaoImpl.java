package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.KaryaAkhirDao;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.Mahasiswa;
import ac.id.itb.ppl.lavender.util.AllConstants;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author Edbert
 */
@Stateless
public class KaryaAkhirDaoImpl extends JpaDao implements KaryaAkhirDao {
    @Override
    public List<KaryaAkhir> findAll() {
        return null;
    }
    
    @Override
    public KaryaAkhir findByOwner(Mahasiswa mahasiswa) {
        KaryaAkhir ka = (KaryaAkhir) em.createQuery(
            "select k from KaryaAkhir as k join fetch k.mahasiswa as m join fetch k.topik as t where k.mahasiswa.nim = :nim")
            .setParameter("nim", mahasiswa.getNim())
            .getSingleResult();
        return ka;
    }
    
    @Override
    public List<KaryaAkhir> findToBeExecutedKaryaAkhirs(char tipeJadwal) {
        Query query = em.createQuery(
            "select ka from KaryaAkhir as ka " +
            "join fetch ka.mahasiswa as mhs " +
            "join fetch ka.dosenPembimbing as pemb " +
            "where ka.statusKa = :status " +
            "order by ka.idKa")
            .setParameter("status", tipeJadwal - 1);
        List<KaryaAkhir> karyaAkhirs = query.getResultList();
        return karyaAkhirs;
    }
    
    @Override
    public List<KaryaAkhir> getAllMahasiswaYangAkanIkutSeminar(char tipeJadwal) {
        if (tipeJadwal != AllConstants.SEMINAR_TA_1 || tipeJadwal != AllConstants.SEMINAR_TESIS) {
            return new ArrayList<KaryaAkhir>(0);
        }
        
        List<KaryaAkhir> karyaAkhirs = em.createQuery(
            "select k from KaryaAkhir as k join fetch k.mahasiswa as m join fetch k.topik as t where k.statusKa = :status")
            .setParameter("status", tipeJadwal - 1) // dikurangin sama satu karena kita pengen ambil yang belum ikut seminar ta 1 atau seminar tesis
            .getResultList();
        return karyaAkhirs;
    }
    
    @Override
    public List<KaryaAkhir> findKaryaAkhir(int tahunMasuk, String jenjang, String judul) {
        String nim = "135";
        if (jenjang.equals("S1")) {
            //nim = "135";
        } else if (jenjang.equals("S2")) {
            nim = "235";
        }
        
        nim += (tahunMasuk + "").substring(2, 4);
        
        System.out.println(">>> NIM: " + nim + " <<<");
                
        Query query = em.createQuery(
            "select k from KaryaAkhir k " +
            "join fetch k.mahasiswa m " +
            "left join fetch k.topik t " +
            "where m.nim like :nim " +
            "and lower(k.judulKa) like lower(:judul) " +
            "and k.statusKa <> 3 and k.statusKa <> 5 order by m.nim")
            .setParameter("nim", nim + "%")
            .setParameter("judul", "%" + judul + "%");
        List<KaryaAkhir> karyaAkhirs = query.getResultList();
        return karyaAkhirs;
    }
    
    @Override
    public KaryaAkhir update(KaryaAkhir karyaAkhir) {
        return em.merge(karyaAkhir);
    }
}
