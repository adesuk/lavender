/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edbert
 */
@Entity
@Table(name = "MENGUJI")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Menguji.findAll", query = "SELECT m FROM Menguji m"),
    @NamedQuery(name = "Menguji.findByIdJadwal", query = "SELECT m FROM Menguji m WHERE m.mengujiPK.idJadwal = :idJadwal"),
    @NamedQuery(name = "Menguji.findByInisialDosen", query = "SELECT m FROM Menguji m WHERE m.mengujiPK.inisialDosen = :inisialDosen")})
public class Menguji implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MengujiPK mengujiPK;

    public Menguji() {
    }

    public Menguji(MengujiPK mengujiPK) {
        this.mengujiPK = mengujiPK;
    }

    public Menguji(int idJadwal, String inisialDosen) {
        this.mengujiPK = new MengujiPK(idJadwal, inisialDosen);
    }

    public MengujiPK getMengujiPK() {
        return mengujiPK;
    }

    public void setMengujiPK(MengujiPK mengujiPK) {
        this.mengujiPK = mengujiPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mengujiPK != null ? mengujiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Menguji)) {
            return false;
        }
        Menguji other = (Menguji) object;
        if ((this.mengujiPK == null && other.mengujiPK != null) || (this.mengujiPK != null && !this.mengujiPK.equals(other.mengujiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.Menguji[ mengujiPK=" + mengujiPK + " ]";
    }
    
}
