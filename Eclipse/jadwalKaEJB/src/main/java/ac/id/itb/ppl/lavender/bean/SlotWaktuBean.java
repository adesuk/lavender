package ac.id.itb.ppl.lavender.bean;

import java.util.List;

import ac.id.itb.ppl.lavender.bean.local.SlotWaktuLocal;
import ac.id.itb.ppl.lavender.bean.remote.SlotWaktuRemote;
import ac.id.itb.ppl.lavender.model.SlotWaktu;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class SlotWaktuBean
 */
@Stateless
@LocalBean
public class SlotWaktuBean extends AbstractBean<SlotWaktu> implements SlotWaktuRemote, SlotWaktuLocal {

	@PersistenceContext(unitName="jadwalPU")
	private EntityManager em;
	
    public SlotWaktuBean() {
       super(SlotWaktu.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	 @Override
	    public List<SlotWaktu> findAll() {
	        return em.createQuery("select s from SlotWaktu as s").getResultList();
	    }
	    
	  @Override
	  public SlotWaktu find(Integer id) {
	        return em.find(SlotWaktu.class, id);
	    }
}
