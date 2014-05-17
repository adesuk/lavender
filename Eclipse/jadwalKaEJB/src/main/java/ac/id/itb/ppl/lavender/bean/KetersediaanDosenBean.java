package ac.id.itb.ppl.lavender.bean;

import java.util.List;

import ac.id.itb.ppl.lavender.bean.remote.KetersediaanDosenRemote;
import ac.id.itb.ppl.lavender.model.KetersediaanWaktuDosen;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

/**
 * Session Bean implementation class KetersediaanDosenBean
 */
@Stateless
@LocalBean
public class KetersediaanDosenBean extends AbstractBean<KetersediaanWaktuDosen> implements KetersediaanDosenRemote {

	@PersistenceUnit(unitName="jadwalPU")
	private EntityManager em;
	
	public KetersediaanDosenBean() {
		super(KetersediaanWaktuDosen.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}
