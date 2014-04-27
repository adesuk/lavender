package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ruangan database table.
 * 
 */
@Entity
public class Ruangan implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="kd_ruangan")
	private String kdRuangan;

	@Column(name="nama_ruangan")
	private String namaRuangan;

	//bi-directional many-to-one association to Jadwal
	@OneToMany(mappedBy="ruangan")
	private List<Jadwal> jadwals;

        //bi-directional many-to-one association to KetersediaanDosen
	private List<KetersediaanRuangan> ketersediaanWaktuRuangan;


	public Ruangan() {
	}

	public String getKdRuangan() {
		return this.kdRuangan;
	}

	public void setKdRuangan(String kdRuangan) {
		this.kdRuangan = kdRuangan;
	}

	public String getNamaRuangan() {
		return this.namaRuangan;
	}

	public void setNamaRuangan(String namaRuangan) {
		this.namaRuangan = namaRuangan;
	}

	public List<Jadwal> getJadwals() {
		return this.jadwals;
	}

	public void setJadwals(List<Jadwal> jadwals) {
		this.jadwals = jadwals;
	}

	public Jadwal addJadwal(Jadwal jadwal) {
		getJadwals().add(jadwal);
		jadwal.setRuangan(this);

		return jadwal;
	}

	public Jadwal removeJadwal(Jadwal jadwal) {
		getJadwals().remove(jadwal);
		jadwal.setRuangan(null);

		return jadwal;
	}

    public List<KetersediaanRuangan> getKetersediaanWaktuRuangan() {
        return ketersediaanWaktuRuangan;
    }

    public void setKetersediaanWaktuRuangan(List<KetersediaanRuangan> ketersediaanWaktuRuangan) {
        this.ketersediaanWaktuRuangan = ketersediaanWaktuRuangan;
    }

}