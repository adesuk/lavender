package ac.id.itb.ppl.lavender.dao;

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
public interface DosenDao {
    Dosen find(String inisial);
    
    List<Dosen> getDosenWithTopikAndKetersediaan(Periode periode);
    
    List<Dosen> getDosenPengujisByMinatTopik(Topik topik);
}
