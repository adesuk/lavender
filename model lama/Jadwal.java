package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the jadwal database table.
 * 
 */
@Entity
public class Jadwal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_jadwal")
	private Integer idJadwal;

	@Column(name="status_hasil_pelaksanaan")
	private Integer statusHasilPelaksanaan;

	@Column(name="status_pelaksanaan")
	private Integer statusPelaksanaan;

	@Temporal(TemporalType.DATE)
        @Column(name="tanggal")
	private Date tanggal;

	//bi-directional many-to-one association to KaryaAkhir
	@ManyToOne
	@JoinColumn(name="id_ka")
	private KaryaAkhir karyaAkhir;

	//bi-directional many-to-one association to Ruangan
	@ManyToOne
	@JoinColumn(name="kd_ruangan")
	private Ruangan ruangan;

	//bi-directional many-to-one association to SlotWaktu
	@ManyToOne
	@JoinColumn(name="id_slot")
	private SlotWaktu slotWaktu;

        //bi-directional many-to-many association to Dosen
	@ManyToMany
	@JoinTable(
		name="menguji"
		, joinColumns={
			@JoinColumn(name="id_jadwal")
			}
		, inverseJoinColumns={
			@JoinColumn(name="inisial_dosen")
			}
		)
	private List<Dosen> dosenpenguji;
        
        @Temporal(TemporalType.DATE)
        @Column(name="generate_date")
        private Date generateDate; // sebagai versi jadwal 
		
		@Column(name="dosen_bentrok")
        private String dosenBentrok; // sebagai versi jadwal 
        
	public Jadwal() {
	}

	public Integer getIdJadwal() {
		return this.idJadwal;
	}

	public void setIdJadwal(Integer idJadwal) {
		this.idJadwal = idJadwal;
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
		return this.dosenpenguji;
	}

	public void setDosenPenguji(List<Dosen> dosens) {
		this.dosenpenguji = dosens;
	}
        
    public Date getGenerateDate() {
		return this.generateDate;
	}

	public void setGenerateDate(Date generateDate) {
		this.generateDate = generateDate;
	}

    public String getDosenBentrok() {
		return this.dosenBentrok;
	}

	public void setDosenBentrok(String dosenBentrok) {
		this.dosenBentrok = dosenBentrok;
	}
}