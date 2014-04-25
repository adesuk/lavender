package com.coba.model;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class DosenBean
 */
@Stateless
@LocalBean
public class DosenBean {
	@PersistenceContext(unitName="jadwalPU")
	private EntityManager em;
	
	public List<Dosen> findAll() {
		return em.createNamedQuery("Dosen.findAll").getResultList();
	}
}
