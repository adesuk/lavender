package ac.id.itb.ppl.lavender.converter;

import ac.id.itb.ppl.lavender.bean.local.DosenLocal;
import ac.id.itb.ppl.lavender.model.Dosen;
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
@Named(value = "inisialDosenConverter")
@RequestScoped
public class InisialDosenConverter implements Converter {
    @Inject private DosenLocal dosenDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("")) {
            return null;
        } else {
            return dosenDao.find(value);
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else {
            return ((Dosen) value).getInisialDosen();
        }
    }
}
