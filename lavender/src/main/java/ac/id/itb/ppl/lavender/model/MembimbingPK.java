/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Edbert
 */
@Embeddable
public class MembimbingPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "INISIAL_DOSEN")
    private String inisialDosen;
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_KA")
    private int idKa;

    public MembimbingPK() {
    }

    public MembimbingPK(String inisialDosen, int idKa) {
        this.inisialDosen = inisialDosen;
        this.idKa = idKa;
    }

    public String getInisialDosen() {
        return inisialDosen;
    }

    public void setInisialDosen(String inisialDosen) {
        this.inisialDosen = inisialDosen;
    }

    public int getIdKa() {
        return idKa;
    }

    public void setIdKa(int idKa) {
        this.idKa = idKa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inisialDosen != null ? inisialDosen.hashCode() : 0);
        hash += (int) idKa;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MembimbingPK)) {
            return false;
        }
        MembimbingPK other = (MembimbingPK) object;
        if ((this.inisialDosen == null && other.inisialDosen != null) || (this.inisialDosen != null && !this.inisialDosen.equals(other.inisialDosen))) {
            return false;
        }
        if (this.idKa != other.idKa) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.MembimbingPK[ inisialDosen=" + inisialDosen + ", idKa=" + idKa + " ]";
    }
    
}
