package ac.id.itb.ppl.lavender.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the ketersediaan_waktu_dosen database table.
 * 
 */
@Embeddable
public class KetersediaanDosenPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="inisial_dosen")
	private String inisialDosen;

	@Column(name="id_slot")
	private Integer idSlot;

	public KetersediaanDosenPK() {
	}
	public String getInisialDosen() {
		return this.inisialDosen;
	}
	public void setInisialDosen(String inisialDosen) {
		this.inisialDosen = inisialDosen;
	}
	public Integer getIdSlot() {
		return this.idSlot;
	}
	public void setIdSlot(Integer idSlot) {
		this.idSlot = idSlot;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof KetersediaanDosenPK)) {
			return false;
		}
		KetersediaanDosenPK castOther = (KetersediaanDosenPK)other;
		return 
			this.inisialDosen.equals(castOther.inisialDosen)
			&& this.idSlot.equals(castOther.idSlot);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.inisialDosen.hashCode();
		hash = hash * prime + this.idSlot.hashCode();
		
		return hash;
	}
}