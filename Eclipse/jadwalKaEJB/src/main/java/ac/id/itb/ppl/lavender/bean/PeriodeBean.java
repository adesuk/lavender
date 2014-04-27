package ac.id.itb.ppl.lavender.bean;

import ac.id.itb.ppl.lavender.bean.remote.PeriodeRemote;
import ac.id.itb.ppl.lavender.model.Periode;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * Session Bean implementation class PeriodeBean
 */
@Stateless
@LocalBean
public class PeriodeBean extends AbstractBean<Periode> implements PeriodeRemote {

	@PersistenceContext(unitName="jadwalPU")
	private EntityManager em;
       
    public PeriodeBean() {
        super(Periode.class);
    }

	@Override
	protected EntityManager getEntityManager() {
		return em;
	}

}