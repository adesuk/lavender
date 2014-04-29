package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the DOSEN database table.
 * 
 */
@Entity
@NamedQuery(name="Dosen.findAll", query="SELECT d FROM Dosen d")
public class Dosen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INISIAL_DOSEN")
	private String inisialDosen;

	@Column(name="GELAR_BELAKANG")
	private String gelarBelakang;

	@Column(name="GELAR_DEPAN")
	private String gelarDepan;

	@Column(name="NAMA_DOSEN")
	private String namaDosen;

	@Column(name="STATUS")
	private String status;

	//bi-directional many-to-one association to KetersediaanWaktuDosen
	@OneToMany(mappedBy="dosen")
	private List<KetersediaanDosen> ketersediaanWaktuDosens;

	//bi-directional many-to-many association to KaryaAkhir
	@ManyToMany(mappedBy="dosens")
	private List<KaryaAkhir> karyaAkhirs;

	//bi-directional many-to-many association to JadwalKuliah
	@ManyToMany(mappedBy="dosens")
	private List<JadwalKuliah> jadwalKuliahs;

	//bi-directional many-to-many association to Jadwal
	@ManyToMany(mappedBy="dosens")
	private List<Jadwal> jadwals;

	//bi-directional many-to-many association to Topik
	@ManyToMany(mappedBy="dosens")
	private List<Topik> topiks;

	public Dosen() {
	}

	public String getInisialDosen() {
		return this.inisialDosen;
	}

	public void setInisialDosen(String inisialDosen) {
		this.inisialDosen = inisialDosen;
	}

	public String getGelarBelakang() {
		return this.gelarBelakang;
	}

	public void setGelarBelakang(String gelarBelakang) {
		this.gelarBelakang = gelarBelakang;
	}

	public String getGelarDepan() {
		return this.gelarDepan;
	}

	public void setGelarDepan(String gelarDepan) {
		this.gelarDepan = gelarDepan;
	}

	public String getNamaDosen() {
		return this.namaDosen;
	}

	public void setNamaDosen(String namaDosen) {
		this.namaDosen = namaDosen;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<KetersediaanDosen> getKetersediaanWaktuDosens() {
		return this.ketersediaanWaktuDosens;
	}

	public void setKetersediaanWaktuDosens(List<KetersediaanDosen> ketersediaanWaktuDosens) {
		this.ketersediaanWaktuDosens = ketersediaanWaktuDosens;
	}

	public KetersediaanDosen addKetersediaanWaktuDosen(KetersediaanDosen ketersediaanWaktuDosen) {
		getKetersediaanWaktuDosens().add(ketersediaanWaktuDosen);
		ketersediaanWaktuDosen.setDosen(this);

		return ketersediaanWaktuDosen;
	}

	public KetersediaanDosen removeKetersediaanWaktuDosen(KetersediaanDosen ketersediaanWaktuDosen) {
		getKetersediaanWaktuDosens().remove(ketersediaanWaktuDosen);
		ketersediaanWaktuDosen.setDosen(null);

		return ketersediaanWaktuDosen;
	}

	public List<KaryaAkhir> getKaryaAkhirs() {
		return this.karyaAkhirs;
	}

	public void setKaryaAkhirs(List<KaryaAkhir> karyaAkhirs) {
		this.karyaAkhirs = karyaAkhirs;
	}

	public List<JadwalKuliah> getJadwalKuliahs() {
		return this.jadwalKuliahs;
	}

	public void setJadwalKuliahs(List<JadwalKuliah> jadwalKuliahs) {
		this.jadwalKuliahs = jadwalKuliahs;
	}

	public List<Jadwal> getJadwals() {
		return this.jadwals;
	}

	public void setJadwals(List<Jadwal> jadwals) {
		this.jadwals = jadwals;
	}

	public List<Topik> getBidangKeahlian() {
		return this.topiks;
	}

	public void setBidangKeahlian(List<Topik> topiks) {
		this.topiks = topiks;
	}

	@Override
	public String toString() {
		return inisialDosen+ "-"+ namaDosen;
	}
}