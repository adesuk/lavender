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
@Table(name = "MEMBIMBING")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Membimbing.findAll", query = "SELECT m FROM Membimbing m"),
    @NamedQuery(name = "Membimbing.findByInisialDosen", query = "SELECT m FROM Membimbing m WHERE m.membimbingPK.inisialDosen = :inisialDosen"),
    @NamedQuery(name = "Membimbing.findByIdKa", query = "SELECT m FROM Membimbing m WHERE m.membimbingPK.idKa = :idKa")})
public class Membimbing implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MembimbingPK membimbingPK;

    public Membimbing() {
    }

    public Membimbing(MembimbingPK membimbingPK) {
        this.membimbingPK = membimbingPK;
    }

    public Membimbing(String inisialDosen, int idKa) {
        this.membimbingPK = new MembimbingPK(inisialDosen, idKa);
    }

    public MembimbingPK getMembimbingPK() {
        return membimbingPK;
    }

    public void setMembimbingPK(MembimbingPK membimbingPK) {
        this.membimbingPK = membimbingPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (membimbingPK != null ? membimbingPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Membimbing)) {
            return false;
        }
        Membimbing other = (Membimbing) object;
        if ((this.membimbingPK == null && other.membimbingPK != null) || (this.membimbingPK != null && !this.membimbingPK.equals(other.membimbingPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.Membimbing[ membimbingPK=" + membimbingPK + " ]";
    }
    
}
