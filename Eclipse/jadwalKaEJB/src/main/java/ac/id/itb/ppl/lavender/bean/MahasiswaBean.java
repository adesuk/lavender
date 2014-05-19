package ac.id.itb.ppl.lavender.bean;

import ac.id.itb.ppl.lavender.bean.local.MahasiswaLocal;
import ac.id.itb.ppl.lavender.model.KetersediaanWaktuDosen;
import ac.id.itb.ppl.lavender.model.Mahasiswa;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

/**
 *
 * @author Edbert
 */
@Stateless
public class MahasiswaBean extends AbstractBean<Mahasiswa> implements MahasiswaLocal {
    
	@PersistenceUnit(unitName="jadwalPU")
	private EntityManager em;
	
	public MahasiswaBean() {
		super(Mahasiswa.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
		
	@Override
    public Mahasiswa find(String nim) {
        return find(nim);
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
