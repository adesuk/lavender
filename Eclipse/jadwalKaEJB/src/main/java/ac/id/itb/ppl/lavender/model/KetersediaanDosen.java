package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the KETERSEDIAAN_WAKTU_DOSEN database table.
 * 
 */
@Entity
@Table(name="KETERSEDIAAN_WAKTU_DOSEN")
@NamedQuery(name="KetersediaanWaktuDosen.findAll", query="SELECT k FROM KetersediaanDosen k")
public class KetersediaanDosen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_KWD")
	private long idKwd;

	@Temporal(TemporalType.DATE)
	@Column(name="TANGGAL_DSN_SEDIA")
	private Date tanggalDsnSedia;

	//bi-directional many-to-one association to Dosen
	@ManyToOne
	@JoinColumn(name="INISIAL_DOSEN")
	private Dosen dosen;

	//bi-directional many-to-one association to SlotWaktu
	@ManyToOne
	@JoinColumn(name="ID_SLOT")
	private SlotWaktu slotWaktu;

	public KetersediaanDosen() {
	}

	public long getIdKwd() {
		return this.idKwd;
	}

	public void setIdKwd(long idKwd) {
		this.idKwd = idKwd;
	}

	public Date getTanggalDsnSedia() {
		return this.tanggalDsnSedia;
	}

	public void setTanggalDsnSedia(Date tanggalDsnSedia) {
		this.tanggalDsnSedia = tanggalDsnSedia;
	}

	public Dosen getDosen() {
		return this.dosen;
	}

	public void setDosen(Dosen dosen) {
		this.dosen = dosen;
	}

	public SlotWaktu getSlotWaktu() {
		return this.slotWaktu;
	}

	public void setSlotWaktu(SlotWaktu slotWaktu) {
		this.slotWaktu = slotWaktu;
	}

}