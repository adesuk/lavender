package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the jadwal_kuliah database table.
 * 
 */
@Entity
@Table(name="jadwal_kuliah")
public class JadwalKuliah implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_jadwal_kuliah")
	private Integer idJadwalKuliah;

	private String hari;

	@Column(name="kode_mata_kuliah")
	private String kodeMataKuliah;

	@Column(name="waktu_keluar")
	private Timestamp waktuKeluar;

	@Column(name="waktu_masuk")
	private Timestamp waktuMasuk;

	//bi-directional many-to-one association to PeriodeKuliah
	@ManyToOne
	@JoinColumn(name="id_periode_kuliah")
	private PeriodeKuliah periodeKuliah;

	//bi-directional many-to-one association to Ruangan
	@ManyToOne
	@JoinColumn(name="kd_ruangan")
	private Ruangan ruangan;

	//bi-directional many-to-many association to Dosen
	@ManyToMany
	@JoinTable(
		name="mengajar"
		, joinColumns={
			@JoinColumn(name="id_jadwal_kuliah")
			}
		, inverseJoinColumns={
			@JoinColumn(name="inisial_dosen")
			}
		)
	private List<Dosen> dosens;

	public JadwalKuliah() {
	}

	public Integer getIdJadwalKuliah() {
		return this.idJadwalKuliah;
	}

	public void setIdJadwalKuliah(Integer idJadwalKuliah) {
		this.idJadwalKuliah = idJadwalKuliah;
	}

	public String getHari() {
		return this.hari;
	}

	public void setHari(String hari) {
		this.hari = hari;
	}

	public String getKodeMataKuliah() {
		return this.kodeMataKuliah;
	}

	public void setKodeMataKuliah(String kodeMataKuliah) {
		this.kodeMataKuliah = kodeMataKuliah;
	}

	public Timestamp getWaktuKeluar() {
		return this.waktuKeluar;
	}

	public void setWaktuKeluar(Timestamp waktuKeluar) {
		this.waktuKeluar = waktuKeluar;
	}

	public Timestamp getWaktuMasuk() {
		return this.waktuMasuk;
	}

	public void setWaktuMasuk(Timestamp waktuMasuk) {
		this.waktuMasuk = waktuMasuk;
	}

	public PeriodeKuliah getPeriodeKuliah() {
		return this.periodeKuliah;
	}

	public void setPeriodeKuliah(PeriodeKuliah periodeKuliah) {
		this.periodeKuliah = periodeKuliah;
	}

	public Ruangan getRuangan() {
		return this.ruangan;
	}

	public void setRuangan(Ruangan ruangan) {
		this.ruangan = ruangan;
	}

	public List<Dosen> getDosens() {
		return this.dosens;
	}

	public void setDosens(List<Dosen> dosens) {
		this.dosens = dosens;
	}

}