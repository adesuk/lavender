/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Edbert
 */
@Entity
@Table(name = "SLOT_WAKTU")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SlotWaktu.findAll", query = "SELECT s FROM SlotWaktu s"),
    @NamedQuery(name = "SlotWaktu.findByIdSlot", query = "SELECT s FROM SlotWaktu s WHERE s.idSlot = :idSlot"),
    @NamedQuery(name = "SlotWaktu.findBySesi", query = "SELECT s FROM SlotWaktu s WHERE s.sesi = :sesi"),
    @NamedQuery(name = "SlotWaktu.findByJamAwal", query = "SELECT s FROM SlotWaktu s WHERE s.jamAwal = :jamAwal"),
    @NamedQuery(name = "SlotWaktu.findByJamAkhir", query = "SELECT s FROM SlotWaktu s WHERE s.jamAkhir = :jamAkhir")})
public class SlotWaktu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SLOT")
    private Integer idSlot;
    @Column(name = "SESI")
    private Short sesi;
    @Column(name = "JAM_AWAL")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jamAwal;
    @Column(name = "JAM_AKHIR")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jamAkhir;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "slotWaktu")
    private List<Jadwal> jadwalList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "slotWaktu")
    private List<KetersediaanWaktuDosen> ketersediaanWaktuDosenList;

    public SlotWaktu() {
    }

    public SlotWaktu(Integer idSlot) {
        this.idSlot = idSlot;
    }

    public Integer getIdSlot() {
        return idSlot;
    }

    public void setIdSlot(Integer idSlot) {
        this.idSlot = idSlot;
    }

    public Short getSesi() {
        return sesi;
    }

    public void setSesi(Short sesi) {
        this.sesi = sesi;
    }

    public Date getJamAwal() {
        return jamAwal;
    }

    public void setJamAwal(Date jamAwal) {
        this.jamAwal = jamAwal;
    }

    public Date getJamAkhir() {
        return jamAkhir;
    }

    public void setJamAkhir(Date jamAkhir) {
        this.jamAkhir = jamAkhir;
    }

    @XmlTransient
    public List<Jadwal> getJadwalList() {
        return jadwalList;
    }

    public void setJadwalList(List<Jadwal> jadwalList) {
        this.jadwalList = jadwalList;
    }

    @XmlTransient
    public List<KetersediaanWaktuDosen> getKetersediaanWaktuDosenList() {
        return ketersediaanWaktuDosenList;
    }

    public void setKetersediaanWaktuDosenList(List<KetersediaanWaktuDosen> ketersediaanWaktuDosenList) {
        this.ketersediaanWaktuDosenList = ketersediaanWaktuDosenList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSlot != null ? idSlot.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SlotWaktu)) {
            return false;
        }
        SlotWaktu other = (SlotWaktu) object;
        if ((this.idSlot == null && other.idSlot != null) || (this.idSlot != null && !this.idSlot.equals(other.idSlot))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.SlotWaktu[ idSlot=" + idSlot + " ]";
    }
    
}
