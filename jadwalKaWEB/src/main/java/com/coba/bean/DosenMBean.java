package com.coba.bean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.coba.model.Dosen;
import com.coba.model.DosenBean;

@ManagedBean(name="dosenBean")
@ViewScoped
public class DosenMBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private DosenBean dosenBea;
	
	public DosenMBean() {
		
	}
	
	public List<Dosen> getDosens() {
		return dosenBea.findAll();
	}
}
