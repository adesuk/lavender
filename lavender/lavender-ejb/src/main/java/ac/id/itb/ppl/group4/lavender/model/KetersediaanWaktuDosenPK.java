/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.group4.lavender.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author adesuk
 */
@Embeddable
public class KetersediaanWaktuDosenPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "INISIAL_DOSEN")
    private String inisialDosen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_SLOT")
    private int idSlot;

    public KetersediaanWaktuDosenPK() {
    }

    public KetersediaanWaktuDosenPK(String inisialDosen, int idSlot) {
        this.inisialDosen = inisialDosen;
        this.idSlot = idSlot;
    }

    public String getInisialDosen() {
        return inisialDosen;
    }

    public void setInisialDosen(String inisialDosen) {
        this.inisialDosen = inisialDosen;
    }

    public int getIdSlot() {
        return idSlot;
    }

    public void setIdSlot(int idSlot) {
        this.idSlot = idSlot;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inisialDosen != null ? inisialDosen.hashCode() : 0);
        hash += (int) idSlot;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KetersediaanWaktuDosenPK)) {
            return false;
        }
        KetersediaanWaktuDosenPK other = (KetersediaanWaktuDosenPK) object;
        if ((this.inisialDosen == null && other.inisialDosen != null) || (this.inisialDosen != null && !this.inisialDosen.equals(other.inisialDosen))) {
            return false;
        }
        if (this.idSlot != other.idSlot) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.group4.lavender.model2.KetersediaanWaktuDosenPK[ inisialDosen=" + inisialDosen + ", idSlot=" + idSlot + " ]";
    }
    
}
