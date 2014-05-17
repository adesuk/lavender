package ac.id.itb.ppl.lavender.bean;

import java.util.Date;
import java.util.List;

import ac.id.itb.ppl.lavender.bean.remote.KetersediaanDosenRemote;
import ac.id.itb.ppl.lavender.model.KetersediaanWaktuDosen;
import ac.id.itb.ppl.lavender.model.SlotWaktu;

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

	public List<SlotWaktu> findSlotWaktu(Date date, String inisialDosen) {
		List<SlotWaktu> slotWaktuList = em.createQuery("select s from KetersediaanWaktuDosen k, SlotWaktu s "
				+ "where k.tanggalDsnSedia=?1 and k.dosen.inisialDosen=?2 and "
				+ "k.slotWaktu.idSlot=s.idSlot")
				.setParameter(1, date)
				.setParameter(2, inisialDosen).getResultList();
		return slotWaktuList;
	}
}
