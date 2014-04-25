/*
 * PERHATIAN!
 * ANGGEP KELAS INI GA ADA, CUMAN STUB NYA INI.
*/
package ac.id.itb.ppl.group4.lavender.modelz;

import java.util.*;
import javax.persistence.*;

/**
 *
 * @author Edbert
 */
@Entity
public class Dosen implements java.io.Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String nama;
    private String inisial;
    private List<Opsi> bidangKeahlian = new ArrayList<Opsi>(0);
    
    public Dosen() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public String getInisial() {
        return inisial;
    }
    
    public void setInisial(String inisial) {
        this.inisial = inisial;
    }

    public List<Opsi> getBidangKeahlian() {
        return bidangKeahlian;
    }

    public void setBidangKeahlian(List<Opsi> bidangKeahlian) {
        this.bidangKeahlian = bidangKeahlian;
    }
}
