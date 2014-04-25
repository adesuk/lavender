/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.group4.lavender.bean;

import ac.id.itb.ppl.group4.lavender.bean.remote.DosenBean;
import ac.id.itb.ppl.group4.lavender.dao.jpa.JpaDao;
import ac.id.itb.ppl.group4.lavender.model.Dosen;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adesuk
 */
@Stateless
public class DosenBeanImpl implements DosenBean {
    
    @PersistenceContext(unitName = "lavenderPU")
    private EntityManager em;
   
    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    
    @Override
    public List<Dosen> findAll() {
        return em.createNamedQuery("Dosen.findAll").getResultList();
    }

    @Override
    public Dosen find(String id) {
        return null;//super.findDao(id);
    }

    @Override
    public void save(Dosen e) {
        //super.saveDao(e);
    }

    @Override
    public Dosen update(Dosen e) {
        return null;//super.updateDao(e);
    }

    @Override
    public void delete(Dosen e) {
        //super.deleteDao(e);
    }

    @Override
    public void businessMethod() {
    }
}
