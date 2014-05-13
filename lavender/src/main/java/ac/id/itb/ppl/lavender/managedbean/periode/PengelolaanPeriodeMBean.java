package ac.id.itb.ppl.lavender.managedbean.periode;

import ac.id.itb.ppl.lavender.dao.PeriodeDao;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.util.TipeEksekusi;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author Edbert
 */
@Named(value = "pengelolaanPeriode")
@SessionScoped
public class PengelolaanPeriodeMBean implements Serializable {
    private static final long serialVersionUID = -901204217738820234L;
    private static final Logger LOGGER = Logger.getLogger(PengelolaanPeriodeMBean.class.getName()); 
    
    @Inject private PeriodeDao periodeDao;
    // view semua periode
    private List<Periode> periodes1;
    private Periode selectedPeriode;
    private String keyword;
    private List<Periode> periodes2;
    private TipeEksekusi tipeEksekusi = new TipeEksekusi();
    
    // tambah periode
    private Periode periode;
    
    //<editor-fold defaultstate="collapsed" desc="Business logic">
    public void reloadPeriodes() {
        periodes1 = periodeDao.findAll();
    }
    
    public void reloadPeriodes2() {
        if (selectedPeriode == null) {
            if (keyword != null) {
                keyword = "";
            }
            periodes2 = periodeDao.findByKeyword(keyword);
        } else {
            periodes2 = new ArrayList<Periode>(1);
            periodes2.add(selectedPeriode);
        }
    }
    
    // keperluan tambah periode
    public void initializePeriode(ComponentSystemEvent e) {
        periode = new Periode();
        periode.setStatusRilis(new BigInteger("0"));
        periode.setStatusVerifikasi(new BigInteger("0"));
    }
    
    public void savePeriode() {
        periodeDao.save(periode); 
        /*FacesMessage m = new FacesMessage(
            FacesMessage.SEVERITY_INFO, "Info", "Periode sudah disimpan"
        );*/
        RequestContext.getCurrentInstance().execute("showInfo()");
    }
    
    public void updatePeriode() {
        periodeDao.update(periode);
        RequestContext.getCurrentInstance().execute("showInfo()");
    }
    
    public void cancel() {
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("PengelolaanPeriode.xhtml");
        } catch (IOException ioe) {
            ioe.printStackTrace(System.out);
        }
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Getter dan setter">
    public List<Periode> getPeriodes1() {
        if (periodes1 == null || periodes1.isEmpty()) {
            reloadPeriodes();
        }
        return periodes1;
    }
    
    public Periode getSelectedPeriode() {
        return selectedPeriode;
    }
    
    public void setSelectedPeriode(Periode selectedPeriode) {
        this.selectedPeriode = selectedPeriode;
    }
    
    public String getKeyword() {
        return keyword;
    }
    
    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }
    
    public List<Periode> getPeriodes2() {
        return periodes2;
    }
    
    public Map<String, Character> getTipeJadwals() {
        Map<String, Character> map = tipeEksekusi.getTipeEksekusis();
        map.remove("Proposal");
        return map;
    }
    
    public String getTipeJadwalName(Character tipeJadwal) {
        return tipeEksekusi.getName(tipeJadwal);
    }
    
    // keperluan tambah periode
    public Periode getPeriode() {
        return periode;
    }
    
    public void setPeriode(Periode periode) {
        this.periode = periode;
    }
    //</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Ajaxnya">
    public void cariListener(ActionEvent e) {
        reloadPeriodes2();
    }
    
    // keperluan tambah periode
    public void tambahPeriode() {
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("TambahPeriode.xhtml");
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, null, ioe);
        }
    }
    
    public void ubahPeriode(Periode periode) {
        this.periode = periode;
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("EditPeriode.xhtml");
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, null, ioe);
        }
    }
    
    public void deletePeriode() {
        boolean doDelete = false;
        for (Periode p : periodes2) {
            if (p.getSelected()) {
                periodeDao.delete(p);
                doDelete = true;
            }
        }
        if (doDelete) {
            RequestContext.getCurrentInstance().execute("showBerhasilHapusDialog()");
            reloadPeriodes2();
        } else {
            RequestContext.getCurrentInstance().execute("showTidakAdaYangDihapusDialog()");
        }
    }
    
    public void onEdit(RowEditEvent event) {
        periodeDao.update((Periode) event.getObject());
    }
    //</editor-fold>
    
}
