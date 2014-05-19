package ac.id.itb.ppl.lavender.converter;

import ac.id.itb.ppl.lavender.bean.local.MahasiswaLocal;
import ac.id.itb.ppl.lavender.model.Mahasiswa;
import java.io.Serializable;
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
@Named(value = "nimMahasiswaConverter")
@RequestScoped
public class NimMahasiswaConverter implements Converter, Serializable {
    @Inject private MahasiswaLocal mahasiswaDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value == null || value.equals("")) {
            return null;
        } else {
            return mahasiswaDao.find(value);
        }
    }
    
    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value == null) {
            return "";
        } else {
            return ((Mahasiswa) value).getNim();
        }
    }
}
