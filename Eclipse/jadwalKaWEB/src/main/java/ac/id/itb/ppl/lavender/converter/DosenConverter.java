package ac.id.itb.ppl.lavender.converter;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ac.id.itb.ppl.lavender.bean.DosenBean;
import ac.id.itb.ppl.lavender.model.Dosen;

@FacesConverter(forClass=Dosen.class, value="dosenConverter")
public class DosenConverter implements Converter, Serializable {

	private static final long serialVersionUID = -6463506516783906026L;

	@EJB
	private DosenBean dosenBean;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}
		else {
			return dosenBean.find(value);
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		return ((Dosen) value).getInisialDosen();
	}

}
