/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edbert
 */
@Entity
@Table(name = "KETERSEDIAAN_WAKTU_DOSEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KetersediaanWaktuDosen.findAll", query = "SELECT k FROM KetersediaanWaktuDosen k"),
    @NamedQuery(name = "KetersediaanWaktuDosen.findByInisialDosen", query = "SELECT k FROM KetersediaanWaktuDosen k WHERE k.ketersediaanWaktuDosenPK.inisialDosen = :inisialDosen"),
    @NamedQuery(name = "KetersediaanWaktuDosen.findByIdSlot", query = "SELECT k FROM KetersediaanWaktuDosen k WHERE k.ketersediaanWaktuDosenPK.idSlot = :idSlot"),
    @NamedQuery(name = "KetersediaanWaktuDosen.findByTanggalDsnSedia", query = "SELECT k FROM KetersediaanWaktuDosen k WHERE k.tanggalDsnSedia = :tanggalDsnSedia")})
public class KetersediaanWaktuDosen implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected KetersediaanWaktuDosenPK ketersediaanWaktuDosenPK;
    @Column(name = "TANGGAL_DSN_SEDIA")
    @Temporal(TemporalType.DATE)
    private Date tanggalDsnSedia;
    @JoinColumn(name = "ID_SLOT", referencedColumnName = "ID_SLOT", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private SlotWaktu slotWaktu;
    @JoinColumn(name = "INISIAL_DOSEN", referencedColumnName = "INISIAL_DOSEN", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Dosen dosen;

    public KetersediaanWaktuDosen() {
    }

    public KetersediaanWaktuDosen(KetersediaanWaktuDosenPK ketersediaanWaktuDosenPK) {
        this.ketersediaanWaktuDosenPK = ketersediaanWaktuDosenPK;
    }

    public KetersediaanWaktuDosen(String inisialDosen, int idSlot) {
        this.ketersediaanWaktuDosenPK = new KetersediaanWaktuDosenPK(inisialDosen, idSlot);
    }

    public KetersediaanWaktuDosenPK getKetersediaanWaktuDosenPK() {
        return ketersediaanWaktuDosenPK;
    }

    public void setKetersediaanWaktuDosenPK(KetersediaanWaktuDosenPK ketersediaanWaktuDosenPK) {
        this.ketersediaanWaktuDosenPK = ketersediaanWaktuDosenPK;
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
        hash += (ketersediaanWaktuDosenPK != null ? ketersediaanWaktuDosenPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KetersediaanWaktuDosen)) {
            return false;
        }
        KetersediaanWaktuDosen other = (KetersediaanWaktuDosen) object;
        if ((this.ketersediaanWaktuDosenPK == null && other.ketersediaanWaktuDosenPK != null) || (this.ketersediaanWaktuDosenPK != null && !this.ketersediaanWaktuDosenPK.equals(other.ketersediaanWaktuDosenPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.KetersediaanWaktuDosen[ ketersediaanWaktuDosenPK=" + ketersediaanWaktuDosenPK + " ]";
    }
    
}
