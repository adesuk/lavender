package ac.id.itb.ppl.lavender.bean.local;

import ac.id.itb.ppl.lavender.model.JadwalKuliah;
import ac.id.itb.ppl.lavender.model.Periode;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author edbert
 */
@Local
public interface JadwalKuliahLocal {
    List<JadwalKuliah> findByPeriode(Periode periode);
}
