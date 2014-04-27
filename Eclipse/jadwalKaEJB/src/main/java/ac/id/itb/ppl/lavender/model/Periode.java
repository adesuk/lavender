package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the PERIODE database table.
 * 
 */
@Entity
@NamedQuery(name="Periode.findAll", query="SELECT p FROM Periode p")
public class Periode implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_PERIODE")
	private long idPeriode;

	@Column(name="NAMA_PERIODE")
	private String namaPeriode;

	@Temporal(TemporalType.DATE)
	@Column(name="PERIODE_AKHIR")
	private Date periodeAkhir;

	@Temporal(TemporalType.DATE)
	@Column(name="PERIODE_AWAL")
	private Date periodeAwal;

	@Column(name="STATUS_JADWAL")
	private String statusJadwal;

	@Column(name="STATUS_RILIS")
	private BigDecimal statusRilis;

	@Column(name="STATUS_VERIFIKASI")
	private BigDecimal statusVerifikasi;

	@Column(name="TIPE_JADWAL")
	private String tipeJadwal;

	//bi-directional many-to-one association to Jadwal
	@OneToMany(mappedBy="periode")
	private List<Jadwal> jadwals;

	public Periode() {
	}

	public long getIdPeriode() {
		return this.idPeriode;
	}

	public void setIdPeriode(long idPeriode) {
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

	public BigDecimal getStatusRilis() {
		return this.statusRilis;
	}

	public void setStatusRilis(BigDecimal statusRilis) {
		this.statusRilis = statusRilis;
	}

	public BigDecimal getStatusVerifikasi() {
		return this.statusVerifikasi;
	}

	public void setStatusVerifikasi(BigDecimal statusVerifikasi) {
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
		jadwal.setPeriode(this);

		return jadwal;
	}

	public Jadwal removeJadwal(Jadwal jadwal) {
		getJadwals().remove(jadwal);
		jadwal.setPeriode(null);

		return jadwal;
	}

}