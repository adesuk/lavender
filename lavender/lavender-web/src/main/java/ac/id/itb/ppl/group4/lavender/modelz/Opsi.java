/*
 * PERHATIAN!
 * ANGGEP KELAS INI GA ADA, CUMAN STUB NYA INI.
 */

package ac.id.itb.ppl.group4.lavender.modelz;

import javax.persistence.*;

/**
 *
 * @author Edbert
 */
@Entity
public class Opsi implements java.io.Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Column
    private Integer id;
    @Column
    private String nama;
    
    public Opsi() { }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }
}
