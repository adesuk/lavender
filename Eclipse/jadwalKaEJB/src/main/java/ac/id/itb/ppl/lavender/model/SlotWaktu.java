package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.List;


/**
 * The persistent class for the SLOT_WAKTU database table.
 * 
 */
@Entity
@Table(name="SLOT_WAKTU")
@NamedQuery(name="SlotWaktu.findAll", query="SELECT s FROM SlotWaktu s")
public class SlotWaktu implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_SLOT")
	private long idSlot;

	@Column(name="JAM_AKHIR")
	private Timestamp jamAkhir;

	@Column(name="JAM_AWAL")
	private Timestamp jamAwal;

	@Column(name="SESI")
	private Short sesi;

	//bi-directional many-to-one association to Jadwal
	@OneToMany(mappedBy="slotWaktu")
	private List<Jadwal> jadwals;

	//bi-directional many-to-one association to KetersediaanWaktuDosen
	@OneToMany(mappedBy="slotWaktu")
	private List<KetersediaanDosen> ketersediaanWaktuDosens;

	public SlotWaktu() {
	}

	public long getIdSlot() {
		return this.idSlot;
	}

	public void setIdSlot(long idSlot) {
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

	public Short getSesi() {
		return this.sesi;
	}

	public void setSesi(Short sesi) {
		this.sesi = sesi;
	}

	public List<Jadwal> getJadwals() {
		return this.jadwals;
	}

	public void setJadwals(List<Jadwal> jadwals) {
		this.jadwals = jadwals;
	}

	public Jadwal addJadwal(Jadwal jadwal) {
		getJadwals().add(jadwal);
		jadwal.setSlotWaktu(this);

		return jadwal;
	}

	public Jadwal removeJadwal(Jadwal jadwal) {
		getJadwals().remove(jadwal);
		jadwal.setSlotWaktu(null);

		return jadwal;
	}

	public List<KetersediaanDosen> getKetersediaanWaktuDosens() {
		return this.ketersediaanWaktuDosens;
	}

	public void setKetersediaanWaktuDosens(List<KetersediaanDosen> ketersediaanWaktuDosens) {
		this.ketersediaanWaktuDosens = ketersediaanWaktuDosens;
	}

	public KetersediaanDosen addKetersediaanWaktuDosen(KetersediaanDosen ketersediaanWaktuDosen) {
		getKetersediaanWaktuDosens().add(ketersediaanWaktuDosen);
		ketersediaanWaktuDosen.setSlotWaktu(this);

		return ketersediaanWaktuDosen;
	}

	public KetersediaanDosen removeKetersediaanWaktuDosen(KetersediaanDosen ketersediaanWaktuDosen) {
		getKetersediaanWaktuDosens().remove(ketersediaanWaktuDosen);
		ketersediaanWaktuDosen.setSlotWaktu(null);

		return ketersediaanWaktuDosen;
	}

}