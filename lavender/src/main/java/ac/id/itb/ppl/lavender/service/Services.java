/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.service;

import ac.id.itb.ppl.lavender.dao.DosenDao;
import ac.id.itb.ppl.lavender.model.Dosen;
import java.util.List;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.ejb.Stateless;

/**
 *
 * @author adesuk
 */
@WebService(serviceName = "Services")
@Stateless()
public class Services {

    @EJB
    private DosenDao dosenDao;
    
    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(@WebParam(name = "name") String txt) {
        return "Hello " + txt + " !";
    }
    
    @WebMethod(operationName = "findDosenByID")    
    public Dosen findDosenByID(@WebParam(name = "inisial") String inisial) {
        return dosenDao.find(inisial);
    }
}
