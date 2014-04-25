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
@Table(name = "MENGAJAR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mengajar.findAll", query = "SELECT m FROM Mengajar m"),
    @NamedQuery(name = "Mengajar.findByIdJadwalKuliah", query = "SELECT m FROM Mengajar m WHERE m.mengajarPK.idJadwalKuliah = :idJadwalKuliah"),
    @NamedQuery(name = "Mengajar.findByInisialDosen", query = "SELECT m FROM Mengajar m WHERE m.mengajarPK.inisialDosen = :inisialDosen")})
public class Mengajar implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MengajarPK mengajarPK;

    public Mengajar() {
    }

    public Mengajar(MengajarPK mengajarPK) {
        this.mengajarPK = mengajarPK;
    }

    public Mengajar(int idJadwalKuliah, String inisialDosen) {
        this.mengajarPK = new MengajarPK(idJadwalKuliah, inisialDosen);
    }

    public MengajarPK getMengajarPK() {
        return mengajarPK;
    }

    public void setMengajarPK(MengajarPK mengajarPK) {
        this.mengajarPK = mengajarPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mengajarPK != null ? mengajarPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mengajar)) {
            return false;
        }
        Mengajar other = (Mengajar) object;
        if ((this.mengajarPK == null && other.mengajarPK != null) || (this.mengajarPK != null && !this.mengajarPK.equals(other.mengajarPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.Mengajar[ mengajarPK=" + mengajarPK + " ]";
    }
    
}
