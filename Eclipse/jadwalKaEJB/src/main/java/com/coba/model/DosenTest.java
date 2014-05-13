package com.coba.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import ac.id.itb.ppl.lavender.model.Topik;
import ac.id.itb.ppl.lavender.model.Dosen;

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
    
    public void tampil(String inisial) {
        
        final Dosen d = (Dosen) em.createQuery("select d from Dosen d where d.inisialDosen=?1")
        		.setParameter(1, inisial)
                .getSingleResult();
 
        if (d != null) {
	        List<Topik> list = d.getBidangKeahlian();
	        for (Topik current : list) {
	            System.out.println(current.getNamaTopik());
	        }
        }
    }
    
 public Dosen retrieve(String inisial) {
        
        Dosen d = (Dosen) em.createQuery("select d from Dosen d where d.inisialDosen=?1")
        		.setParameter(1, inisial)
                .getSingleResult();
 
       return d;
    }
    
    public void saveTopikDosen(Dosen d, List<Topik> listTopik) {        
    	d.setBidangKeahlian(listTopik);        
    	em.persist(d);
    }
    
    public void deleteDosen(Dosen d) {              
    	em.remove(d);
    }
    
    public static void main(String[] args) {
		DosenTest dt = new DosenTest();
		dt.initEmfAndEm();
//		dt.retrieve();
		
		Dosen d = new Dosen();
    	d.setInisialDosen("AS");
    	d.setNamaDosen("Ade Sukendar");
    	
    	List<Topik> listTopik = new ArrayList<Topik>();
    	Topik t1 = new Topik();
    	t1.setBidang("RPL");
    	t1.setNamaTopik("Topik 1");
    	Topik t2 = new Topik();
    	t2.setBidang("RPL");
    	t2.setNamaTopik("Topik 2");
    	
    	listTopik.add(t1);
    	listTopik.add(t2);
    	
    	dt.saveTopikDosen(d, listTopik);
    	dt.tampil("AS");
    	Dosen d1 = dt.retrieve("AS");
    	dt.deleteDosen(d1);
    	dt.tampil("AS");
    	
		dt.cleanup();
	}
}
