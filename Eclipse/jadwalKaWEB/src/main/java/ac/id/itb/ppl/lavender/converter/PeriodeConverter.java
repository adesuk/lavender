package ac.id.itb.ppl.lavender.converter;

import ac.id.itb.ppl.lavender.bean.PeriodeBean;
import ac.id.itb.ppl.lavender.model.Periode;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Named;

/**
 *
 * @author Edbert
 */
@FacesConverter(forClass=Periode.class, value="periodeConverter")
public class PeriodeConverter implements Converter, java.io.Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -3710689498744559062L;
	@EJB
	    private PeriodeBean periodeBean;

	    @Override
	    public Object getAsObject(FacesContext context, UIComponent component, String value) {
	        if (value == null) {
//	        	System.out.println("getAsObject null");
	            return null;
	        } else if (value.equals("All")) {
//	        	System.out.println("getAsObject all null");
	        	return null;
	        } else {
//	        	System.out.println("getAsObject Periode Bean");
//	        	Periode p = periodeBean.find(Long.valueOf(value));
//	        	System.out.println(p.getIdPeriode()+ ","+ p.getNamaPeriode());
	            return periodeBean.find(Long.valueOf(value));
	        }
	    }

	    @Override
	    public String getAsString(FacesContext context, UIComponent component, Object value) {
	        if (value == null) {
//	        	System.out.println("getAsString null");
	            return null;
	        } else {
//	        	System.out.println("getAsString long to string"+ ((Periode) value).getIdPeriode());
	            return Long.toString(((Periode) value).getIdPeriode());
	        	
//	        	return Long.toString((long)value);
	        }
	    }
}
