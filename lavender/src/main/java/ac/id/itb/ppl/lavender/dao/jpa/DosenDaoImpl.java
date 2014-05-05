package ac.id.itb.ppl.lavender.dao.jpa;

import ac.id.itb.ppl.lavender.dao.DosenDao;
import ac.id.itb.ppl.lavender.model.*;
import java.util.*;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Edbert
 */
@Stateless
public class DosenDaoImpl extends JpaDao implements DosenDao {
    /**
     * Mencari dosen berdasarkan inisialnya.
     * 
     * @param inisial
     * @return 
     */
    @Override
    public Dosen find(String inisial) {
        return em.find(Dosen.class, inisial);
    }
    
    /**
     * Get dosen-dosen beserta topik dan waktu ketersediaannya sesuai dengan
     * rentang waktu periode masa seminar/sidang TA/Tesis.
     * 
     * @param periode
     * @return 
     */
    @Override
    public List<Dosen> getDosenWithTopikAndKetersediaan(Periode periode) {
        Query query = em.createQuery(
            "select distinct dosen from Dosen as dosen " +
            "inner join fetch dosen.ketersediaanWaktuDosens as kwd " +
            "on dosen.inisialDosen = kwd.dosen.inisialDosen and kwd.tanggalDsnSedia between :periodeAwal and :periodeAkhir")
            .setParameter("periodeAwal", periode.getPeriodeAwal(), TemporalType.DATE)
            .setParameter("periodeAkhir", periode.getPeriodeAkhir(), TemporalType.DATE);
        List<Dosen> dosens = query.getResultList();
        
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
    
    @Override
    public List<Dosen> getDosenPengujisByMinatTopik(Topik topik) {
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
}
