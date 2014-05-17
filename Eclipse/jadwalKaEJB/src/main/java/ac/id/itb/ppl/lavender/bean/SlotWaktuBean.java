package ac.id.itb.ppl.lavender.bean;

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
public class SlotWaktuBean extends AbstractBean<SlotWaktu> implements SlotWaktuRemote {

	@PersistenceContext(unitName="jadwalPU")
	private EntityManager em;
	
    public SlotWaktuBean() {
       super(SlotWaktu.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
