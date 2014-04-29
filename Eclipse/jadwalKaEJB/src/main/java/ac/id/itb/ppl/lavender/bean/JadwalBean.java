package ac.id.itb.ppl.lavender.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ac.id.itb.ppl.lavender.bean.remote.JadwalRemote;
import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.Jadwal;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.view.JadwalView;
import ac.id.itb.ppl.lavender.util.Time;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class JadwalBean
 */
@Stateless
@LocalBean
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

	public List<Jadwal> searchJadwal(Periode p) {
		return em.createQuery("select j from Jadwal j, Periode p where p.idPeriode=j.periode.idPeriode "
				+ "and p.idPeriode=?")
				.setParameter(1, p.getIdPeriode()).getResultList();	
	}
	
	public List<JadwalView> findJadwalByPeriode(long idPeriode) {
		List<Jadwal> listJadwal = em.createQuery("SELECT j FROM Jadwal j where j.periode.idPeriode=?1")
				.setParameter(1, idPeriode).getResultList();
		List<JadwalView> listJadwalView = new ArrayList<JadwalView>();
		
		for (Jadwal jadwal : listJadwal) {
			
			Calendar cal = Calendar.getInstance();
			cal.setTime(jadwal.getTanggal());
			//dosen pembimbing
			String pembimbing1 = "";
			String pembimbing2 = "";
			List<Dosen> pembimbing = jadwal.getKaryaAkhir().getDosensPembimbing();
			if (pembimbing.size() == 1) {
				pembimbing1 = pembimbing.get(0).getNamaDosen(); // tambahkan gelar
			}
			else if (pembimbing.size() == 2){
				pembimbing1 = pembimbing.get(0).getNamaDosen();
				pembimbing2 = pembimbing.get(1).getNamaDosen();
			}
			//dosen penguji
			String penguji1 = "";
			String penguji2 = "";			
			List<Dosen> penguji = jadwal.getDosensPenguji();
			if (penguji.size() == 1) {
				penguji1 = penguji.get(0).getNamaDosen();
			}
			else if (penguji.size() == 2) {
				penguji1 = penguji.get(0).getNamaDosen();
				penguji2 = penguji.get(1).getNamaDosen();
			}
			JadwalView jd = new JadwalView(jadwal.getIdJadwal(), 
					Time.getDayName(cal.get(Calendar.DAY_OF_WEEK)), 
					"09.00-10.00", jadwal.getKaryaAkhir().getMahasiswa().getNim(),
					jadwal.getKaryaAkhir().getMahasiswa().getNamaMhs(), 
					jadwal.getKaryaAkhir().getJudulKa(), pembimbing1, pembimbing2, 
					penguji1, penguji2, jadwal.getRuangan().getNamaRuangan());
			listJadwalView.add(jd);
		}
		
		return listJadwalView;
	}
}
