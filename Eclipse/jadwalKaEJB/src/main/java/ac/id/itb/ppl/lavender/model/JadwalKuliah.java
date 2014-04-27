package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the JADWAL_KULIAH database table.
 * 
 */
@Entity
@Table(name="JADWAL_KULIAH")
@NamedQuery(name="JadwalKuliah.findAll", query="SELECT j FROM JadwalKuliah j")
public class JadwalKuliah implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_JADWAL_KULIAH")
	private long idJadwalKuliah;

	@Column(name="HARI")
	private String hari;

	@Column(name="KODE_MATA_KULIAH")
	private String kodeMataKuliah;

	@Column(name="WAKTU_KELUAR")
	private Timestamp waktuKeluar;

	@Column(name="WAKTU_MASUK")
	private Timestamp waktuMasuk;

	//bi-directional many-to-one association to PeriodeKuliah
	@ManyToOne
	@JoinColumn(name="ID_PERIODE_KULIAH")
	private PeriodeKuliah periodeKuliah;

	//bi-directional many-to-one association to Ruangan
	@ManyToOne
	@JoinColumn(name="KD_RUANGAN")
	private Ruangan ruangan;

	//bi-directional many-to-many association to Dosen
	@ManyToMany
	@JoinTable(
		name="MENGAJAR"
		, joinColumns={
			@JoinColumn(name="ID_JADWAL_KULIAH")
			}
		, inverseJoinColumns={
			@JoinColumn(name="INISIAL_DOSEN")
			}
		)
	private List<Dosen> dosens;

	public JadwalKuliah() {
	}

	public long getIdJadwalKuliah() {
		return this.idJadwalKuliah;
	}

	public void setIdJadwalKuliah(long idJadwalKuliah) {
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