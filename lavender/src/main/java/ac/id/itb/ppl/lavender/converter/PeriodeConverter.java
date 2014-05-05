package ac.id.itb.ppl.lavender.converter;

import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.model.Periode;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Edbert
 */
@Named(value = "periodeConverter")
@RequestScoped
public class PeriodeConverter implements Converter, java.io.Serializable {
    @Inject private PeriodeDao periodeDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Object output = null;
        if (value != null) {
            try {
                output = periodeDao.find(Integer.valueOf(value));
            } catch (NumberFormatException nfe) {
                output = null;
            }
        }
        return output;
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return null;
        } else {
            return ((Periode) value).getIdPeriode().toString();
        }
    }
}
