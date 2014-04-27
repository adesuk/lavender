package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the topik database table.
 * 
 */
@Entity
public class Topik implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_topik")
	private Integer idTopik;

	private String bidang;

	@Column(name="nama_topik")
	private String namaTopik;

	public Topik() {
	}

	public Integer getIdTopik() {
		return this.idTopik;
	}

	public void setIdTopik(Integer idTopik) {
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
}