package ac.id.itb.ppl.lavender.managedbean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

import ac.id.itb.ppl.lavender.bean.DosenBean;
import ac.id.itb.ppl.lavender.bean.KetersediaanDosenBean;
import ac.id.itb.ppl.lavender.bean.PeriodeBean;
import ac.id.itb.ppl.lavender.bean.SlotWaktuBean;
import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.KetersediaanWaktuDosen;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.SlotWaktu;

@ManagedBean
@ViewScoped
public class KetersediaanDosenMBean implements Serializable {

	private static final long serialVersionUID = -3624715237710814073L;

	@EJB
	private PeriodeBean periodeBean;
	@EJB
	private DosenBean dosenBean;
	@EJB
	private SlotWaktuBean slotWaktuBean;
	@EJB
	private KetersediaanDosenBean ketesediaanBean;
	
	private List<Periode> periodeList;
	private Periode periodeSelected;
	private List<Dosen> dosenList;
	private Dosen dosenSelected;
	private List<SelectItem> tanggalList;
	private SelectItem tanggalSelected;
	private List<SlotWaktu> slotWaktuList;
	private List<SlotWaktu> slotWaktuSelected;
	
	@PostConstruct
	public void init() {
		periodeList = periodeBean.findAll();
		dosenList = dosenBean.findAll();
		slotWaktuList = slotWaktuBean.findAll();
	}
	
	public void findPeriode() {
		Map<Date, String> dateMap = periodeBean.getTanggalList(periodeSelected);
		if (dateMap.size() != 0) {
			tanggalList.clear();
			for (Date d : dateMap.keySet()) {
				tanggalList.add(new SelectItem(d, dateMap.get(d)));
			}
		}
	}
	
	public void findSlotWaktu() {
		slotWaktuSelected = ketesediaanBean.findSlotWaktu(
				(Date)tanggalSelected.getValue(), dosenSelected.getInisialDosen());
	}
	
	public void save() {
		KetersediaanWaktuDosen kwd = new KetersediaanWaktuDosen();
		kwd.setDosen(dosenSelected);
		kwd.setSlotWaktu(slotWaktu);
		ketesediaanBean.create();
	}
	
	/*
	 * Setter & Getter
	 */
	
	public List<Periode> getPeriodeList() {
		return periodeList;
	}
	
	public void setPeriodeList(List<Periode> periodeList) {
		this.periodeList = periodeList;
	}
	
	public Periode getPeriodeSelected() {
		return periodeSelected;
	}
	
	public void setPeriodeSelected(Periode periodeSelected) {
		this.periodeSelected = periodeSelected;
	}
	
	public List<Dosen> getDosenList() {
		return dosenList;
	}
	
	public void setDosenList(List<Dosen> dosenList) {
		this.dosenList = dosenList;
	}
	
	public Dosen getDosenSelected() {
		return dosenSelected;
	}
	
	public void setDosenSelected(Dosen dosenSelected) {
		this.dosenSelected = dosenSelected;
	}
	
	public List<SelectItem> getTanggalList() {
		return tanggalList;
	}
	
	public void setTanggalList(List<SelectItem> tanggalList) {
		this.tanggalList = tanggalList;
	}
	
	public SelectItem getTanggalSelected() {
		return tanggalSelected;
	}
	
	public void setTanggalSelected(SelectItem tanggalSelected) {
		this.tanggalSelected = tanggalSelected;
	}
	
	public List<SlotWaktu> getSlotWaktuList() {
		return slotWaktuList;
	}
	
	public void setSlotWaktuList(List<SlotWaktu> slotWaktuList) {
		this.slotWaktuList = slotWaktuList;
	}
	
	public List<SlotWaktu> getSlotWaktuSelected() {
		return slotWaktuSelected;
	}
	
	public void setSlotWaktuSelected(List<SlotWaktu> slotWaktuSelected) {
		this.slotWaktuSelected = slotWaktuSelected;
	}
	
}
