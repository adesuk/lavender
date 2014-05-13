package ac.id.itb.ppl.lavender.bean;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ac.id.itb.ppl.lavender.bean.remote.TopikRemote;
import ac.id.itb.ppl.lavender.model.Topik;

/**
 * Session Bean implementation class TopikBean
 */
@Stateless
@LocalBean
public class TopikBean extends AbstractBean<Topik> implements TopikRemote {

	@PersistenceContext(unitName="jadwalPU")
	private EntityManager em;
	
	public TopikBean() {
		super(Topik.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	public List<String> findAllBidangTopik() {
		List<String> bidangs = em.createQuery("select t.bidang from Topik t group by t.bidang").getResultList();
		List<String> bs = new ArrayList<String>();
		for (String str : bidangs) {
			if (str!= null) {
				bs.add(str);
			}
		}
		return bs;
	}
	
	public List<Topik> findTopikByBidang(String bidang) {		
		
		List<Topik> topiks = em.createNamedQuery("Topik.findAll").getResultList();
		
		System.out.println("findTopikByBidang : "+ topiks.size());
		
		return topiks;
	}
	
	
	public List<Topik> findTopikByBidangAndDosen(String inisialDosen, String bidang) {		
		List<Topik> topiks = em.createQuery("select t from Topik t join t.dosenList d where d.inisialDosen=?1 "
				+ "and t.bidang=?2")
				.setParameter(1, inisialDosen)
				.setParameter(2, bidang).getResultList();
		
		return topiks;
	}

}
