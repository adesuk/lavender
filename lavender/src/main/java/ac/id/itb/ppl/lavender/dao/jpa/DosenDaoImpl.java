package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.DosenDao;
import ac.id.itb.ppl.lavender.model.*;
import java.util.*;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Edbert
 */
@Stateless
public class DosenDaoImpl extends JpaDao {
    /**
     * Get dosen-dosen beserta topik dan waktu ketersediaannya sesuai dengan
     * rentang waktu periode masa seminar/sidang TA/Tesis.
     * 
     * @param periode
     * @return 
     */
    public List<Dosen> getDosenWithTopikAndKetersediaan(Periode periode) {
        System.out.println(">>> Periode akhir: " + periode.getPeriodeAkhir());
        Query query = em.createQuery(
            "select distinct dosen from Dosen as dosen " +
            "inner join fetch dosen.ketersediaanWaktuDosens as kwd " +
            "on dosen.inisialDosen = kwd.dosen.inisialDosen and kwd.tanggalDsnSedia between :periodeAwal and :periodeAkhir")
            .setParameter("periodeAwal", periode.getPeriodeAwal(), TemporalType.DATE)
            .setParameter("periodeAkhir", periode.getPeriodeAkhir(), TemporalType.DATE);
        List<Dosen> dosens = query.getResultList();
        
//        Query query = em.createQuery(
//            "select k from KetersediaanWaktuDosen as k left join k.dosen as d where k.tanggalDsnSedia between :start and :end")
//            .setParameter("start", periode.getPeriodeAwal(), TemporalType.DATE)
//            .setParameter("end", periode.getPeriodeAkhir(), TemporalType.DATE);
//        List<KetersediaanWaktuDosen> kwds = query.getResultList();
//        List<Dosen> dosens = new ArrayList<Dosen>(kwds.size());
//        for (KetersediaanWaktuDosen kwd : kwds) {
//            dosens.add(kwd.getDosen());
//        }
        
        for (Dosen dosen : dosens) {
            for (KetersediaanWaktuDosen kwd : dosen.getKetersediaanWaktuDosens()) {
                if (kwd.getTanggalDsnSedia().compareTo(periode.getPeriodeAwal()) < 0 ||
                    kwd.getTanggalDsnSedia().compareTo(periode.getPeriodeAkhir()) > 0) {
                    dosen.getKetersediaanWaktuDosens().remove(kwd);
                }
            }
        }
        return dosens;
    }
    //"left join fetch dosen.bidangKeahlian as bk"
}
