package ac.id.itb.ppl.lavender.converter;

import ac.id.itb.ppl.lavender.dao.RuanganDao;
import ac.id.itb.ppl.lavender.model.Ruangan;
import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author edbert
 */
@Named(value = "ruanganConverter")
@RequestScoped
public class RuanganConverter implements Converter, Serializable {
    @Inject private RuanganDao ruanganDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("")) {
            return null;
        } else {
            return ruanganDao.find(value);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else {
            return ((Ruangan) value).getNamaRuangan();
        }
    }
}
