package ac.id.itb.ppl.lavender.converter;

import ac.id.itb.ppl.lavender.dao.jpa.SlotWaktuDaoImpl;
import ac.id.itb.ppl.lavender.model.SlotWaktu;
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
@Named(value = "slotWaktuConverter")
@RequestScoped
public class SlotWaktuConverter implements Converter, Serializable {
    @EJB private SlotWaktuDaoImpl slotWaktuDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        return slotWaktuDao.find(Integer.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        SlotWaktu s = (SlotWaktu) value;
        return s.getIdSlot().toString();
    }
}
