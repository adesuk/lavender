package ac.id.itb.ppl.lavender.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ac.id.itb.ppl.lavender.bean.DosenBean;
import ac.id.itb.ppl.lavender.model.Dosen;

@ManagedBean(name="dosenMBean")
@ViewScoped
public class DosenMBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@EJB
	private DosenBean dosenBean;
	
	public DosenMBean() {
		
	}
	
	public List<Dosen> getDosens() {
		return dosenBean.findAll();
	}
}
