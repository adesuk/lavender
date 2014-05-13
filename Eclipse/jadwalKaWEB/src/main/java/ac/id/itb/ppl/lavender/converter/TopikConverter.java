package ac.id.itb.ppl.lavender.converter;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import ac.id.itb.ppl.lavender.bean.TopikBean;
import ac.id.itb.ppl.lavender.model.Topik;

@FacesConverter(forClass=Topik.class, value="topikConverter")
public class TopikConverter implements Converter, Serializable{
	private static final long serialVersionUID = -1350358842070603233L;

	@EJB
	private TopikBean topikBean;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		if (value == null) {
			return null;
		}
		else {
			Topik t = topikBean.find(Integer.parseInt(value));
			System.out.println("getAsObject : "+ t.getNamaTopik());
			return t;
//			return topikBean.find(Integer.parseInt(value));
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		if (value == null) {
			return null;
		}
		else {
			String val = ((Topik) value).getIdTopik().toString();
			System.out.println(val);
			return val;
//			return ((Topik) value).getIdTopik().toString();
		}
	}

}
