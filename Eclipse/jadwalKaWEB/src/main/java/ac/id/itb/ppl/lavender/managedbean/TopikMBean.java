package ac.id.itb.ppl.lavender.managedbean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ac.id.itb.ppl.lavender.bean.DosenBean;
import ac.id.itb.ppl.lavender.bean.TopikBean;
import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.Topik;

@ManagedBean(name="topikMBean")
@ViewScoped
public class TopikMBean implements Serializable {

	private static final long serialVersionUID = 333661705133697967L;
	@EJB
	private TopikBean topikBean;
	@EJB
	private DosenBean dosenBean;
	// stub dosen
	private Dosen dosen;
	
	private List<String> bidangList;
	private String bidang;
	private List<Topik> topikList;
	private List<Topik> topikSelected;
	
	public TopikMBean() {
		
	}
	
	@PostConstruct
	public void init() {
		dosen = dosenBean.find("AS");
		reloadBidangList();
	}
	
	public void reloadBidangList() {
		bidangList = topikBean.findAllBidangTopik();
	}
	
	public void reloadTopikList() {
		topikList = topikBean.findTopikByBidang(bidang);
	}
	
	public void reloadTopikSelected() {
		topikSelected = topikBean.findTopikByBidangAndDosen(dosen.getInisialDosen(), getBidang());
	}
	
	public void searchListener() {
		System.out.println("MAsuk search. Bidang : "+ bidang);
		
//		topikList = topikBean.findTopikByBidang(bidang);
		reloadTopikList();
		System.out.println(topikList.size());
		for (Topik t : topikList) {
			System.out.println(t.getIdTopik()+ ","+ t.getNamaTopik()+ ","+ t.getBidang());
		}
		
		reloadTopikSelected();
		System.out.println(topikSelected.size());
		for (Topik t : topikSelected) {
			System.out.println(t.getNamaTopik());
		}
		
	}
	
	public void save() {
		System.out.println("MAsuk save");
//		dosen.setBidangKeahlian(topikSelected);
//		dosenBean.edit(dosen);
		
		
		System.out.println(topikSelected.size());
		for (Topik t : topikSelected) {
			System.out.println(t.getNamaTopik());
		}
	}
	
	/*
	 * set & get
	 */
	
	public Dosen getDosen() {
		return dosen;
	}
	
	public void setDosen(Dosen dosen) {
		this.dosen = dosen;
	}
	
	public List<String> getBidangList() {
		if (bidangList == null || bidangList.isEmpty()) {
			reloadBidangList();
		}
		return bidangList;
	}
	
	public void setBidangList(List<String> bidangList) {
		this.bidangList = bidangList;
	}
	
	public String getBidang() {
		if (bidang == null && bidangList != null && !bidangList.isEmpty()) {
			bidang = bidangList.get(0);
		}
		return bidang;
	}
	
	public void setBidang(String bidang) {
		this.bidang = bidang;
	}
	
	public List<Topik> getTopikList() {
		return topikList;
	}

	public void setTopikList(List<Topik> topikList) {
		this.topikList = topikList;
	}
	
	public List<Topik> getTopikSelected() {
		return topikSelected;
	}
	
	public void setTopikSelected(List<Topik> topikSelected) {
		this.topikSelected = topikSelected;
	}
	
}
