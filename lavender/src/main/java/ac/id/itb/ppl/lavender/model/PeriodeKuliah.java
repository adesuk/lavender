/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TOSHIBA
 */
@Entity
@Table(name = "PERIODE_KULIAH")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PeriodeKuliah.findAll", query = "SELECT p FROM PeriodeKuliah p"),
    @NamedQuery(name = "PeriodeKuliah.findByIdPeriodeKuliah", query = "SELECT p FROM PeriodeKuliah p WHERE p.idPeriodeKuliah = :idPeriodeKuliah"),
    @NamedQuery(name = "PeriodeKuliah.findByWaktuMulaiKuliah", query = "SELECT p FROM PeriodeKuliah p WHERE p.waktuMulaiKuliah = :waktuMulaiKuliah"),
    @NamedQuery(name = "PeriodeKuliah.findByWaktuAkhirKuliah", query = "SELECT p FROM PeriodeKuliah p WHERE p.waktuAkhirKuliah = :waktuAkhirKuliah"),
    @NamedQuery(name = "PeriodeKuliah.findByVersi", query = "SELECT p FROM PeriodeKuliah p WHERE p.versi = :versi"),
    @NamedQuery(name = "PeriodeKuliah.findByTahunAkademik", query = "SELECT p FROM PeriodeKuliah p WHERE p.tahunAkademik = :tahunAkademik"),
    @NamedQuery(name = "PeriodeKuliah.findBySemester", query = "SELECT p FROM PeriodeKuliah p WHERE p.semester = :semester")})
public class PeriodeKuliah implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ID_PERIODE_KULIAH")
    private Integer idPeriodeKuliah;
    @Column(name = "WAKTU_MULAI_KULIAH")
    @Temporal(TemporalType.DATE)
    private Date waktuMulaiKuliah;
    @Column(name = "WAKTU_AKHIR_KULIAH")
    @Temporal(TemporalType.DATE)
    private Date waktuAkhirKuliah;
    @Column(name = "VERSI")
    @Temporal(TemporalType.DATE)
    private Date versi;
    @Size(max = 9)
    @Column(name = "TAHUN_AKADEMIK")
    private String tahunAkademik;
    @Column(name = "SEMESTER")
    private Short semester;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idPeriodeKuliah")
    private Collection<JadwalKuliah> jadwalKuliahCollection;

    public PeriodeKuliah() {
    }

    public PeriodeKuliah(Integer idPeriodeKuliah) {
        this.idPeriodeKuliah = idPeriodeKuliah;
    }

    public Integer getIdPeriodeKuliah() {
        return idPeriodeKuliah;
    }

    public void setIdPeriodeKuliah(Integer idPeriodeKuliah) {
        this.idPeriodeKuliah = idPeriodeKuliah;
    }

    public Date getWaktuMulaiKuliah() {
        return waktuMulaiKuliah;
    }

    public void setWaktuMulaiKuliah(Date waktuMulaiKuliah) {
        this.waktuMulaiKuliah = waktuMulaiKuliah;
    }

    public Date getWaktuAkhirKuliah() {
        return waktuAkhirKuliah;
    }

    public void setWaktuAkhirKuliah(Date waktuAkhirKuliah) {
        this.waktuAkhirKuliah = waktuAkhirKuliah;
    }

    public Date getVersi() {
        return versi;
    }

    public void setVersi(Date versi) {
        this.versi = versi;
    }

    public String getTahunAkademik() {
        return tahunAkademik;
    }

    public void setTahunAkademik(String tahunAkademik) {
        this.tahunAkademik = tahunAkademik;
    }

    public Short getSemester() {
        return semester;
    }

    public void setSemester(Short semester) {
        this.semester = semester;
    }

    @XmlTransient
    public Collection<JadwalKuliah> getJadwalKuliahCollection() {
        return jadwalKuliahCollection;
    }

    public void setJadwalKuliahCollection(Collection<JadwalKuliah> jadwalKuliahCollection) {
        this.jadwalKuliahCollection = jadwalKuliahCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPeriodeKuliah != null ? idPeriodeKuliah.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PeriodeKuliah)) {
            return false;
        }
        PeriodeKuliah other = (PeriodeKuliah) object;
        if ((this.idPeriodeKuliah == null && other.idPeriodeKuliah != null) || (this.idPeriodeKuliah != null && !this.idPeriodeKuliah.equals(other.idPeriodeKuliah))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.PeriodeKuliah[ idPeriodeKuliah=" + idPeriodeKuliah + " ]";
    }
    
}
