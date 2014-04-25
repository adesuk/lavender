package ac.id.itb.ppl.group4.lavender.nmodel;

import ac.id.itb.ppl.group4.lavender.model.Periode;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.*;

/**
 * Kelas Jadwal Eksekusi.
 * 1 periode punya byk Jadwal Eksekusi (Seminar/Sidang).
 * 1 Jadwal Eksekusi itu isinya cuma periode, versi, tanggal dibuat, tanggal selesai dibuat,
 * sama status si jadwal ini.
 * 1 jadwal eksekusi punya rincian-rincian jadwal eksekusinya
 * Rincian jadwal eksekusi itu yg ntar isinya ruangan, tanggal pelaksanaan eksekusi,
 * status hasil pelaksanaan, dll, yg nanti terhubung ke slot waktu.
 * 
 * @author Edbert
 */
public class JadwalEksekusi implements java.io.Serializable {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column
//    private BigInteger id; // karena pasti bisa byk versi, jd pake big integer aja
//    @ManyToOne
//    @JoinColumn(name = "...", nullable = false)
//    private Periode periode;
//
//    public BigInteger getId() {
//        return id;
//    }
//
//    public void setId(BigInteger id) {
//        this.id = id;
//    }
//
//    public Periode getPeriode() {
//        return periode;
//    }
//
//    public void setPeriode(Periode periode) {
//        this.periode = periode;
//    }
//
//    public List<DetailJadwalEksekusi> getDetailJadwalEksekusis() {
//        return detailJadwalEksekusis;
//    }
//
//    public void setDetailJadwalEksekusis(List<DetailJadwalEksekusi> detailJadwalEksekusis) {
//        this.detailJadwalEksekusis = detailJadwalEksekusis;
//    }
//
//    public String getVersi() {
//        return versi;
//    }
//
//    public void setVersi(String versi) {
//        this.versi = versi;
//    }
//
//    public Calendar getTanggalDibuat() {
//        return tanggalDibuat;
//    }
//
//    public void setTanggalDibuat(Calendar tanggalDibuat) {
//        this.tanggalDibuat = tanggalDibuat;
//    }
//
//    public Calendar getTanggalSelesaiDibuat() {
//        return tanggalSelesaiDibuat;
//    }
//
//    public void setTanggalSelesaiDibuat(Calendar tanggalSelesaiDibuat) {
//        this.tanggalSelesaiDibuat = tanggalSelesaiDibuat;
//    }
//
//    public String getStatusJadwal() {
//        return statusJadwal;
//    }
//
//    public void setStatusJadwal(String statusJadwal) {
//        this.statusJadwal = statusJadwal;
//    }
//    @Column
//    private List<DetailJadwalEksekusi> detailJadwalEksekusis = 
//        new ArrayList<DetailJadwalEksekusi>(0);
//    @Column
//    private String versi;
//    @Column
//    private Calendar tanggalDibuat;
//    @Column
//    private Calendar tanggalSelesaiDibuat; // perlu gak?
//    @Column
//    private String statusJadwal;
//    // status jadwal sedang digenerate, sudah selesai, atau versi yang fix dipilih 
//    // (menurutku lock itu maenin atribut ini aja)
}
