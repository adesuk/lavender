/*
 * PERHATIAN!
 * ANGGEP KELAS INI GA ADA, CUMAN STUB NYA INI.
 */

package ac.id.itb.ppl.group4.lavender.modelz;

import java.util.Calendar;
import javax.persistence.*;

/**
 *
 * @author Edbert
 */
@Entity
public class JadwalSS implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private Calendar tanggal;
    private String judul;
    private String namaMhs;
    private String nimMhs;
    private String jamPelaksanaan;
    
    public JadwalSS() { }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Calendar getTanggal() {
        return tanggal;
    }

    public void setTanggal(Calendar tanggal) {
        this.tanggal = tanggal;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getNamaMhs() {
        return namaMhs;
    }

    public void setNamaMhs(String namaMhs) {
        this.namaMhs = namaMhs;
    }

    public String getNimMhs() {
        return nimMhs;
    }

    public void setNimMhs(String nimMhs) {
        this.nimMhs = nimMhs;
    }

    public String getJamPelaksanaan() {
        return jamPelaksanaan;
    }

    public void setJamPelaksanaan(String jamPelaksanaan) {
        this.jamPelaksanaan = jamPelaksanaan;
    }
}
