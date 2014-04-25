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
public class ReferencePK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TOPIK")
    private int idTopik;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "INISIAL_DOSEN")
    private String inisialDosen;

    public ReferencePK() {
    }

    public ReferencePK(int idTopik, String inisialDosen) {
        this.idTopik = idTopik;
        this.inisialDosen = inisialDosen;
    }

    public int getIdTopik() {
        return idTopik;
    }

    public void setIdTopik(int idTopik) {
        this.idTopik = idTopik;
    }

    public String getInisialDosen() {
        return inisialDosen;
    }

    public void setInisialDosen(String inisialDosen) {
        this.inisialDosen = inisialDosen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTopik;
        hash += (inisialDosen != null ? inisialDosen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ReferencePK)) {
            return false;
        }
        ReferencePK other = (ReferencePK) object;
        if (this.idTopik != other.idTopik) {
            return false;
        }
        if ((this.inisialDosen == null && other.inisialDosen != null) || (this.inisialDosen != null && !this.inisialDosen.equals(other.inisialDosen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.ReferencePK[ idTopik=" + idTopik + ", inisialDosen=" + inisialDosen + " ]";
    }
    
}
