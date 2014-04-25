/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edbert
 */
@Entity
@Table(name = "JADWAL_KULIAH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JadwalKuliah.findAll", query = "SELECT j FROM JadwalKuliah j"),
    @NamedQuery(name = "JadwalKuliah.findByIdJadwalKuliah", query = "SELECT j FROM JadwalKuliah j WHERE j.idJadwalKuliah = :idJadwalKuliah"),
    @NamedQuery(name = "JadwalKuliah.findByKdRuangan", query = "SELECT j FROM JadwalKuliah j WHERE j.kdRuangan = :kdRuangan"),
    @NamedQuery(name = "JadwalKuliah.findByIdPeriodeKuliah", query = "SELECT j FROM JadwalKuliah j WHERE j.idPeriodeKuliah = :idPeriodeKuliah"),
    @NamedQuery(name = "JadwalKuliah.findByKodeMataKuliah", query = "SELECT j FROM JadwalKuliah j WHERE j.kodeMataKuliah = :kodeMataKuliah"),
    @NamedQuery(name = "JadwalKuliah.findByHari", query = "SELECT j FROM JadwalKuliah j WHERE j.hari = :hari"),
    @NamedQuery(name = "JadwalKuliah.findByWaktuMasuk", query = "SELECT j FROM JadwalKuliah j WHERE j.waktuMasuk = :waktuMasuk"),
    @NamedQuery(name = "JadwalKuliah.findByWaktuKeluar", query = "SELECT j FROM JadwalKuliah j WHERE j.waktuKeluar = :waktuKeluar")})
public class JadwalKuliah implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_JADWAL_KULIAH")
    private Integer idJadwalKuliah;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "KD_RUANGAN")
    private String kdRuangan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODE_KULIAH")
    private int idPeriodeKuliah;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 6)
    @Column(name = "KODE_MATA_KULIAH")
    private String kodeMataKuliah;
    @Size(max = 3)
    @Column(name = "HARI")
    private String hari;
    @Column(name = "WAKTU_MASUK")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuMasuk;
    @Column(name = "WAKTU_KELUAR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date waktuKeluar;

    public JadwalKuliah() {
    }

    public JadwalKuliah(Integer idJadwalKuliah) {
        this.idJadwalKuliah = idJadwalKuliah;
    }

    public JadwalKuliah(Integer idJadwalKuliah, String kdRuangan, int idPeriodeKuliah, String kodeMataKuliah) {
        this.idJadwalKuliah = idJadwalKuliah;
        this.kdRuangan = kdRuangan;
        this.idPeriodeKuliah = idPeriodeKuliah;
        this.kodeMataKuliah = kodeMataKuliah;
    }

    public Integer getIdJadwalKuliah() {
        return idJadwalKuliah;
    }

    public void setIdJadwalKuliah(Integer idJadwalKuliah) {
        this.idJadwalKuliah = idJadwalKuliah;
    }

    public String getKdRuangan() {
        return kdRuangan;
    }

    public void setKdRuangan(String kdRuangan) {
        this.kdRuangan = kdRuangan;
    }

    public int getIdPeriodeKuliah() {
        return idPeriodeKuliah;
    }

    public void setIdPeriodeKuliah(int idPeriodeKuliah) {
        this.idPeriodeKuliah = idPeriodeKuliah;
    }

    public String getKodeMataKuliah() {
        return kodeMataKuliah;
    }

    public void setKodeMataKuliah(String kodeMataKuliah) {
        this.kodeMataKuliah = kodeMataKuliah;
    }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }

    public Date getWaktuMasuk() {
        return waktuMasuk;
    }

    public void setWaktuMasuk(Date waktuMasuk) {
        this.waktuMasuk = waktuMasuk;
    }

    public Date getWaktuKeluar() {
        return waktuKeluar;
    }

    public void setWaktuKeluar(Date waktuKeluar) {
        this.waktuKeluar = waktuKeluar;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJadwalKuliah != null ? idJadwalKuliah.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JadwalKuliah)) {
            return false;
        }
        JadwalKuliah other = (JadwalKuliah) object;
        if ((this.idJadwalKuliah == null && other.idJadwalKuliah != null) || (this.idJadwalKuliah != null && !this.idJadwalKuliah.equals(other.idJadwalKuliah))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.JadwalKuliah[ idJadwalKuliah=" + idJadwalKuliah + " ]";
    }
    
}
