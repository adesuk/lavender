package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;

import javax.persistence.*;

import java.sql.Timestamp;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the JADWAL database table.
 * 
 */
@Entity
@NamedQuery(name="Jadwal.findAll", query="SELECT j FROM Jadwal j")
public class Jadwal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_JADWAL")
	private long idJadwal;

	@Column(name = "GENERATE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date generateDate;

	@Column(name="STATUS_HASIL_PELAKSANAAN")
	private Integer statusHasilPelaksanaan;

	@Column(name="STATUS_PELAKSANAAN")
	private Integer statusPelaksanaan;

	@Temporal(TemporalType.DATE)
	private Date tanggal;

	//bi-directional many-to-one association to KaryaAkhir
	@ManyToOne
	@JoinColumn(name="ID_KA")
	private KaryaAkhir karyaAkhir;

	//bi-directional many-to-one association to Periode
	@ManyToOne
	@JoinColumn(name="ID_PERIODE")
	private Periode periode;

	//bi-directional many-to-one association to Ruangan
	@ManyToOne
	@JoinColumn(name="KD_RUANGAN")
	private Ruangan ruangan;

	//bi-directional many-to-one association to SlotWaktu
	@ManyToOne
	@JoinColumn(name="ID_SLOT")
	private SlotWaktu slotWaktu;

	//bi-directional many-to-many association to Dosen
	@ManyToMany
	@JoinTable(
		name="MENGUJI"
		, joinColumns={
			@JoinColumn(name="ID_JADWAL")
			}
		, inverseJoinColumns={
			@JoinColumn(name="INISIAL_DOSEN")
			}
		)
	private List<Dosen> dosens;
	
	@Column(name="DOSEN_BENTROK")
    private String dosenBentrok; // sebagai versi jadwal 
	
	public Jadwal() {
	}

	public long getIdJadwal() {
		return this.idJadwal;
	}

	public void setIdJadwal(long idJadwal) {
		this.idJadwal = idJadwal;
	}

	public Date getGenerateDate() {
		return this.generateDate;
	}

	public void setGenerateDate(Date generateDate) {
		this.generateDate = generateDate;
	}

	public Integer getStatusHasilPelaksanaan() {
		return this.statusHasilPelaksanaan;
	}

	public void setStatusHasilPelaksanaan(Integer statusHasilPelaksanaan) {
		this.statusHasilPelaksanaan = statusHasilPelaksanaan;
	}

	public Integer getStatusPelaksanaan() {
		return this.statusPelaksanaan;
	}

	public void setStatusPelaksanaan(Integer statusPelaksanaan) {
		this.statusPelaksanaan = statusPelaksanaan;
	}

	public Date getTanggal() {
		return this.tanggal;
	}

	public void setTanggal(Date tanggal) {
		this.tanggal = tanggal;
	}

	public KaryaAkhir getKaryaAkhir() {
		return this.karyaAkhir;
	}

	public void setKaryaAkhir(KaryaAkhir karyaAkhir) {
		this.karyaAkhir = karyaAkhir;
	}

	public Periode getPeriode() {
		return this.periode;
	}

	public void setPeriode(Periode periode) {
		this.periode = periode;
	}

	public Ruangan getRuangan() {
		return this.ruangan;
	}

	public void setRuangan(Ruangan ruangan) {
		this.ruangan = ruangan;
	}

	public SlotWaktu getSlotWaktu() {
		return this.slotWaktu;
	}

	public void setSlotWaktu(SlotWaktu slotWaktu) {
		this.slotWaktu = slotWaktu;
	}

	public List<Dosen> getDosensPenguji() {
		return this.dosens;
	}

	public void setDosensPenguji(List<Dosen> dosens) {
		this.dosens = dosens;
	}
	
	public String getDosenBentrok() {
		return this.dosenBentrok;
	}

	public void setDosenBentrok(String dosenBentrok) {
		this.dosenBentrok = dosenBentrok;
	}

}