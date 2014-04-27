package ac.id.itb.ppl.lavender.bean;

import ac.id.itb.ppl.lavender.bean.remote.JadwalRemote;
import ac.id.itb.ppl.lavender.model.Jadwal;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class JadwalBean
 */
@Stateless
public class JadwalBean extends AbstractBean<Jadwal> implements JadwalRemote {

	@PersistenceContext(unitName="jadwalPU")
	private EntityManager em;
   
    public JadwalBean() {
       super(Jadwal.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
