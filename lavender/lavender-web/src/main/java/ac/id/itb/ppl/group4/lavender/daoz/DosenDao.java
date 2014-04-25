/*
 * PERHATIAN!
 * ANGGEP INI GA ADA, CUMAN STUB NYA INI.
 */

package ac.id.itb.ppl.group4.lavender.daoz;

import ac.id.itb.ppl.group4.lavender.modelz.*;
import java.util.*;
import javax.ejb.*;

/**
 *
 * @author Edbert
 */
@Stateless
@LocalBean
public class DosenDao {
    private List<Dosen> dosens;
    
    public DosenDao() {
        dosens = new ArrayList<Dosen>();
        List<Opsi> opsis = new OpsiDao().getOpsis();
        List<Opsi> keahlian;
        Dosen dosen;
        dosen = new Dosen();
        dosen.setId("1");
        dosen.setNama("Mortred");
        dosen.setInisial("MO");
        keahlian = new ArrayList<Opsi>();
        keahlian.add(opsis.get(0));
        dosen.setBidangKeahlian(keahlian);
        dosens.add(dosen);
        dosen = new Dosen();
        dosen.setId("2");
        dosen.setNama("Magina");
        dosen.setInisial("MA");
        keahlian = new ArrayList<Opsi>();
        keahlian.add(opsis.get(1));
        dosen.setBidangKeahlian(keahlian);
        dosens.add(dosen);
        dosen = new Dosen();
        dosen.setId("3");
        dosen.setNama("Alleria");
        dosen.setInisial("AL");
        keahlian = new ArrayList<Opsi>();
        keahlian.add(opsis.get(2));
        dosen.setBidangKeahlian(keahlian);
        dosens.add(dosen);
        dosen = new Dosen();
        dosen.setId("4");
        dosen.setNama("Purist Thunderwrath");
        dosen.setInisial("PT");
        keahlian = new ArrayList<Opsi>();
        keahlian.add(opsis.get(3));
        dosen.setBidangKeahlian(keahlian);
        dosens.add(dosen);
    }
    
    public List<Dosen> getDosens() {
        return dosens;
    }
    
    public Dosen find(Integer id) {
        return dosens.get(id);
    }
}
