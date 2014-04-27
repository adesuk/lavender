package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the karya_akhir database table.
 * 
 */
@Entity
@Table(name="karya_akhir")
public class KaryaAkhir implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_ka")
	private Integer idKa;

	@Column(name="judul_ka")
	private String judulKa;

	@Column(name="status_ka")
	private String statusKa;

	//bi-directional many-to-one association to Topik
	@ManyToOne
	@JoinColumn(name="id_topik")
	private Topik topik;

	//bi-directional many-to-many association to Dosen
	@ManyToMany
	@JoinTable(
		name="membimbing"
		, joinColumns={
			@JoinColumn(name="id_ka")
			}
		, inverseJoinColumns={
			@JoinColumn(name="inisial_dosen")
			}
		)
	private List<Dosen> dosenpembimbing;

	//bi-directional one-to-one association to Mahasiswa
	@OneToOne(mappedBy="karyaAkhir")
	private Mahasiswa mahasiswa;

	public KaryaAkhir() {
	}

	public Integer getIdKa() {
		return this.idKa;
	}

	public void setIdKa(Integer idKa) {
		this.idKa = idKa;
	}

	public String getJudulKa() {
		return this.judulKa;
	}

	public void setJudulKa(String judulKa) {
		this.judulKa = judulKa;
	}

	public String getStatusKa() {
		return this.statusKa;
	}

	public void setStatusKa(String statusKa) {
		this.statusKa = statusKa;
	}

	public Topik getTopik() {
		return this.topik;
	}

	public void setTopik(Topik topik) {
		this.topik = topik;
	}

	public List<Dosen> getDosensPembimbing() {
		return this.dosenpembimbing;
	}

	public void setDosenPembimbing(List<Dosen> dosens) {
		this.dosenpembimbing = dosens;
	}

	public Mahasiswa getMahasiswa() {
		return this.mahasiswa;
	}

	public void setMahasiswa(Mahasiswa mahasiswa) {
		this.mahasiswa = mahasiswa;
	}
}