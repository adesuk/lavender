package ac.id.itb.ppl.lavender.bean;

import ac.id.itb.ppl.lavender.bean.local.RuanganLocal;
import ac.id.itb.ppl.lavender.bean.local.SlotWaktuLocal;
import ac.id.itb.ppl.lavender.bean.local.JadwalKuliahLocal;
import ac.id.itb.ppl.lavender.model.*;

import java.math.BigDecimal;
import java.util.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.*;

/**
 *
 * @author Edbert
 */
@Stateless
public class RuanganBean extends AbstractBean<Ruangan> implements RuanganLocal { 
    
	@PersistenceContext(unitName="jadwalPU")
	private EntityManager em;
    
	@EJB RuanganLocal ruanganDao;
    @EJB SlotWaktuLocal slotWaktuDao;
    @EJB JadwalKuliahLocal jadwalKuliahDao;
	
    public RuanganBean() {
        super(Ruangan.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}
	    
    @Override
    public List<Ruangan> findAll() {
        Query query = em.createQuery(
            "select r from Ruangan r");
        List<Ruangan> ruangans = query.getResultList();
        return ruangans;
    }
    
    @Override
    public Ruangan find(String id) {
        return em.find(Ruangan.class, id);
    }
    
    @Override
    public List<Ruangan> findRuanganYangDipakai(Periode periode) {
        // select id_jadwal_kuliah, kd_ruangan, id_periode_kuliah,
        // kode_mata_kuliah, hari, waktu_masuk, waktu_keluar,
        // waktu_mulai_kuliah, waktu_akhir_kuliah
        // from jadwal_kuliah jk,
        // (select pkul.id_periode_kuliah as pid
        // from periode_kuliah pkul, periode p
        // where p.periode_awal <= pkul.waktu_akhir_kuliah
        // and p.periode_akhir >= pkul.waktu_mulai_kuliah
        // and p.id_periode = 1) v1,
        // periode_kuliah pku
        // where jk.id_periode_kuliah in(v1.pid)
        // and jk.id_periode_kuliah = pku.id_periode_kuliah
        // order by hari, waktu_masuk
        Query query = em.createNativeQuery(
            new StringBuilder()
                .append("select id_jadwal_kuliah, kd_ruangan, jk.id_periode_kuliah, ")
                .append("kode_mata_kuliah, hari, waktu_masuk, waktu_keluar, ")
                .append("waktu_mulai_kuliah, waktu_akhir_kuliah ")
                .append("from jadwal_kuliah jk, ")
                .append("(select pkul.id_periode_kuliah as pid ")
                .append("from periode_kuliah pkul, periode p ")
                .append("where p.periode_awal <= pkul.waktu_akhir_kuliah ")
                .append("and p.periode_akhir >= pkul.waktu_mulai_kuliah ")
                .append("and p.id_periode = ?) v1, ")
                .append("periode_kuliah pku ")
                .append("where jk.id_periode_kuliah in(v1.pid) ")
                .append("and jk.id_periode_kuliah = pku.id_periode_kuliah ")
                .append("order by hari, waktu_masuk")
                .toString())
            .setParameter(1, periode.getIdPeriode());
        
        List<Object[]> result = query.getResultList();
        List<Ruangan> ruangans = new ArrayList<Ruangan>();
        Map<String, Ruangan> ruanganMap =
            new HashMap<String, Ruangan>();
        Ruangan ruangan;
        JadwalKuliah jadwalKuliah;
        PeriodeKuliah pkul;
        
        for (Object[] cols : result) {
            if ((ruangan = ruanganMap.get((String) cols[1])) == null) {
                ruangan = new Ruangan((String) cols[1]);
                ruangan.setJadwalKuliahList(
                    new ArrayList<JadwalKuliah>());
                ruanganMap.put((String) cols[1], ruangan);
                ruangans.add(ruangan);
            }
            
            jadwalKuliah = new JadwalKuliah();
            jadwalKuliah.setIdJadwalKuliah(
                Integer.valueOf(((BigDecimal) cols[0]).toString())
            );
            jadwalKuliah.setKdRuangan(ruangan);
            pkul = new PeriodeKuliah(
                Integer.valueOf(
                    ((BigDecimal) cols[2]).toString()
                )
            );
            pkul.setWaktuAkhirKuliah((Date) cols[8]);
            pkul.setWaktuMulaiKuliah((Date) cols[7]);
            jadwalKuliah.setIdPeriodeKuliah(pkul);
            jadwalKuliah.setKodeMataKuliah((String) cols[3]);
            jadwalKuliah.setHari((String) cols[4]);
            jadwalKuliah.setWaktuMasuk((Date) cols[5]);
            jadwalKuliah.setWaktuKeluar((Date) cols[6]);
            ruangan.getJadwalKuliahList().add(jadwalKuliah);
        }
        
        return ruangans;
    }
    
    @Override
    public List<Ruangan> findRuanganDanKetersediaanRuangans(Periode periode) {
        List<Ruangan> ruanganTerpakaiList = findRuanganYangDipakai(periode);
        List<Ruangan> ruangans = ruanganDao.findAll();
        List<SlotWaktu> slotWaktus = slotWaktuDao.findAll();
        List<Calendar> dates = new ArrayList<Calendar>();
        Calendar c1 = new GregorianCalendar();
        c1.setTime(periode.getPeriodeAwal());
        Calendar c2 = new GregorianCalendar();
        c2.setTime(c1.getTime());
        dates.add(c2);
        while (c1.getTime().before(periode.getPeriodeAkhir())) {
            c1.add(Calendar.DATE, 1);
            if (c1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY &&
                c1.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
                c2 = new GregorianCalendar();
                c2.setTime(c1.getTime());
                dates.add(c2);
            }
        }
        
        //System.out.println(">>> Banyak hari: " + dates.size() + " <<<");
        
        Map<String, Ruangan> rydMap = new HashMap<String, Ruangan>(); // Ruangan yang Dipake Map
        for (Ruangan r : ruanganTerpakaiList) {
            rydMap.put(r.getKdRuangan(), r);
        }
//        Map<String, Ruangan> ruanganMap = new HashMap<String, Ruangan>();
        for (Ruangan r : ruangans) {
            r.setKetersediaanWaktuRuangan(new ArrayList<KetersediaanRuangan>());
//            ruanganMap.put(r.getKdRuangan(), r);
        }
        
        Ruangan temp;
        KetersediaanRuangan kr;
        List<JadwalKuliah> jks;
        
        List<Ruangan> asdf = new ArrayList<Ruangan>();
        
        // ngedapetin ruangan kosong
        for (Calendar day : dates) { // untuk setiap hari dalam periode eksekusi
            for (Ruangan r : ruangans) { // untuk setiap ruangan
            
                temp = rydMap.get(r.getKdRuangan());
                jks = new ArrayList<JadwalKuliah>(); // ambil dulu jadwal kuliah utk ruangan itu
                if (temp != null && temp.getJadwalKuliahList() != null &&
                    !temp.getJadwalKuliahList().isEmpty()) {
                    PeriodeKuliah pk = temp.getJadwalKuliahList().get(0).getIdPeriodeKuliah();
                    if (day.getTime().compareTo(pk.getWaktuMulaiKuliah()) >= 0 &&
                        day.getTime().compareTo(pk.getWaktuAkhirKuliah()) <= 0) {
                        // intinya klo ga di jadwal kuliah, dia kosong
                        
                        for (JadwalKuliah a : temp.getJadwalKuliahList()) {
                            if ((Integer.parseInt(a.getHari())) == day.get(Calendar.DAY_OF_WEEK)) {
                                jks.add(a);
                            }
                        }
                    }
                }
                
                boolean bentrok;
                for (SlotWaktu sw : slotWaktus) {
                    bentrok = false;
                    
                    for (JadwalKuliah a : jks) {
                        /*System.out.println(">>> jks size: " + jks.size() + " <<<");
                        System.out.printf(
                            ">>> %s < %s ~ awal[%s] && %s > %s ~ akhir[%s] <<<", 
                            sw.getJamAwal(), a.getWaktuKeluar(),
                            sw.getJamAkhir(), a.getWaktuMasuk(),
                            sw.getJamAwal().compareTo(a.getWaktuKeluar()),
                            sw.getJamAkhir().compareTo(a.getWaktuMasuk())
                        );*/
                        if (sw.getJamAwal().compareTo(a.getWaktuKeluar()) < 0 
                            && sw.getJamAkhir().compareTo(a.getWaktuMasuk()) > 0) {
                            //System.out.println(">>> Ada bentrok! " + sw.getIdSlot() + " <<<");
                            bentrok = true;
                            break;
                        }
                    }
                    
                    if (!bentrok) {
                        kr = new KetersediaanRuangan();
                        kr.setRuangan(r);
                        kr.setSlotWaktu(sw);
                        kr.setTanggalRuanganSedia(day.getTime());
                        r.getKetersediaanWaktuRuangan().add(kr);
                    }
                }
            }
        }
        
        return ruangans;
    }
}
