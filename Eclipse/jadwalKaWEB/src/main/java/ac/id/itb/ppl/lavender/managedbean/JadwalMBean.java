package ac.id.itb.ppl.lavender.managedbean;

import java.io.Serializable;

import javax.ejb.EJB;

import ac.id.itb.ppl.lavender.bean.JadwalBean;
import ac.id.itb.ppl.lavender.bean.PeriodeBean;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.util.PeriodeFormat;
import ac.id.itb.ppl.lavender.util.TipeEksekusi;

public class JadwalMBean implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@EJB
	protected JadwalBean jadwalBean;
	@EJB
	protected PeriodeBean periodeBean;
	
	protected Periode periode;
	protected TipeEksekusi tipeEksekusi;
	
	public String getPeriodeFormat() {
		return "Periode ".concat(PeriodeFormat.format(periode));
	}

	public String getPeriodeType() {
		return "Jadwal ["
				.concat(tipeEksekusi.getTipeEksekusi(
							periode.getTipeJadwal().charAt(0)))
				.concat("]");			
	}

	public Periode getPeriode() {
		return periode;
	}

	public void setPeriode(Periode periode) {
		this.periode = periode;
	}
}
