package ac.id.itb.ppl.lavender.bean;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import ac.id.itb.ppl.lavender.bean.local.JadwalKuliahLocal;
import ac.id.itb.ppl.lavender.model.JadwalKuliah;
import ac.id.itb.ppl.lavender.model.Periode;

/**
 *
 * @author edbert
 */
@Stateless
public class JadwalKuliahBean extends AbstractBean<JadwalKuliah> implements JadwalKuliahLocal {
    
	@PersistenceUnit(unitName="jadwalPU")
	private EntityManager em;
	
	public JadwalKuliahBean() {
		super(JadwalKuliah.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	
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
