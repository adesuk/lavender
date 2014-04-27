package ac.id.itb.ppl.lavender.converter;

import ac.id.itb.ppl.lavender.bean.PeriodeBean;
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
    private PeriodeBean periodeBean;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return periodeBean.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return String.valueOf(((Periode) value).getIdPeriode());
    }
}
