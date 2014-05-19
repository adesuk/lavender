package ac.id.itb.ppl.lavender.bean;

import java.util.Date;
import java.util.List;

import ac.id.itb.ppl.lavender.bean.remote.KetersediaanDosenRemote;
import ac.id.itb.ppl.lavender.model.KetersediaanWaktuDosen;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.SlotWaktu;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 * Session Bean implementation class KetersediaanDosenBean
 */
@Stateless
@LocalBean
public class KetersediaanDosenBean extends AbstractBean<KetersediaanWaktuDosen> 
		implements KetersediaanDosenRemote {

	@PersistenceUnit(unitName="jadwalPU")
	private EntityManager em;
	
	public KetersediaanDosenBean() {
		super(KetersediaanWaktuDosen.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	// edbert
	public List<KetersediaanWaktuDosen> kwds() {
        Query q;
        Periode p = em.find(Periode.class, 1);
        q = em.createQuery(
            "select k from KetersediaanWaktuDosen as k where k.tanggalDsnSedia between :start and :end")
            .setParameter("start", p.getPeriodeAwal(), TemporalType.DATE)
            .setParameter("end", p.getPeriodeAkhir(), TemporalType.DATE);
        
        return q.getResultList();
    }
		
	// ade
	public List<SlotWaktu> findSlotWaktu(Date date, String inisialDosen) {
		List<SlotWaktu> slotWaktuList = em.createQuery("select s from KetersediaanWaktuDosen k, SlotWaktu s "
				+ "where k.tanggalDsnSedia=?1 and k.dosen.inisialDosen=?2 and "
				+ "k.slotWaktu.idSlot=s.idSlot")
				.setParameter(1, date)
				.setParameter(2, inisialDosen).getResultList();
		return slotWaktuList;
	}
	
	public List<KetersediaanWaktuDosen> findKetersediaanByDateAndDosen(Date date, String inisialDosen) {
		List<KetersediaanWaktuDosen> waktuList = em.createQuery(
				"select k from KetersediaanWaktuDosen k where k.tanggalDsnSedia=?1 and k.dosen.inisialDosen=?2")
				.setParameter(1, date)
				.setParameter(2, inisialDosen).getResultList();
		return waktuList;
	}
}
