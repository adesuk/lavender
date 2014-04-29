package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;

import javax.persistence.*;

import java.util.List;


/**
 * The persistent class for the KARYA_AKHIR database table.
 * 
 */
@Entity
@Table(name="KARYA_AKHIR")
@NamedQuery(name="KaryaAkhir.findAll", query="SELECT k FROM KaryaAkhir k")
public class KaryaAkhir implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_KA")
	private long idKa;

	@Column(name="JUDUL_KA")
	private String judulKa;

	@Column(name="STATUS_KA")
	private String statusKa;

	//bi-directional many-to-one association to Jadwal
	@OneToMany(mappedBy="karyaAkhir")
	private List<Jadwal> jadwals;

	//bi-directional many-to-one association to Mahasiswa
	@ManyToOne
	@JoinColumn(name="NIM")
	private Mahasiswa mahasiswa;

	//bi-directional many-to-one association to Topik
	@ManyToOne
	@JoinColumn(name="ID_TOPIK")
	private Topik topik;

	//bi-directional many-to-many association to Dosen
	@ManyToMany
	@JoinTable(
		name="MEMBIMBING"
		, joinColumns={
			@JoinColumn(name="ID_KA")
			}
		, inverseJoinColumns={
			@JoinColumn(name="INISIAL_DOSEN")
			}
		)
	private List<Dosen> dosens;

	public KaryaAkhir() {
	}

	public long getIdKa() {
		return this.idKa;
	}

	public void setIdKa(long idKa) {
		this.idKa = idKa;
	}

	public String getJudulKa() {
		return this.judulKa;
	}

	public void setJudulKa(String judulKa) {
		this.judulKa = judulKa;
	}

	public String getStatusKa() {
		return this.statusKa;
	}

	public void setStatusKa(String statusKa) {
		this.statusKa = statusKa;
	}

	public List<Jadwal> getJadwals() {
		return this.jadwals;
	}

	public void setJadwals(List<Jadwal> jadwals) {
		this.jadwals = jadwals;
	}

	public Jadwal addJadwal(Jadwal jadwal) {
		getJadwals().add(jadwal);
		jadwal.setKaryaAkhir(this);

		return jadwal;
	}

	public Jadwal removeJadwal(Jadwal jadwal) {
		getJadwals().remove(jadwal);
		jadwal.setKaryaAkhir(null);

		return jadwal;
	}

	public Mahasiswa getMahasiswa() {
		return this.mahasiswa;
	}

	public void setMahasiswa(Mahasiswa mahasiswa) {
		this.mahasiswa = mahasiswa;
	}

	public Topik getTopik() {
		return this.topik;
	}

	public void setTopik(Topik topik) {
		this.topik = topik;
	}

	public List<Dosen> getDosensPembimbing() {
		return this.dosens;
	}

	public void setDosensPembimbing(List<Dosen> dosens) {
		this.dosens = dosens;
	}

	@Override
	public String toString() {
		return idKa+ "-"+ judulKa;
	}
}