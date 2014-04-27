package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the periode_kuliah database table.
 * 
 */
@Entity
@Table(name="periode_kuliah")
public class PeriodeKuliah implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_periode_kuliah")
	private Integer idPeriodeKuliah;

	private Integer semester;

	@Column(name="tahun_akademik")
	private String tahunAkademik;

	@Temporal(TemporalType.DATE)
	private Date versi;

	@Temporal(TemporalType.DATE)
	@Column(name="waktu_akhir_kuliah")
	private Date waktuAkhirKuliah;

	@Temporal(TemporalType.DATE)
	@Column(name="waktu_mulai_kuliah")
	private Date waktuMulaiKuliah;

	//bi-directional many-to-one association to JadwalKuliah
	@OneToMany(mappedBy="periodeKuliah")
	private List<JadwalKuliah> jadwalKuliahs;

	public PeriodeKuliah() {
	}

	public Integer getIdPeriodeKuliah() {
		return this.idPeriodeKuliah;
	}

	public void setIdPeriodeKuliah(Integer idPeriodeKuliah) {
		this.idPeriodeKuliah = idPeriodeKuliah;
	}

	public Integer getSemester() {
		return this.semester;
	}

	public void setSemester(Integer semester) {
		this.semester = semester;
	}

	public String getTahunAkademik() {
		return this.tahunAkademik;
	}

	public void setTahunAkademik(String tahunAkademik) {
		this.tahunAkademik = tahunAkademik;
	}

	public Date getVersi() {
		return this.versi;
	}

	public void setVersi(Date versi) {
		this.versi = versi;
	}

	public Date getWaktuAkhirKuliah() {
		return this.waktuAkhirKuliah;
	}

	public void setWaktuAkhirKuliah(Date waktuAkhirKuliah) {
		this.waktuAkhirKuliah = waktuAkhirKuliah;
	}

	public Date getWaktuMulaiKuliah() {
		return this.waktuMulaiKuliah;
	}

	public void setWaktuMulaiKuliah(Date waktuMulaiKuliah) {
		this.waktuMulaiKuliah = waktuMulaiKuliah;
	}

	public List<JadwalKuliah> getJadwalKuliahs() {
		return this.jadwalKuliahs;
	}

	public void setJadwalKuliahs(List<JadwalKuliah> jadwalKuliahs) {
		this.jadwalKuliahs = jadwalKuliahs;
	}

	public JadwalKuliah addJadwalKuliah(JadwalKuliah jadwalKuliah) {
		getJadwalKuliahs().add(jadwalKuliah);
		jadwalKuliah.setPeriodeKuliah(this);

		return jadwalKuliah;
	}

	public JadwalKuliah removeJadwalKuliah(JadwalKuliah jadwalKuliah) {
		getJadwalKuliahs().remove(jadwalKuliah);
		jadwalKuliah.setPeriodeKuliah(null);

		return jadwalKuliah;
	}

}