package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the MAHASISWA database table.
 * 
 */
@Entity
@NamedQuery(name="Mahasiswa.findAll", query="SELECT m FROM Mahasiswa m")
public class Mahasiswa implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="NIM")
	private String nim;

	@Column(name="JENJANG")
	private String jenjang;

	@Column(name="NAMA_MHS")
	private String namaMhs;

	//bi-directional many-to-one association to KaryaAkhir
	@OneToMany(mappedBy="mahasiswa")
	private List<KaryaAkhir> karyaAkhirs;

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

	public List<KaryaAkhir> getKaryaAkhirs() {
		return this.karyaAkhirs;
	}

	public void setKaryaAkhirs(List<KaryaAkhir> karyaAkhirs) {
		this.karyaAkhirs = karyaAkhirs;
	}

	public KaryaAkhir addKaryaAkhir(KaryaAkhir karyaAkhir) {
		getKaryaAkhirs().add(karyaAkhir);
		karyaAkhir.setMahasiswa(this);

		return karyaAkhir;
	}

	public KaryaAkhir removeKaryaAkhir(KaryaAkhir karyaAkhir) {
		getKaryaAkhirs().remove(karyaAkhir);
		karyaAkhir.setMahasiswa(null);

		return karyaAkhir;
	}

}