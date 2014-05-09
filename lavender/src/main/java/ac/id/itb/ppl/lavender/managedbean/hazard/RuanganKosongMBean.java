package ac.id.itb.ppl.lavender.managedbean.hazard;

import ac.id.itb.ppl.lavender.dao.*;
import ac.id.itb.ppl.lavender.model.*;
import java.io.*;
import java.util.*;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author edbert
 */
@Named("ruanganKosong")
@SessionScoped
public class RuanganKosongMBean implements Serializable {
    @Inject private RuanganDao rDao;
    @Inject private PeriodeDao pDao;
    private List<Ruangan> rs;
    
    public List<Ruangan> getRs() {
        return rDao.findRuanganDanKetersediaanRuangans(pDao.find(1));
    }
}
