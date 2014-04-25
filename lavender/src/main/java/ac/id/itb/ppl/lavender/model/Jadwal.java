/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "JADWAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jadwal.findAll", query = "SELECT j FROM Jadwal j"),
    @NamedQuery(name = "Jadwal.findByIdJadwal", query = "SELECT j FROM Jadwal j WHERE j.idJadwal = :idJadwal"),
    @NamedQuery(name = "Jadwal.findByKdRuangan", query = "SELECT j FROM Jadwal j WHERE j.kdRuangan = :kdRuangan"),
    @NamedQuery(name = "Jadwal.findByIdSlot", query = "SELECT j FROM Jadwal j WHERE j.idSlot = :idSlot"),
    @NamedQuery(name = "Jadwal.findByIdPeriode", query = "SELECT j FROM Jadwal j WHERE j.idPeriode = :idPeriode"),
    @NamedQuery(name = "Jadwal.findByIdKa", query = "SELECT j FROM Jadwal j WHERE j.idKa = :idKa"),
    @NamedQuery(name = "Jadwal.findByTanggal", query = "SELECT j FROM Jadwal j WHERE j.tanggal = :tanggal"),
    @NamedQuery(name = "Jadwal.findByStatusPelaksanaan", query = "SELECT j FROM Jadwal j WHERE j.statusPelaksanaan = :statusPelaksanaan"),
    @NamedQuery(name = "Jadwal.findByStatusHasilPelaksanaan", query = "SELECT j FROM Jadwal j WHERE j.statusHasilPelaksanaan = :statusHasilPelaksanaan"),
    @NamedQuery(name = "Jadwal.findByGenerateDate", query = "SELECT j FROM Jadwal j WHERE j.generateDate = :generateDate")})
public class Jadwal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_JADWAL")
    private Integer idJadwal;
    @Size(max = 4)
    @Column(name = "KD_RUANGAN")
    private String kdRuangan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SLOT")
    private int idSlot;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODE")
    private int idPeriode;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_KA")
    private int idKa;
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.DATE)
    private Date tanggal;
    @Column(name = "STATUS_PELAKSANAAN")
    private BigInteger statusPelaksanaan;
    @Column(name = "STATUS_HASIL_PELAKSANAAN")
    private BigInteger statusHasilPelaksanaan;
    @Basic(optional = false)
    @NotNull
    @Column(name = "GENERATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date generateDate;

    public Jadwal() {
    }

    public Jadwal(Integer idJadwal) {
        this.idJadwal = idJadwal;
    }

    public Jadwal(Integer idJadwal, int idSlot, int idPeriode, int idKa, Date generateDate) {
        this.idJadwal = idJadwal;
        this.idSlot = idSlot;
        this.idPeriode = idPeriode;
        this.idKa = idKa;
        this.generateDate = generateDate;
    }

    public Integer getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(Integer idJadwal) {
        this.idJadwal = idJadwal;
    }

    public String getKdRuangan() {
        return kdRuangan;
    }

    public void setKdRuangan(String kdRuangan) {
        this.kdRuangan = kdRuangan;
    }

    public int getIdSlot() {
        return idSlot;
    }

    public void setIdSlot(int idSlot) {
        this.idSlot = idSlot;
    }

    public int getIdPeriode() {
        return idPeriode;
    }

    public void setIdPeriode(int idPeriode) {
        this.idPeriode = idPeriode;
    }

    public int getIdKa() {
        return idKa;
    }

    public void setIdKa(int idKa) {
        this.idKa = idKa;
    }

    public Date getTanggal() {
        return tanggal;
    }

    public void setTanggal(Date tanggal) {
        this.tanggal = tanggal;
    }

    public BigInteger getStatusPelaksanaan() {
        return statusPelaksanaan;
    }

    public void setStatusPelaksanaan(BigInteger statusPelaksanaan) {
        this.statusPelaksanaan = statusPelaksanaan;
    }

    public BigInteger getStatusHasilPelaksanaan() {
        return statusHasilPelaksanaan;
    }

    public void setStatusHasilPelaksanaan(BigInteger statusHasilPelaksanaan) {
        this.statusHasilPelaksanaan = statusHasilPelaksanaan;
    }

    public Date getGenerateDate() {
        return generateDate;
    }

    public void setGenerateDate(Date generateDate) {
        this.generateDate = generateDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idJadwal != null ? idJadwal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jadwal)) {
            return false;
        }
        Jadwal other = (Jadwal) object;
        if ((this.idJadwal == null && other.idJadwal != null) || (this.idJadwal != null && !this.idJadwal.equals(other.idJadwal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.Jadwal[ idJadwal=" + idJadwal + " ]";
    }
    
}
