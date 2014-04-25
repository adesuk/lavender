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
@Table(name = "REFERENCE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reference.findAll", query = "SELECT r FROM Reference r"),
    @NamedQuery(name = "Reference.findByIdTopik", query = "SELECT r FROM Reference r WHERE r.referencePK.idTopik = :idTopik"),
    @NamedQuery(name = "Reference.findByInisialDosen", query = "SELECT r FROM Reference r WHERE r.referencePK.inisialDosen = :inisialDosen")})
public class Reference implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ReferencePK referencePK;

    public Reference() {
    }

    public Reference(ReferencePK referencePK) {
        this.referencePK = referencePK;
    }

    public Reference(int idTopik, String inisialDosen) {
        this.referencePK = new ReferencePK(idTopik, inisialDosen);
    }

    public ReferencePK getReferencePK() {
        return referencePK;
    }

    public void setReferencePK(ReferencePK referencePK) {
        this.referencePK = referencePK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (referencePK != null ? referencePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reference)) {
            return false;
        }
        Reference other = (Reference) object;
        if ((this.referencePK == null && other.referencePK != null) || (this.referencePK != null && !this.referencePK.equals(other.referencePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.Reference[ referencePK=" + referencePK + " ]";
    }
    
}
