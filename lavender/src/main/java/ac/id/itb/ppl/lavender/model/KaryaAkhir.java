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
@Table(name = "KARYA_AKHIR")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "KaryaAkhir.findAll", query = "SELECT k FROM KaryaAkhir k"),
    @NamedQuery(name = "KaryaAkhir.findByIdKa", query = "SELECT k FROM KaryaAkhir k WHERE k.idKa = :idKa"),
    @NamedQuery(name = "KaryaAkhir.findByIdTopik", query = "SELECT k FROM KaryaAkhir k WHERE k.idTopik = :idTopik"),
    @NamedQuery(name = "KaryaAkhir.findByNim", query = "SELECT k FROM KaryaAkhir k WHERE k.nim = :nim"),
    @NamedQuery(name = "KaryaAkhir.findByJudulKa", query = "SELECT k FROM KaryaAkhir k WHERE k.judulKa = :judulKa"),
    @NamedQuery(name = "KaryaAkhir.findByStatusKa", query = "SELECT k FROM KaryaAkhir k WHERE k.statusKa = :statusKa")})
public class KaryaAkhir implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_KA")
    private Integer idKa;
    @Column(name = "ID_TOPIK")
    private Integer idTopik;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 8)
    @Column(name = "NIM")
    private String nim;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "JUDUL_KA")
    private String judulKa;
    @Column(name = "STATUS_KA")
    private Character statusKa;

    public KaryaAkhir() {
    }

    public KaryaAkhir(Integer idKa) {
        this.idKa = idKa;
    }

    public KaryaAkhir(Integer idKa, String nim, String judulKa) {
        this.idKa = idKa;
        this.nim = nim;
        this.judulKa = judulKa;
    }

    public Integer getIdKa() {
        return idKa;
    }

    public void setIdKa(Integer idKa) {
        this.idKa = idKa;
    }

    public Integer getIdTopik() {
        return idTopik;
    }

    public void setIdTopik(Integer idTopik) {
        this.idTopik = idTopik;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getJudulKa() {
        return judulKa;
    }

    public void setJudulKa(String judulKa) {
        this.judulKa = judulKa;
    }

    public Character getStatusKa() {
        return statusKa;
    }

    public void setStatusKa(Character statusKa) {
        this.statusKa = statusKa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idKa != null ? idKa.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof KaryaAkhir)) {
            return false;
        }
        KaryaAkhir other = (KaryaAkhir) object;
        if ((this.idKa == null && other.idKa != null) || (this.idKa != null && !this.idKa.equals(other.idKa))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.KaryaAkhir[ idKa=" + idKa + " ]";
    }
    
}
