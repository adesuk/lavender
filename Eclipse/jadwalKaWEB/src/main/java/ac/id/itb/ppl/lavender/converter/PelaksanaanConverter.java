package ac.id.itb.ppl.lavender.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.SelectItem;

@FacesConverter(forClass=SelectItem.class, value="pelaksanaanConverter")
public class PelaksanaanConverter implements Converter, Serializable {

	private static final long serialVersionUID = 7485036731977905013L;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		return new Integer(value);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		SelectItem i = (SelectItem) value;
		return ""; 
	}

}
