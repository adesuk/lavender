package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.JadwalKuliahDao;
import ac.id.itb.ppl.lavender.model.JadwalKuliah;
import ac.id.itb.ppl.lavender.model.Periode;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.Query;

/**
 *
 * @author edbert
 */
@Stateless
public class JadwalKuliahDaoImpl extends JpaDao
implements JadwalKuliahDao {
    @Override
    public List<JadwalKuliah> findByPeriode(Periode periode) {
        Query query = em.createNativeQuery(
            new StringBuilder()
                .append("select id_jadwal_kuliah, kd_ruangan, id_periode_kuliah, ")
                .append("kode_mata_kuliah, hari, waktu_masuk, waktu_keluar ")
                .append("from jadwal_kuliah jk, ")
                .append("(select pkul.id_periode_kuliah as pid ")
                .append("from periode_kuliah pkul, periode p ")
                .append("where p.periode_awal <= pkul.waktu_akhir_kuliah ")
                .append("and p.periode_akhir >= pkul.waktu_mulai_kuliah ")
                .append("and p.id_periode = ?) v1 ")
                .append("where jk.id_periode_kuliah in(v1.pid) ")
                .append("order by hari, waktu_masuk")
                .toString(), JadwalKuliah.class)
            .setParameter(1, periode.getIdPeriode());
        
        return query.getResultList();
    }
}
