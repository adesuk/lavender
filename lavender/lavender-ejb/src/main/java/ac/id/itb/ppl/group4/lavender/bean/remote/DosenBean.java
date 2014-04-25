/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.group4.lavender.bean.remote;

import ac.id.itb.ppl.group4.lavender.dao.Dao;
import ac.id.itb.ppl.group4.lavender.model.Dosen;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author adesuk
 */
@Remote
public interface DosenBean extends Dao<Dosen, String>{

    void businessMethod();
    
}
