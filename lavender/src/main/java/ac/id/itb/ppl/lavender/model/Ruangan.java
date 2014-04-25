/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Edbert
 */
@Entity
@Table(name = "RUANGAN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ruangan.findAll", query = "SELECT r FROM Ruangan r"),
    @NamedQuery(name = "Ruangan.findByKdRuangan", query = "SELECT r FROM Ruangan r WHERE r.kdRuangan = :kdRuangan"),
    @NamedQuery(name = "Ruangan.findByNamaRuangan", query = "SELECT r FROM Ruangan r WHERE r.namaRuangan = :namaRuangan")})
public class Ruangan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 4)
    @Column(name = "KD_RUANGAN")
    private String kdRuangan;
    @Size(max = 5)
    @Column(name = "NAMA_RUANGAN")
    private String namaRuangan;

    public Ruangan() {
    }

    public Ruangan(String kdRuangan) {
        this.kdRuangan = kdRuangan;
    }

    public String getKdRuangan() {
        return kdRuangan;
    }

    public void setKdRuangan(String kdRuangan) {
        this.kdRuangan = kdRuangan;
    }

    public String getNamaRuangan() {
        return namaRuangan;
    }

    public void setNamaRuangan(String namaRuangan) {
        this.namaRuangan = namaRuangan;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kdRuangan != null ? kdRuangan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ruangan)) {
            return false;
        }
        Ruangan other = (Ruangan) object;
        if ((this.kdRuangan == null && other.kdRuangan != null) || (this.kdRuangan != null && !this.kdRuangan.equals(other.kdRuangan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.Ruangan[ kdRuangan=" + kdRuangan + " ]";
    }
    
}
