package ac.id.itb.ppl.lavender.converter;

import ac.id.itb.ppl.lavender.dao.TopikDao;
import ac.id.itb.ppl.lavender.model.Topik;
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
@Named("topikConverter")
@RequestScoped
public class TopikConverter implements Converter, Serializable {
    @Inject private TopikDao topikDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        Object output;
        System.out.println(">>> Dari converter topik: " + value + " <<<");
        if (value == null || value.equals("")) {
            output = null;
        } else {
            try {
                output = topikDao.find(Integer.valueOf(value));
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
            return ((Topik) value).getIdTopik().toString();
        }
    }
}
