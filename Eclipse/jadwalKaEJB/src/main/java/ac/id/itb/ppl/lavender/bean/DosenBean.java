package ac.id.itb.ppl.lavender.bean;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import ac.id.itb.ppl.lavender.bean.remote.DosenRemote;
import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;

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

	public List<KaryaAkhir> getKaryaAkhir(String dosenID) {
		Dosen dosen = find(dosenID);
		List<KaryaAkhir> listKaryaAkhir = dosen.getKaryaAkhirList();
		for (KaryaAkhir ka : listKaryaAkhir) {
			System.out.println(ka.getJudulKa());
		}
		return listKaryaAkhir;
	}
	
}
