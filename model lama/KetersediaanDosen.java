package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the ketersediaan_waktu_dosen database table.
 * 
 */
@Entity
@Table(name="ketersediaan_waktu_dosen")
public class KetersediaanDosen implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private KetersediaanDosenPK id;

	@Temporal(TemporalType.DATE)
	@Column(name="tanggal_dsn_sedia")
	private Date tanggalDsnSedia;

	//bi-directional many-to-one association to Dosen
	@ManyToOne
	@JoinColumn(name="inisial_dosen")
	private Dosen dosen;

	//bi-directional many-to-one association to SlotWaktu
	@ManyToOne
	@JoinColumn(name="id_slot")
	private SlotWaktu slotWaktu;

	public KetersediaanDosen() {
	}

	public KetersediaanDosenPK getId() {
		return this.id;
	}

	public void setId(KetersediaanDosenPK id) {
		this.id = id;
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