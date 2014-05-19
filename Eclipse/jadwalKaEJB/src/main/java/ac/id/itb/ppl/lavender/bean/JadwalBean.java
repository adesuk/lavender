package ac.id.itb.ppl.lavender.bean;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ac.id.itb.ppl.lavender.bean.local.JadwalLocal;
import ac.id.itb.ppl.lavender.bean.remote.JadwalRemote;
import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.Jadwal;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.SlotWaktu;
import ac.id.itb.ppl.lavender.model.view.JadwalView;
import ac.id.itb.ppl.lavender.util.AllConstants;
import ac.id.itb.ppl.lavender.util.DateFormat;
import ac.id.itb.ppl.lavender.util.SlotWaktuFormat;
import ac.id.itb.ppl.lavender.util.StatusJadwal;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 * Session Bean implementation class JadwalBean
 */
@Stateless
@LocalBean
public class JadwalBean extends AbstractBean<Jadwal> implements JadwalRemote, JadwalLocal {

	@PersistenceContext(unitName="jadwalPU")
	private EntityManager em;
   
    public JadwalBean() {
       super(Jadwal.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	// edbert
	public List<Jadwal> findJadwalByPeriodeAndVersi(Periode periode, Date versi) {
        if (periode == null) {
            throw new NullPointerException("Periode tidak boleh null");
        }
        //em.getTransaction().begin();
        em.getEntityManagerFactory().getCache().evictAll();
        Query query = em.createQuery(
            "select j from Jadwal as j join fetch j.slotWaktu as s where j.idPeriode.idPeriode = :id and j.generateDate = :versi")
            .setParameter("id", periode.getIdPeriode())
            .setParameter("versi", versi, TemporalType.TIMESTAMP);
        List<Jadwal> result = query.getResultList();
        //em.getTransaction().commit();
        return result;
    }
	
	 public List<Date> findJadwalVersions(Periode periode) {
	        Query query = em.createNativeQuery(
	            "select distinct generate_date from jadwal where id_periode = ? order by generate_date desc")
	            .setParameter(1, periode.getIdPeriode());
	        List<java.sql.Timestamp> temp = query.getResultList();
	        List<Date> versions = new ArrayList<Date>(temp.size());
	        for (java.sql.Timestamp t : temp) {
	            Date version = new Date(t.getTime());
	            versions.add(version);
	        }
	        return versions;
	    }

	    public void saveGeneratedJadwal(List<Jadwal> jadwal) {
	        if (jadwal == null || jadwal.isEmpty()) {
	            return;
	        }
	        
	        Query query = em.createNativeQuery("select max(id_jadwal) from jadwal");
	        List<BigDecimal> temp = query.getResultList();
	        Integer id = temp == null || temp.isEmpty() || temp.get(0) == null 
	            ? 1 
	            :Integer.valueOf(temp.get(0).toString()) + 1;
	        int x;
	        for (Jadwal jad : jadwal) {
	            List<Dosen> pengujis = jad.getDosenPenguji();
	            jad.setIdJadwal(id++);
	            em.persist(jad);
	            if (pengujis == null) return;
	            x = 1;
	            for (Dosen p : pengujis) {
	                query = em.createNativeQuery(
	                    "insert into menguji (id_jadwal, inisial_dosen, status_penguji) values (?, ?, ?)")
	                    .setParameter(1, jad.getIdJadwal())
	                    .setParameter(2, (String) p.getInisialDosen())
	                    .setParameter(3, x++);
	                query.executeUpdate();
	            }
	        }
	    }
	    
	    public Jadwal update(Jadwal jadwal) {
	        Query query = em.createNativeQuery(
	            "select id_jadwal, inisial_dosen from menguji where id_jadwal = ?")
	            .setParameter(1, jadwal.getIdJadwal());
	        List<Object[]> temp = query.getResultList();
	        for (Object[] o : temp) {
	            query = em.createNativeQuery(
	                "delete from menguji where id_jadwal = ? and inisial_dosen = ?")
	                .setParameter(1, jadwal.getIdJadwal())
	                .setParameter(2, (String) o[1]);
	            query.executeUpdate();
	        }
	        Jadwal ja = em.merge(jadwal);
	        for (Dosen p : jadwal.getDosenPenguji()) {
	            query = em.createNativeQuery(
	                "insert into menguji (id_jadwal, inisial_dosen) values (?, ?)")
	                .setParameter(1, jadwal.getIdJadwal())
	                .setParameter(2, (String) p.getInisialDosen());
	            query.executeUpdate();
	        }
	        
	        return ja;
	    }
	    
	    public boolean delete(Jadwal jadwal) {
	        if (jadwal == null) {
	            return false;
	        }
	        
	        Query query = em.createNativeQuery("delete from menguji where id_jadwal = ?")
	            .setParameter(1, jadwal.getIdJadwal());
	        query.executeUpdate();
	        
	        query = em.createNativeQuery("delete from jadwal where id_jadwal = ?")
	            .setParameter(1, jadwal.getIdJadwal());
	        return query.executeUpdate() != 0;
	    }
	 
	    public void save(Jadwal jadwal) {
	        Query query = em.createNativeQuery("select max(id_jadwal) from jadwal");
	        List<BigDecimal> temp = query.getResultList();
	        if (temp == null || temp.isEmpty() || temp.get(0) == null) {
	            jadwal.setIdJadwal(1);
	        } else {
	            jadwal.setIdJadwal(Integer.valueOf(temp.get(0).toString()) + 1);
	            
	        }
	        List<Dosen> pengujis = jadwal.getDosenPenguji();
	        em.persist(jadwal);
	        if (pengujis == null) return;
	        int x = 1;
	        for (Dosen p : pengujis) {
	            query = em.createNativeQuery(
	                "insert into menguji (id_jadwal, inisial_dosen, status_penguji) values (?, ?, ?)")
	                .setParameter(1, jadwal.getIdJadwal())
	                .setParameter(2, (String) p.getInisialDosen())
	                .setParameter(3, x++);
	            query.executeUpdate();
	        }
	    }
	    
	    public Jadwal findComplete(Integer id) {
	        Query query = em.createQuery(
	            "select j from Jadwal as j join fetch j.slotWaktu as s where j.idJadwal = :id")
	            .setParameter("id", id);
	        Jadwal jadwal = (Jadwal) query.getSingleResult();
//	        for (Dosen d : jadwal.getDosenPenguji()) {
//	            System.out.println(">>> " + d.getNamaDosen() + " <<<");
//	        }
	        
	        return jadwal;
	    }   
	    
	// ade
	public List<Jadwal> searchJadwal(Periode p) {
		return em.createQuery("select j from Jadwal j, Periode p where p.idPeriode=j.idPeriode.idPeriode "
				+ "and p.idPeriode=?")
				.setParameter(1, p.getIdPeriode()).getResultList();	
	}
	
	public List<JadwalView> findJadwalByPeriode(Integer idPeriode) {
		List<Jadwal> listJadwal = em.createQuery("SELECT j FROM Jadwal j where j.idPeriode.idPeriode=?1")
				.setParameter(1, idPeriode)
				.getResultList();
		return processJadwalView(listJadwal);
	}

	public List<JadwalView> findJadwalByPeriodeAndPelaksanaan(Integer idPeriode, int status) {
		if (status == AllConstants.BELUM_TERLAKSANA || status == AllConstants.SUDAH_TERLAKSANA) {
			List<Jadwal> listJadwal = em.createQuery("SELECT j FROM Jadwal j where j.idPeriode.idPeriode=?1 "
				+ "and j.statusPelaksanaan=?2")
				.setParameter(1, idPeriode)
				.setParameter(2, status)
				.getResultList();
			return processJadwalView(listJadwal);
		}
		else {
			return findJadwalByPeriode(idPeriode);
		}
	}
	
	private List<JadwalView> processJadwalView(List<Jadwal> listJadwal) {
		List<JadwalView> listJadwalView = new ArrayList<JadwalView>();
		
		for (Jadwal jadwal : listJadwal) {
			
			//dosen pembimbing
			String pembimbing1 = "";
			String pembimbing2 = "";
			List<Dosen> pembimbing = jadwal.getKaryaAkhir().getDosenPembimbing();
			if (pembimbing.size() == 1) {
				pembimbing1 = pembimbing.get(0).getInisialDosen();
			}
			else if (pembimbing.size() == 2){
				pembimbing1 = pembimbing.get(0).getInisialDosen();
				pembimbing2 = pembimbing.get(1).getInisialDosen();
			}
			//dosen penguji
			String penguji1 = "";
			String penguji2 = "";			
			List<Dosen> penguji = jadwal.getDosenPenguji();
			if (penguji.size() == 1) {
				penguji1 = penguji.get(0).getInisialDosen();
			}
			else if (penguji.size() == 2) {
				penguji1 = penguji.get(0).getInisialDosen();
				penguji2 = penguji.get(1).getInisialDosen();
			}
			JadwalView jd = new JadwalView(jadwal.getIdJadwal(), 
					DateFormat.getDateDesc(jadwal.getTanggal()), 
					SlotWaktuFormat.format(jadwal.getSlotWaktu()), 
					jadwal.getKaryaAkhir().getMahasiswa().getNim(),
					jadwal.getKaryaAkhir().getMahasiswa().getNamaMhs(), 
					jadwal.getKaryaAkhir().getJudulKa(), pembimbing1, pembimbing2, 
					penguji1, penguji2, jadwal.getRuangan().getNamaRuangan(),
					jadwal.getStatusPelaksanaan().intValue(), jadwal.getStatusHasilPelaksanaan().intValue());
			listJadwalView.add(jd);
		}
		
		return listJadwalView;
	}

	@Override
	public Jadwal find(Integer id) {
		return find(id);
	}
}
