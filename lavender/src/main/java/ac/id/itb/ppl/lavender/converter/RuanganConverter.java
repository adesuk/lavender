package ac.id.itb.ppl.lavender.converter;

import ac.id.itb.ppl.lavender.dao.jpa.RuanganDaoImpl;
import ac.id.itb.ppl.lavender.model.Ruangan;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author edbert
 */
@Named(value = "ruanganConverter")
@RequestScoped
public class RuanganConverter implements Converter, Serializable {
    @EJB private RuanganDaoImpl ruanganDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return ruanganDao.find(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return ((Ruangan) value).getNamaRuangan();
    }
}
