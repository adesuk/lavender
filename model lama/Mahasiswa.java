package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the mahasiswa database table.
 * 
 */
@Entity
public class Mahasiswa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private String nim;

	private String jenjang;

	@Column(name="nama_mhs")
	private String namaMhs;

	//bi-directional one-to-one association to KaryaAkhir
	@OneToOne
	@JoinColumn(name="nim", referencedColumnName="nim")
	private KaryaAkhir karyaAkhir;

	public Mahasiswa() {
	}

	public String getNim() {
		return this.nim;
	}

	public void setNim(String nim) {
		this.nim = nim;
	}

	public String getJenjang() {
		return this.jenjang;
	}

	public void setJenjang(String jenjang) {
		this.jenjang = jenjang;
	}

	public String getNamaMhs() {
		return this.namaMhs;
	}

	public void setNamaMhs(String namaMhs) {
		this.namaMhs = namaMhs;
	}

	public KaryaAkhir getKaryaAkhir() {
		return this.karyaAkhir;
	}

	public void setKaryaAkhir(KaryaAkhir karyaAkhir) {
		this.karyaAkhir = karyaAkhir;
	}

}