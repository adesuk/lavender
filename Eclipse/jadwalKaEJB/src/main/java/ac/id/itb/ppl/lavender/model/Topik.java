package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the TOPIK database table.
 * 
 */
@Entity
@NamedQuery(name="Topik.findAll", query="SELECT t FROM Topik t")
public class Topik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID_TOPIK")
	private long idTopik;

	@Column(name="BIDANG")
	private String bidang;

	@Column(name="NAMA_TOPIK")
	private String namaTopik;

	//bi-directional many-to-one association to KaryaAkhir
	@OneToMany(mappedBy="topik")
	private List<KaryaAkhir> karyaAkhirs;

	//bi-directional many-to-many association to Dosen
	@ManyToMany
	@JoinTable(
		name="REFERENCE"
		, joinColumns={
			@JoinColumn(name="ID_TOPIK")
			}
		, inverseJoinColumns={
			@JoinColumn(name="INISIAL_DOSEN")
			}
		)
	private List<Dosen> dosens;

	public Topik() {
	}

	public long getIdTopik() {
		return this.idTopik;
	}

	public void setIdTopik(long idTopik) {
		this.idTopik = idTopik;
	}

	public String getBidang() {
		return this.bidang;
	}

	public void setBidang(String bidang) {
		this.bidang = bidang;
	}

	public String getNamaTopik() {
		return this.namaTopik;
	}

	public void setNamaTopik(String namaTopik) {
		this.namaTopik = namaTopik;
	}

	public List<KaryaAkhir> getKaryaAkhirs() {
		return this.karyaAkhirs;
	}

	public void setKaryaAkhirs(List<KaryaAkhir> karyaAkhirs) {
		this.karyaAkhirs = karyaAkhirs;
	}

	public KaryaAkhir addKaryaAkhir(KaryaAkhir karyaAkhir) {
		getKaryaAkhirs().add(karyaAkhir);
		karyaAkhir.setTopik(this);

		return karyaAkhir;
	}

	public KaryaAkhir removeKaryaAkhir(KaryaAkhir karyaAkhir) {
		getKaryaAkhirs().remove(karyaAkhir);
		karyaAkhir.setTopik(null);

		return karyaAkhir;
	}

	public List<Dosen> getDosens() {
		return this.dosens;
	}

	public void setDosens(List<Dosen> dosens) {
		this.dosens = dosens;
	}

}