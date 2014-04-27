package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


/**
 * The persistent class for the dosen database table.
 * 
 */
@Entity
public class Dosen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="inisial_dosen")
	private String inisialDosen;

	@Column(name="gelar_belakang")
	private String gelarBelakang;

	@Column(name="gelar_depan")
	private String gelarDepan;

	@Column(name="nama_dosen")
	private String namaDosen;

	private String status;

	//bi-directional many-to-one association to KetersediaanDosen
	@OneToMany(mappedBy="dosen")
	private List<KetersediaanDosen> ketersediaanWaktuDosens;
        
        //bi-directional many-to-many association to Topik
	@ManyToMany(mappedBy="dosens")
	private List<Topik> bidangKeahlian;

	public Dosen() {
	}

	public String getInisialDosen() {
		return this.inisialDosen;
	}

	public void setInisialDosen(String inisialDosen) {
		this.inisialDosen = inisialDosen;
	}

	public String getGelarBelakang() {
		return this.gelarBelakang;
	}

	public void setGelarBelakang(String gelarBelakang) {
		this.gelarBelakang = gelarBelakang;
	}

	public String getGelarDepan() {
		return this.gelarDepan;
	}

	public void setGelarDepan(String gelarDepan) {
		this.gelarDepan = gelarDepan;
	}

	public String getNamaDosen() {
		return this.namaDosen;
	}

	public void setNamaDosen(String namaDosen) {
		this.namaDosen = namaDosen;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<KetersediaanDosen> getKetersediaanWaktuDosens() {
		return this.ketersediaanWaktuDosens;
	}

	public void setKetersediaanWaktuDosens(List<KetersediaanDosen> ketersediaanWaktuDosens) {
		this.ketersediaanWaktuDosens = ketersediaanWaktuDosens;
	}

	public KetersediaanDosen addKetersediaanWaktuDosen(KetersediaanDosen ketersediaanWaktuDosen) {
		getKetersediaanWaktuDosens().add(ketersediaanWaktuDosen);
		ketersediaanWaktuDosen.setDosen(this);

		return ketersediaanWaktuDosen;
	}

	public KetersediaanDosen removeKetersediaanWaktuDosen(KetersediaanDosen ketersediaanWaktuDosen) {
		getKetersediaanWaktuDosens().remove(ketersediaanWaktuDosen);
		ketersediaanWaktuDosen.setDosen(null);

		return ketersediaanWaktuDosen;
	}
        
        public List<Topik> getBidangKeahlian() {
		return this.bidangKeahlian;
	}

	public void setBidangKeahlian(List<Topik> topiks) {
		this.bidangKeahlian = topiks;
	}
}