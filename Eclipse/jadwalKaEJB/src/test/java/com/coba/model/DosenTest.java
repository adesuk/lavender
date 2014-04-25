package com.coba.model;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

//import org.junit.After;
//import org.junit.Before;
//import org.junit.Test;

public class DosenTest {
	private EntityManagerFactory emf;
    private EntityManager em;
 
//    @Before
    public void initEmfAndEm() { 
        emf = Persistence.createEntityManagerFactory("jadwalPU");
        em = emf.createEntityManager();
    }
 
//    @After
    public void cleanup() {
        em.close();
    }
 
//    @SuppressWarnings("unchecked")
//    @Test
    public void retrieve() {
       
        final List<Dosen> list = em.createNamedQuery("Dosen.findAll")
                .getResultList();
 
        for (Dosen current : list) {
            System.out.println(current.getNamaDosen());
        }
    }
    
    public static void main(String[] args) {
		DosenTest dt = new DosenTest();
		dt.initEmfAndEm();
		dt.retrieve();
		dt.cleanup();
	}
}
