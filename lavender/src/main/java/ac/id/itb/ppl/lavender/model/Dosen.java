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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
@Table(name = "DOSEN")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dosen.findAll", query = "SELECT d FROM Dosen d"),
    @NamedQuery(name = "Dosen.findByInisialDosen", query = "SELECT d FROM Dosen d WHERE d.inisialDosen = :inisialDosen"),
    @NamedQuery(name = "Dosen.findByNamaDosen", query = "SELECT d FROM Dosen d WHERE d.namaDosen = :namaDosen"),
    @NamedQuery(name = "Dosen.findByStatus", query = "SELECT d FROM Dosen d WHERE d.status = :status"),
    @NamedQuery(name = "Dosen.findByGelarDepan", query = "SELECT d FROM Dosen d WHERE d.gelarDepan = :gelarDepan"),
    @NamedQuery(name = "Dosen.findByGelarBelakang", query = "SELECT d FROM Dosen d WHERE d.gelarBelakang = :gelarBelakang")})
public class Dosen implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 3)
    @Column(name = "INISIAL_DOSEN")
    private String inisialDosen;
    @Size(max = 50)
    @Column(name = "NAMA_DOSEN")
    private String namaDosen;
    @Size(max = 10)
    @Column(name = "STATUS")
    private String status;
    @Size(max = 30)
    @Column(name = "GELAR_DEPAN")
    private String gelarDepan;
    @Size(max = 30)
    @Column(name = "GELAR_BELAKANG")
    private String gelarBelakang;
    @JoinTable(name = "MENGAJAR", joinColumns = {
        @JoinColumn(name = "INISIAL_DOSEN", referencedColumnName = "INISIAL_DOSEN")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_JADWAL_KULIAH", referencedColumnName = "ID_JADWAL_KULIAH")})
    @ManyToMany
    private List<JadwalKuliah> jadwalKuliahList;
    @JoinTable(name = "REFERENCE", joinColumns = {
        @JoinColumn(name = "INISIAL_DOSEN", referencedColumnName = "INISIAL_DOSEN")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_TOPIK", referencedColumnName = "ID_TOPIK")})
    @ManyToMany
    private List<Topik> bidangKeahlian;
    @JoinTable(name = "MENGUJI", joinColumns = {
        @JoinColumn(name = "INISIAL_DOSEN", referencedColumnName = "INISIAL_DOSEN")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_JADWAL", referencedColumnName = "ID_JADWAL")})
    @ManyToMany
    private List<Jadwal> jadwalList;
    @JoinTable(name = "MEMBIMBING", joinColumns = {
        @JoinColumn(name = "INISIAL_DOSEN", referencedColumnName = "INISIAL_DOSEN")}, inverseJoinColumns = {
        @JoinColumn(name = "ID_KA", referencedColumnName = "ID_KA")})
    @ManyToMany
    private List<KaryaAkhir> karyaAkhirList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dosen")
    private List<KetersediaanWaktuDosen> ketersediaanWaktuDosens;

    public Dosen() {
    }

    public Dosen(String inisialDosen) {
        this.inisialDosen = inisialDosen;
    }

    public String getInisialDosen() {
        return inisialDosen;
    }

    public void setInisialDosen(String inisialDosen) {
        this.inisialDosen = inisialDosen;
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        this.namaDosen = namaDosen;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGelarDepan() {
        return gelarDepan;
    }

    public void setGelarDepan(String gelarDepan) {
        this.gelarDepan = gelarDepan;
    }

    public String getGelarBelakang() {
        return gelarBelakang;
    }

    public void setGelarBelakang(String gelarBelakang) {
        this.gelarBelakang = gelarBelakang;
    }

    @XmlTransient
    public List<JadwalKuliah> getJadwalKuliahList() {
        return jadwalKuliahList;
    }

    public void setJadwalKuliahList(List<JadwalKuliah> jadwalKuliahList) {
        this.jadwalKuliahList = jadwalKuliahList;
    }

    @XmlTransient
    public List<Topik> getBidangKeahlian() {
        return bidangKeahlian;
    }

    public void setBidangKeahlian(List<Topik> bidangKeahlian) {
        this.bidangKeahlian = bidangKeahlian;
    }

    @XmlTransient
    public List<Jadwal> getJadwalList() {
        return jadwalList;
    }

    public void setJadwalList(List<Jadwal> jadwalList) {
        this.jadwalList = jadwalList;
    }

    @XmlTransient
    public List<KaryaAkhir> getKaryaAkhirList() {
        return karyaAkhirList;
    }

    public void setKaryaAkhirList(List<KaryaAkhir> karyaAkhirList) {
        this.karyaAkhirList = karyaAkhirList;
    }

    @XmlTransient
    public List<KetersediaanWaktuDosen> getKetersediaanWaktuDosens() {
        return ketersediaanWaktuDosens;
    }

    public void setKetersediaanWaktuDosens(List<KetersediaanWaktuDosen> ketersediaanWaktuDosens) {
        this.ketersediaanWaktuDosens = ketersediaanWaktuDosens;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inisialDosen != null ? inisialDosen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dosen)) {
            return false;
        }
        Dosen other = (Dosen) object;
        if ((this.inisialDosen == null && other.inisialDosen != null) || (this.inisialDosen != null && !this.inisialDosen.equals(other.inisialDosen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ac.id.itb.ppl.lavender.model.Dosen[ inisialDosen=" + inisialDosen + " ]";
    }
    
}
