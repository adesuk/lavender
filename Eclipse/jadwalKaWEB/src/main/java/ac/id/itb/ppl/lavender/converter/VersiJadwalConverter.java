package ac.id.itb.ppl.lavender.converter;

import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;

/**
 *
 * @author edbert
 */
@Named
@RequestScoped
public class VersiJadwalConverter implements Converter, Serializable {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        
            return new Date(Long.valueOf(value));
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else {
            return new Long(((Date) value).getTime()).toString();
        }
    }
    
}
