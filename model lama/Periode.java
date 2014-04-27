package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the periode database table.
 * 
 */
@Entity
public class Periode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_periode")
	private Integer idPeriode;

	@Column(name="nama_periode")
	private String namaPeriode;

	@Temporal(TemporalType.DATE)
	@Column(name="periode_akhir")
	private Date periodeAkhir;

	@Temporal(TemporalType.DATE)
	@Column(name="periode_awal")
	private Date periodeAwal;

	@Column(name="status_jadwal")
	private String statusJadwal;

	@Column(name="status_rilis")
	private Integer statusRilis;

	@Column(name="status_verifikasi")
	private Integer statusVerifikasi;

	@Column(name="tipe_jadwal")
	private String tipeJadwal;

	//bi-directional many-to-one association to Jadwal
	@OneToMany(mappedBy="periode")
	private List<Jadwal> jadwals;

	public Periode() {
	}

	public Integer getIdPeriode() {
		return this.idPeriode;
	}

	public void setIdPeriode(Integer idPeriode) {
		this.idPeriode = idPeriode;
	}

	public String getNamaPeriode() {
		return this.namaPeriode;
	}

	public void setNamaPeriode(String namaPeriode) {
		this.namaPeriode = namaPeriode;
	}

	public Date getPeriodeAkhir() {
		return this.periodeAkhir;
	}

	public void setPeriodeAkhir(Date periodeAkhir) {
		this.periodeAkhir = periodeAkhir;
	}

	public Date getPeriodeAwal() {
		return this.periodeAwal;
	}

	public void setPeriodeAwal(Date periodeAwal) {
		this.periodeAwal = periodeAwal;
	}

	public String getStatusJadwal() {
		return this.statusJadwal;
	}

	public void setStatusJadwal(String statusJadwal) {
		this.statusJadwal = statusJadwal;
	}

	public Integer getStatusRilis() {
		return this.statusRilis;
	}

	public void setStatusRilis(Integer statusRilis) {
		this.statusRilis = statusRilis;
	}

	public Integer getStatusVerifikasi() {
		return this.statusVerifikasi;
	}

	public void setStatusVerifikasi(Integer statusVerifikasi) {
		this.statusVerifikasi = statusVerifikasi;
	}

	public String getTipeJadwal() {
		return this.tipeJadwal;
	}

	public void setTipeJadwal(String tipeJadwal) {
		this.tipeJadwal = tipeJadwal;
	}

	public List<Jadwal> getJadwals() {
		return this.jadwals;
	}

	public void setJadwals(List<Jadwal> jadwals) {
		this.jadwals = jadwals;
	}

	public Jadwal addJadwal(Jadwal jadwal) {
		getJadwals().add(jadwal);
		return jadwal;
	}

	public Jadwal removeJadwal(Jadwal jadwal) {
		getJadwals().remove(jadwal);
		return jadwal;
	}

}