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
@Table(name = "TOPIK")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Topik.findAll", query = "SELECT t FROM Topik t"),
    @NamedQuery(name = "Topik.findByIdTopik", query = "SELECT t FROM Topik t WHERE t.idTopik = :idTopik"),
    @NamedQuery(name = "Topik.findByNamaTopik", query = "SELECT t FROM Topik t WHERE t.namaTopik = :namaTopik"),
    @NamedQuery(name = "Topik.findByBidang", query = "SELECT t FROM Topik t WHERE t.bidang = :bidang")})
public class Topik implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_TOPIK")
    private Integer idTopik;
    @Size(max = 30)
    @Column(name = "NAMA_TOPIK")
    private String namaTopik;
    @Size(max = 10)
    @Column(name = "BIDANG")
    private String bidang;

    public Topik() {
    }

    public Topik(Integer idTopik) {
        this.idTopik = idTopik;
    }

    public Integer getIdTopik() {
        return idTopik;
    }

    public void setIdTopik(Integer idTopik) {
        this.idTopik = idTopik;
    }

    public String getNamaTopik() {
        return namaTopik;
    }

    public void setNamaTopik(String namaTopik) {
        this.namaTopik = namaTopik;
    }

    public String getBidang() {
        return bidang;
    }

    public void setBidang(String bidang) {
        this.bidang = bidang;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idTopik != null ? idTopik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Topik)) {
            return false;
        }
        Topik other = (Topik) object;
        if ((this.idTopik == null && other.idTopik != null) || (this.idTopik != null && !this.idTopik.equals(other.idTopik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.Topik[ idTopik=" + idTopik + " ]";
    }
    
}
