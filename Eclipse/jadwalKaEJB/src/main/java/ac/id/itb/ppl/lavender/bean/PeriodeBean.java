package ac.id.itb.ppl.lavender.bean;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ac.id.itb.ppl.lavender.bean.local.PeriodeLocal;
import ac.id.itb.ppl.lavender.bean.remote.PeriodeRemote;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.util.AllConstants;
import ac.id.itb.ppl.lavender.util.PeriodeFormat;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 * Session Bean implementation class PeriodeBean
 */
@Stateless
@LocalBean
public class PeriodeBean extends AbstractBean<Periode> implements PeriodeRemote, PeriodeLocal {

	@PersistenceContext(unitName="jadwalPU")
	private EntityManager em;
       
    public PeriodeBean() {
        super(Periode.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	// ade
	public Periode lastPeriode() {
//		List<Periode> listPeriode = em.createQuery(
//				"select p from Periode p order by p.periodeAkhir desc")
//				.getResultList();
		
		Periode p = (Periode) 
				em.createQuery(
						"select p from Periode p order by p.periodeAkhir desc")
						.setMaxResults(1).getSingleResult();
		
		return p;
	}
	
	public Map<Date, String> getTanggalList(Periode periode) {
		Map<Date, String> mapDate = new LinkedHashMap<>();
	
		Calendar calAkhir = Calendar.getInstance();
		calAkhir.setTime(periode.getPeriodeAkhir());
		calAkhir.add(Calendar.DATE, 1);
		Calendar cal = Calendar.getInstance();
		cal.setTime(periode.getPeriodeAwal());
		while (!cal.equals(calAkhir)) {
			mapDate.put(cal.getTime(), PeriodeFormat.formatDate(cal.getTime()));
			cal.add(Calendar.DATE, 1);
		}
		
		return mapDate;
	}
	
	//edbert
	 @Override
	    public Periode find(Integer id) {
	        return em.find(Periode.class, id);
	    }
	    
	    @Override
	    public List<Periode> findAll() {
	        Query query = em.createQuery("select p from Periode as p order by p.periodeAwal desc");
	        List<Periode> periodes = query.getResultList();
	        return periodes;
	    }
	    
	    @Override
	    public List<Periode> findByKeyword(String keyword) {
	        Query query = em.createQuery(
	            "select p from Periode as p where lower(p.namaPeriode) like lower(:keyword) order by p.periodeAwal desc")
	            .setParameter("keyword", keyword + "%");
	        List<Periode> periodes = query.getResultList();
	        return periodes;
	    }
	    
	    @Override
	    public void save(Periode periode) {
	        Query query = em.createNativeQuery("select max(id_periode) from periode");
	        List<BigDecimal> temp = query.getResultList();
	        periode.setIdPeriode(Integer.valueOf(temp.get(0).toString()) + 1);
	        periode.setStatusJadwal(AllConstants.BELUM_DIGENERATE);
	        em.persist(periode);
	    }
	    
	    @Override
	    public Periode update(Periode periode) {
	        return em.merge(periode);
	    }
	    
	    @Override
	    public void delete(Periode periode) {
	        Periode p = em.merge(periode);
	        em.remove(p);
	    }
	    
	    @Override
	    public void changeGenerateStatusInProgress(Periode periode) {
	        periode.setStatusJadwal(AllConstants.SEDANG_DIGENERATE);
	        em.merge(periode);
	    }
	    
	    @Override
	    public void changeGenerateStatusDone(Periode periode) {
	        periode.setStatusJadwal(AllConstants.SUDAH_DIGENERATE);
	        em.merge(periode);
	    }
	    
	    @Override
	    public char findStatusJadwal(Periode periode) {
	        Query query = em.createNativeQuery(
	            "select status_jadwal from periode where id_periode = ?")
	            .setParameter(1, periode.getIdPeriode());
	        
	        String result = (String) query.getSingleResult();
	        
	        return result.charAt(0);
	    }
}
