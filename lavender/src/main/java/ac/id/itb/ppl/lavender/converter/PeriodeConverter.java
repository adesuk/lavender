package ac.id.itb.ppl.lavender.converter;

import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.dao.jpa.PeriodeDaoImpl;
import ac.id.itb.ppl.lavender.model.Periode;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author Edbert
 */
@Named(value = "periodeConverter")
@RequestScoped
public class PeriodeConverter implements Converter, java.io.Serializable {
    @EJB
    private PeriodeDaoImpl periodeDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return periodeDao.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Periode) value).getIdPeriode().toString();
    }
}
