package ac.id.itb.ppl.lavender.converter;

import ac.id.itb.ppl.lavender.dao.SlotWaktuDao;
import ac.id.itb.ppl.lavender.model.SlotWaktu;
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
@Named(value = "slotWaktuConverter")
@RequestScoped
public class SlotWaktuConverter implements Converter, Serializable {
    @Inject private SlotWaktuDao slotWaktuDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("")) {
            return null;
        } else {
            return slotWaktuDao.find(Integer.valueOf(value));
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else {
            return ((SlotWaktu) value).getIdSlot().toString();
        }
    }
}
