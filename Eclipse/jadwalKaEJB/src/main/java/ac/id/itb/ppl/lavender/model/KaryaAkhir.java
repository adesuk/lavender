/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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
    @NamedQuery(name = "KaryaAkhir.findByJudulKa", query = "SELECT k FROM KaryaAkhir k WHERE k.judulKa = :judulKa"),
    @NamedQuery(name = "KaryaAkhir.findByStatusKa", query = "SELECT k FROM KaryaAkhir k WHERE k.statusKa = :statusKa")})
public class KaryaAkhir implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_KA")
    private Integer idKa;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "JUDUL_KA")
    private String judulKa;
    @Column(name = "STATUS_KA")
    private Character statusKa;
    @ManyToMany(mappedBy = "karyaAkhirList")
    private List<Dosen> dosenPembimbing;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "karyaAkhir")
    private List<Jadwal> jadwalList;
    @JoinColumn(name = "ID_TOPIK", referencedColumnName = "ID_TOPIK")
    @ManyToOne
    private Topik topik;
    @JoinColumn(name = "NIM", referencedColumnName = "NIM")
    @ManyToOne(optional = false)
    private Mahasiswa mahasiswa;

    public KaryaAkhir() {
    }

    public KaryaAkhir(Integer idKa) {
        this.idKa = idKa;
    }

    public KaryaAkhir(Integer idKa, String judulKa) {
        this.idKa = idKa;
        this.judulKa = judulKa;
    }

    public Integer getIdKa() {
        return idKa;
    }

    public void setIdKa(Integer idKa) {
        this.idKa = idKa;
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

    @XmlTransient
    public List<Dosen> getDosenPembimbing() {
        return dosenPembimbing;
    }

    public void setDosenPembimbing(List<Dosen> dosenPembimbing) {
        this.dosenPembimbing = dosenPembimbing;
    }

    @XmlTransient
    public List<Jadwal> getJadwalList() {
        return jadwalList;
    }

    public void setJadwalList(List<Jadwal> jadwalList) {
        this.jadwalList = jadwalList;
    }

    public Topik getTopik() {
        return topik;
    }

    public void setTopik(Topik topik) {
        this.topik = topik;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }

    public void setMahasiswa(Mahasiswa mahasiswa) {
        this.mahasiswa = mahasiswa;
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
