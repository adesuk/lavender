package com.coba.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the DOSEN database table.
 * 
 */
@Entity
@NamedQuery(name="Dosen.findAll", query="SELECT d FROM Dosen d")
public class Dosen implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="INISIAL_DOSEN")
	private String inisialDosen;

	@Column(name="GELAR_BELAKANG")
	private String gelarBelakang;

	@Column(name="GELAR_DEPAN")
	private String gelarDepan;

	@Column(name="NAMA_DOSEN")
	private String namaDosen;

	private String status;

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

}