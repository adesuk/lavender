package ac.id.itb.ppl.lavender.bean;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ac.id.itb.ppl.lavender.bean.local.DosenLocal;
import ac.id.itb.ppl.lavender.bean.remote.DosenRemote;
import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.KetersediaanWaktuDosen;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.SlotWaktu;
import ac.id.itb.ppl.lavender.model.Topik;

/**
 * Session Bean implementation class DosenBean
 */
@Stateless
@LocalBean
public class DosenBean extends AbstractBean<Dosen> implements DosenRemote, DosenLocal {
	
	@PersistenceContext(unitName="jadwalPU")
	private EntityManager em;
	
	public DosenBean() {
		super(Dosen.class);
	}
	
	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

	 /**
     * Get dosen-dosen beserta topik dan waktu ketersediaannya sesuai dengan
     * rentang waktu periode masa seminar/sidang TA/Tesis.
     * 
     * @param periode
     * @return 
     */
    public List<Dosen> findDosenWithTopikAndKetersediaan(Periode periode) {
        List<Dosen> dosens = new ArrayList<Dosen>(0);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        // Query yang diinginkan kek gini:
        // select d.inisial_dosen as inisial, d.nama_dosen as nama, k.tanggal_dsn_sedia as tgl, k.id_slot as slot
        // from dosen d
        // inner join ketersediaan_waktu_dosen k
        //  on d.inisial_dosen = k.inisial_dosen 
        //   and tanggal_dsn_sedia between to_date('10/02/2014', 'DD/MM/YYYY') and to_date('21/02/2014', 'DD/MM/YYYY')
        // order by d.inisial_dosen, tgl, k.id_slot

        Query query = em.createNativeQuery(
            new StringBuilder()
                .append("select d.*, k.tanggal_dsn_sedia as tgl, k.id_slot ")
                .append("from dosen d ")
                .append("inner join ketersediaan_waktu_dosen k ")
                .append("on d.inisial_dosen = k.inisial_dosen ")
                .append("and tanggal_dsn_sedia between ")
                .append("to_date(?, 'DD/MM/YYYY') and to_date(?, 'DD/MM/YYYY') ")
                .append("order by d.inisial_dosen, tgl, k.id_slot")
                .toString())
            .setParameter(1, sdf.format(periode.getPeriodeAwal()))
            .setParameter(2, sdf.format(periode.getPeriodeAkhir()));
        
        List<Object[]> result = query.getResultList();
        
        Map<String, Dosen> createdDosens = new HashMap<String, Dosen>();
        Iterator<Object[]> it = result.iterator();
        Object[] objs;
        Dosen dosen;
        KetersediaanWaktuDosen kwd;
        
        while (it.hasNext()) {
            objs = it.next();
            if ((dosen = createdDosens.get((String) objs[0])) == null) {
                dosen = new Dosen();
                dosen.setInisialDosen((String) objs[0]);
                dosen.setNamaDosen((String) objs[1]);
                dosen.setStatus((String) objs[2]);
                dosen.setGelarDepan((String) objs[3]);
                dosen.setGelarBelakang((String) objs[4]);
                dosen.setKetersediaanWaktuDosens(
                    new ArrayList<KetersediaanWaktuDosen>());
                createdDosens.put((String) objs[0], dosen);
                
                dosens.add(dosen);
            }
            
            kwd = new KetersediaanWaktuDosen();
            kwd.setDosen(dosen);
            kwd.setSlotWaktu(
                new SlotWaktu(
                    Integer.valueOf(
                        ((BigDecimal) objs[6]).toString()
                    )
                )
            );
            kwd.setTanggalDsnSedia((Date) objs[5]);
            dosen.getKetersediaanWaktuDosens().add(kwd);
        }
        
        String[] inisialDosens = new String[dosens.size()];
        for (int i = 0; i < inisialDosens.length; i++) {
            inisialDosens[i] = dosens.get(i).getInisialDosen();
        }
        
        // Query yg diinginkan kek gini:
        // select dosen.inisial_dosen as inisial, dosen.nama_dosen, topik.nama_topik
        // from dosen
        // left outer join reference
        //  on dosen.inisial_dosen = reference.inisial_dosen
        // left outer join topik
        //  on topik.id_topik = reference.id_topik
        // where dosen.inisial_dosen in ('BY', 'DPL', 'AAA', 'AM', 'DHW', 'FNA', 'HLL', 'CS', 'AP', 'BS', 'AI')
        query = em.createQuery("select distinct d from Dosen d left join fetch d.bidangKeahlian t where d.inisialDosen in :inisialDosens")
            .setParameter("inisialDosens", Arrays.asList(inisialDosens));
        
        List<Dosen> temp = query.getResultList();
        //System.out.println(">>> jumlah inisial dosen: " + temp.size() + " <<<");
        
        for (Dosen d : temp) {
            if (d.getBidangKeahlian() == null || d.getBidangKeahlian().isEmpty()) {
                // klo sekiranya ga ada minatnya, hapus dari daftar
                dosens.remove(d);
            } else {
                // klo sekiranya punya minat, bikin minat di daftar yang udh ada
                createdDosens.get(d.getInisialDosen())
                    .setBidangKeahlian(
                        new ArrayList<Topik>(d.getBidangKeahlian().size())
                    );
                
                // lalu tambahkan minat2nya itu
                for (Topik t : d.getBidangKeahlian()) {
                    createdDosens.get(d.getInisialDosen())
                        .getBidangKeahlian().add(t);
                }
            }
        }
        //System.out.println(">>> Ngecekin isi si dosens <<<");
        //System.out.println(">>> Dosens size: " + dosens.size() + " <<<");
//        for (Dosen d : dosens) {
//            System.out.print(new StringBuilder()
//                .append("inisial : ").append(d.getInisialDosen()).append(" - ")
//                .append("nama dosen: ").append(d.getNamaDosen()).append(" - ")
//                .append("status: ").append(d.getStatus()).append(" - ")
//                .append("gelar depan: ").append(d.getGelarDepan()).append(" - ")
//                .append("gelar blkg: ").append(d.getGelarBelakang()).append(" - ")
//                .toString()
//            );
//            for (KetersediaanWaktuDosen k : d.getKetersediaanWaktuDosens()) {
//                System.out.print(new StringBuilder()
//                    .append("tgl: ").append(k.getTanggalDsnSedia()).append(" - ")
//                    .append("slot: ").append(k.getSlotWaktu().getIdSlot())
//                    .toString()
//                );
//            }
//            System.out.println();
//
//        }
        
        return dosens;
    }
    
    public List<Dosen> findDosenPengujisByMinatTopik(Topik topik) {
        List<Dosen> pengujis;
        if (topik == null) {
            pengujis = new ArrayList<Dosen>(0);
        } else {
            Query query = em.createQuery(
                "select distinct d from Dosen as d join fetch d.bidangKeahlian as t where t.idTopik = :idTopik order by d.inisialDosen")
                .setParameter("idTopik", topik.getIdTopik());
            pengujis = query.getResultList();
        }
        return pengujis;
    }

	public List<KaryaAkhir> getKaryaAkhir(String dosenID) {
		Dosen dosen = find(dosenID);
		List<KaryaAkhir> listKaryaAkhir = dosen.getKaryaAkhirList();
		for (KaryaAkhir ka : listKaryaAkhir) {
			System.out.println(ka.getJudulKa());
		}
		return listKaryaAkhir;
	}

	@Override
	public Dosen find(String inisial) {
		return find(inisial);
	}
	
}
