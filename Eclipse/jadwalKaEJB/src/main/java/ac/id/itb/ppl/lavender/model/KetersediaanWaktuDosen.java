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
@Table(name = "KETERSEDIAAN_WAKTU_DOSEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KetersediaanWaktuDosen.findAll", query = "SELECT k FROM KetersediaanWaktuDosen k"),
    @NamedQuery(name = "KetersediaanWaktuDosen.findByTanggalDsnSedia", query = "SELECT k FROM KetersediaanWaktuDosen k WHERE k.tanggalDsnSedia = :tanggalDsnSedia")})
public class KetersediaanWaktuDosen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TANGGAL_DSN_SEDIA")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tanggalDsnSedia;
    @JoinColumn(name = "ID_SLOT", referencedColumnName = "ID_SLOT")
    @ManyToOne(optional = false)
    private SlotWaktu slotWaktu;
    @JoinColumn(name = "INISIAL_DOSEN", referencedColumnName = "INISIAL_DOSEN")
    @ManyToOne(optional = false)
    private Dosen dosen;

    public KetersediaanWaktuDosen() {
    }

    public KetersediaanWaktuDosen(Date tanggalDsnSedia) {
        this.tanggalDsnSedia = tanggalDsnSedia;
    }

    public Date getTanggalDsnSedia() {
        return tanggalDsnSedia;
    }

    public void setTanggalDsnSedia(Date tanggalDsnSedia) {
        this.tanggalDsnSedia = tanggalDsnSedia;
    }

    public SlotWaktu getSlotWaktu() {
        return slotWaktu;
    }

    public void setSlotWaktu(SlotWaktu slotWaktu) {
        this.slotWaktu = slotWaktu;
    }

    public Dosen getDosen() {
        return dosen;
    }

    public void setDosen(Dosen dosen) {
        this.dosen = dosen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tanggalDsnSedia != null ? tanggalDsnSedia.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KetersediaanWaktuDosen)) {
            return false;
        }
        KetersediaanWaktuDosen other = (KetersediaanWaktuDosen) object;
        if ((this.tanggalDsnSedia == null && other.tanggalDsnSedia != null) || (this.tanggalDsnSedia != null && !this.tanggalDsnSedia.equals(other.tanggalDsnSedia))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.KetersediaanWaktuDosen[ tanggalDsnSedia=" + tanggalDsnSedia + " ]";
    }
    
}
