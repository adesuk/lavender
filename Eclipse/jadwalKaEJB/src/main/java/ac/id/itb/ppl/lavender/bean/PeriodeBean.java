package ac.id.itb.ppl.lavender.bean;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ac.id.itb.ppl.lavender.bean.remote.PeriodeRemote;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.util.PeriodeFormat;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class PeriodeBean
 */
@Stateless
@LocalBean
public class PeriodeBean extends AbstractBean<Periode> implements PeriodeRemote {

	@PersistenceContext(unitName="jadwalPU")
	private EntityManager em;
       
    public PeriodeBean() {
        super(Periode.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

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
}
