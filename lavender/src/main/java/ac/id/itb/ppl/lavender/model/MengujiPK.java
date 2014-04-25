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
public class MengujiPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_JADWAL")
    private int idJadwal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "INISIAL_DOSEN")
    private String inisialDosen;

    public MengujiPK() {
    }

    public MengujiPK(int idJadwal, String inisialDosen) {
        this.idJadwal = idJadwal;
        this.inisialDosen = inisialDosen;
    }

    public int getIdJadwal() {
        return idJadwal;
    }

    public void setIdJadwal(int idJadwal) {
        this.idJadwal = idJadwal;
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
        hash += (int) idJadwal;
        hash += (inisialDosen != null ? inisialDosen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MengujiPK)) {
            return false;
        }
        MengujiPK other = (MengujiPK) object;
        if (this.idJadwal != other.idJadwal) {
            return false;
        }
        if ((this.inisialDosen == null && other.inisialDosen != null) || (this.inisialDosen != null && !this.inisialDosen.equals(other.inisialDosen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.MengujiPK[ idJadwal=" + idJadwal + ", inisialDosen=" + inisialDosen + " ]";
    }
    
}
