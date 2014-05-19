package ac.id.itb.ppl.lavender.bean.local;

import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.Topik;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Edbert
 */
@Local
public interface DosenLocal {
    Dosen find(String inisial);
    
    List<Dosen> findDosenWithTopikAndKetersediaan(Periode periode);
    
    List<Dosen> findDosenPengujisByMinatTopik(Topik topik);
}
