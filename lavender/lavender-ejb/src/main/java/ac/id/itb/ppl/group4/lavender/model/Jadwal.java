/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.group4.lavender.model;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author adesuk
 */
@Entity
@Table(name = "JADWAL")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Jadwal.findAll", query = "SELECT j FROM Jadwal j"),
    @NamedQuery(name = "Jadwal.findByIdJadwal", query = "SELECT j FROM Jadwal j WHERE j.idJadwal = :idJadwal"),
    @NamedQuery(name = "Jadwal.findByTanggal", query = "SELECT j FROM Jadwal j WHERE j.tanggal = :tanggal"),
    @NamedQuery(name = "Jadwal.findByStatusPelaksanaan", query = "SELECT j FROM Jadwal j WHERE j.statusPelaksanaan = :statusPelaksanaan"),
    @NamedQuery(name = "Jadwal.findByStatusHasilPelaksanaan", query = "SELECT j FROM Jadwal j WHERE j.statusHasilPelaksanaan = :statusHasilPelaksanaan")})
public class Jadwal implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_JADWAL")
    private Integer idJadwal;
    @Column(name = "TANGGAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggal;
    @Column(name = "STATUS_PELAKSANAAN")
    private BigInteger statusPelaksanaan;
    @Column(name = "STATUS_HASIL_PELAKSANAAN")
    private BigInteger statusHasilPelaksanaan;
    @JoinColumn(name = "ID_SLOT", referencedColumnName = "ID_SLOT")
    @ManyToOne(optional = false)
    private SlotWaktu idSlot;
    @JoinColumn(name = "KD_RUANGAN", referencedColumnName = "KD_RUANGAN")
    @ManyToOne
    private Ruangan kdRuangan;
    @JoinColumn(name = "ID_PERIODE", referencedColumnName = "ID_PERIODE")
    @ManyToOne(optional = false)
    private Periode idPeriode;
    @JoinColumn(name = "ID_KA", referencedColumnName = "ID_KA")
    @ManyToOne(optional = false)
    private KaryaAkhir idKa;

    public Jadwal() {
    }

    public Jadwal(Integer idJadwal) {
        this.idJadwal = idJadwal;
    }

    public Integer getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(Integer idJadwal) {
        this.idJadwal = idJadwal;
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

    public SlotWaktu getIdSlot() {
        return idSlot;
    }

    public void setIdSlot(SlotWaktu idSlot) {
        this.idSlot = idSlot;
    }

    public Ruangan getKdRuangan() {
        return kdRuangan;
    }

    public void setKdRuangan(Ruangan kdRuangan) {
        this.kdRuangan = kdRuangan;
    }

    public Periode getIdPeriode() {
        return idPeriode;
    }

    public void setIdPeriode(Periode idPeriode) {
        this.idPeriode = idPeriode;
    }

    public KaryaAkhir getIdKa() {
        return idKa;
    }

    public void setIdKa(KaryaAkhir idKa) {
        this.idKa = idKa;
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
        return "ac.id.itb.ppl.group4.lavender.model2.Jadwal[ idJadwal=" + idJadwal + " ]";
    }
    
}
