/*
 * PERHATIAN!
 * ANGGEP KELAS INI GA ADA, CUMAN STUB NYA INI.
 */

package ac.id.itb.ppl.group4.lavender.daoz;

import ac.id.itb.ppl.group4.lavender.modelz.Opsi;
import java.util.*;
import javax.ejb.*;

/**
 *
 * @author Edbert
 */
@Stateless
@LocalBean
public class OpsiDao {
    private List<Opsi> opsis;
    
    public OpsiDao() {
        Opsi opsi;
        opsis = new ArrayList<Opsi>();
        opsi = new Opsi();
        opsi.setId(1);
        opsi.setNama("RPL");
        opsis.add(opsi);
        opsi = new Opsi();
        opsi.setId(1);
        opsi.setNama("CS");
        opsis.add(opsi);
        opsi = new Opsi();
        opsi.setId(1);
        opsi.setNama("BI");
        opsis.add(opsi);
        opsi = new Opsi();
        opsi.setId(1);
        opsi.setNama("SI");
        opsis.add(opsi);
    }
    
    public List<Opsi> getOpsis() {
        return opsis;
    }
}
