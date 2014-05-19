package ac.id.itb.ppl.lavender.converter;

import ac.id.itb.ppl.lavender.bean.local.RuanganLocal;
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
    @Inject private RuanganLocal ruanganDao;

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        //System.out.println(">>> get ruangan as object, input: " + value + " <<<");
        if (value == null || value.equals("")) {
            return null;
        } else {
            Ruangan r = ruanganDao.find(value);
            //System.out.println(">>> ruangannya " + r + " <<<");
            return r;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        //System.out.println(">>> get as string, input: " + (value != null ?  ((Ruangan) value).getKdRuangan()  : "") + " <<<");
        if (value == null) {
            return "";
        } else {
            String s = ((Ruangan) value).getKdRuangan();
            return s;
        }
    }
}
