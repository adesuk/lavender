package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the slot_waktu database table.
 * 
 */
@Entity
@Table(name="slot_waktu")
public class SlotWaktu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_slot")
	private Integer idSlot;

	@Column(name="jam_akhir")
	private Timestamp jamAkhir;

	@Column(name="jam_awal")
	private Timestamp jamAwal;

	private Integer sesi;

	public SlotWaktu() {
	}

	public Integer getIdSlot() {
		return this.idSlot;
	}

	public void setIdSlot(Integer idSlot) {
		this.idSlot = idSlot;
	}

	public Timestamp getJamAkhir() {
		return this.jamAkhir;
	}

	public void setJamAkhir(Timestamp jamAkhir) {
		this.jamAkhir = jamAkhir;
	}

	public Timestamp getJamAwal() {
		return this.jamAwal;
	}

	public void setJamAwal(Timestamp jamAwal) {
		this.jamAwal = jamAwal;
	}

	public Integer getSesi() {
		return this.sesi;
	}

	public void setSesi(Integer sesi) {
		this.sesi = sesi;
	}
}