package ac.id.itb.ppl.lavender.bean;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ac.id.itb.ppl.lavender.bean.remote.DosenRemote;
import ac.id.itb.ppl.lavender.model.Dosen;

/**
 * Session Bean implementation class DosenBean
 */
@Stateless
@LocalBean
public class DosenBean extends AbstractBean<Dosen> implements DosenRemote {
	
	@PersistenceContext(unitName="jadwalPU")
	private EntityManager em;
	
	public DosenBean() {
		super(Dosen.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

//	@Override
//	public List<Dosen> findAll() {
//		return em.createNamedQuery("Dosen.findAll").getResultList();
//	}

}
