package ac.id.itb.ppl.lavender.managedbean.jadwal;

import ac.id.itb.ppl.lavender.bean.DosenBean;
import ac.id.itb.ppl.lavender.bean.JadwalBean;
import ac.id.itb.ppl.lavender.bean.KaryaAkhirBean;
import ac.id.itb.ppl.lavender.bean.MahasiswaBean;
import ac.id.itb.ppl.lavender.bean.PeriodeBean;
import ac.id.itb.ppl.lavender.bean.RuanganBean;
import ac.id.itb.ppl.lavender.bean.SlotWaktuBean;
import ac.id.itb.ppl.lavender.bean.local.DosenLocal;
import ac.id.itb.ppl.lavender.bean.local.JadwalLocal;
import ac.id.itb.ppl.lavender.bean.local.KaryaAkhirLocal;
import ac.id.itb.ppl.lavender.bean.local.MahasiswaLocal;
import ac.id.itb.ppl.lavender.bean.local.PeriodeLocal;
import ac.id.itb.ppl.lavender.bean.local.RuanganLocal;
import ac.id.itb.ppl.lavender.bean.local.SlotWaktuLocal;
import ac.id.itb.ppl.lavender.formatter.PeriodeFormat;
import ac.id.itb.ppl.lavender.formatter.SlotWaktuFormat;
import ac.id.itb.ppl.lavender.formatter.VersiFormat;
import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.Jadwal;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.Mahasiswa;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.Ruangan;
import ac.id.itb.ppl.lavender.model.SlotWaktu;
import ac.id.itb.ppl.lavender.util.TipeEksekusi;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.ejb.EJB;
import javax.inject.Named;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Edbert
 */
@Named("pengelolaanJadwal")
@SessionScoped
public class PengelolaanJadwalMBean implements Serializable {
    private static final int DOSEN_PENGUJI_NUM = 2;
    private static final long serialVersionUID = -923472098100123532L;
    private static final Logger LOGGER = Logger.getLogger(PengelolaanJadwalMBean.class.getName());
    
    @EJB private PeriodeBean periodeDao;
    @EJB private JadwalBean jadwalDao;
    private List<Periode> periodes;
    private Periode periode;
    private List<Date> versiJadwals;
    private Date versiJadwal;
    private List<Jadwal> jadwal;
    private Jadwal jadwalDetail;
    @EJB private MahasiswaBean mahasiswaDao;
    @EJB private SlotWaktuBean slotWaktuDao;
    @EJB private RuanganBean ruanganDao;
    @EJB private KaryaAkhirBean karyaAkhirDao;
    @EJB private DosenBean dosenDao;
    private List<Mahasiswa> mahasiswas;
    private Mahasiswa selectedMahasiswa;
    private List<Dosen> pengujis1;
    private List<Dosen> pengujis2;
    private Dosen[] selectedPengujis = new Dosen[DOSEN_PENGUJI_NUM];
    
    public void reloadPeriodes() {
        periodes = periodeDao.findAll();
    }
    
    public void reloadVersiJadwals() {
        if (getPeriode() != null) {
            versiJadwals = jadwalDao.findJadwalVersions(getPeriode());
        } else {
            versiJadwals = new ArrayList<Date>(0);
            versiJadwal = null;
        }
    }
    
    public void reloadJadwal() {
        if (getPeriode() != null && getVersiJadwal() != null) {
            jadwal = jadwalDao
                .findJadwalByPeriodeAndVersi(getPeriode(), getVersiJadwal());
        }
    }
    
    public void reloadMahasiswas() {
        if (getPeriode() != null) {
            mahasiswas = mahasiswaDao.getAllMahasiswaYangIkutDiSelectedPeriode(
                getPeriode().getTipeJadwal());
        }
    }
    
    public void reloadPengujis1() {
        pengujis1 = dosenDao.findDosenPengujisByMinatTopik(
            jadwalDetail.getKaryaAkhir().getTopik());
    }
    
    public void reloadPengujis2() {
        pengujis2 = dosenDao.findDosenPengujisByMinatTopik(
            jadwalDetail.getKaryaAkhir().getTopik());
    }
    
    public String formatPeriode(Periode periode) {
        return PeriodeFormat.format(periode);
    }
    
    public String formatVersiJadwal(Date versiJadwal) {
        return VersiFormat.format(versiJadwal);
    }
    
    public String formatSlotWaktu(SlotWaktu slotWaktu) {
        return SlotWaktuFormat.format(slotWaktu);
    }
    
    public String getTipeJadwalRealName(char tipeJadwal) {
        Map<String, Character> temp = new TipeEksekusi().getTipeEksekusis();
        for (String s : temp.keySet()) {
            if (temp.get(s) == tipeJadwal) {
                return s;
            }
        }
        return null;
    }
    
    
    
    public void initializeJadwal() {
        //System.out.println(">>> masuk init jadwal <<<");
        //System.out.println(">>> ini versinya " + versiJadwal + " <<<");
        
        selectedMahasiswa = null;
        jadwalDetail = new Jadwal();
        jadwalDetail.setIdPeriode(getPeriode());
        //jadwalDetail.setRuangan(new Ruangan());
        List<Dosen> pengujis = new ArrayList<Dosen>(2);
        pengujis.add(null);
        pengujis.add(null);
        //jadwalDetail.setDosenPenguji(pengujis);
        getSelectedPengujis()[0] = null;
        getSelectedPengujis()[1] = null;
        reloadMahasiswas();
    }
    
    public void handlePeriodeChange() {
//        System.out.println(">>> masuk handlePeriodeChange <<<");
        if (periode != null) {
            versiJadwals = jadwalDao.findJadwalVersions(periode);
            //if (versiJadwals != null || versiJadwals.isEmpty()) {
                //versiJadwal = versiJadwals.get(0);
            //}
        } else {
            versiJadwals = new ArrayList<Date>(0);
        }
    }
    
//    public void handleVersiChange(AjaxBehaviorEvent e) {
//        System.out.println(">>> Lalala " + e.getSource());
//    }
    
    public void displayJadwal() {
        //System.out.println(">>> display jadwal <<<");
        jadwal = new ArrayList<Jadwal>(0);
        boolean errorExist = false;
        
        if (periode == null) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Periode belum dipilih",
                    "Periode harus dipilih terlebih dahulu!")
            );
            errorExist = true;
        }
        
        if (versiJadwal == null) {
            FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Versi belum dipilih",
                    "Versi harus dipilih terlebih dahulu!")
            );
            errorExist = true;
        }
        
        if (errorExist) {
            jadwal = new ArrayList<Jadwal>(0);
            jadwalDetail = null;
            return;
        }
        
        jadwal = jadwalDao.findJadwalByPeriodeAndVersi(periode, versiJadwal);
    }
    
    public void selectNimListener() {
        if (getSelectedMahasiswa() == null) {
            jadwalDetail.setKaryaAkhir(null);
        } else {
            KaryaAkhir karya = karyaAkhirDao.findByOwner(
                getSelectedMahasiswa()
            );
            jadwalDetail.setKaryaAkhir(karya);
            reloadPengujis1();
        }
    }
    
    public void handlePenguji1Change() {
//        System.out.println(">>> size si penguji " + jadwalDetail.getDosenPenguji().size() + " <<<");
        if (getSelectedPengujis()[0] == null) {
            pengujis2 = new ArrayList<Dosen>(0);
        } else {
            reloadPengujis2();
        }
    }
    
//    public void onRowSelect(SelectEvent e) {
//        this.jadwalDetail = ((Jadwal) e.getObject());
//    }
    
    
    
    
    public void createJadwalDetail() {
        //System.out.println(">>> create jadwal detail <<<");
        
        if (periode == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            
            context.addMessage(
                null,
                new FacesMessage(
                    FacesMessage.SEVERITY_ERROR, 
                    "Periode belum dipilih",
                    "Pilih periode terlebih dahulu!"
                )
            );
            return;
        }
        
        try {
            initializeJadwal();
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("TambahJadwal.xhtml");
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, null, ioe);
        }
    }
    
    public void saveJadwal() {
        //System.out.println(">>> masuk save jadwal 1 <<<");
        //System.out.println(">>> nama mhs " + selectedMahasiswa.getNamaMhs());
        //System.out.println(">>> ruangan " + jadwalDetail.getRuangan().getKdRuangan());
        //System.out.println(">>> dospeng 1 " + getSelectedPengujis()[0]);
        //System.out.println(">>> dospeng 2 " + getSelectedPengujis()[1]);
        //System.out.println(">>> versi " + VersiFormat.format(versiJadwal));
        
        KaryaAkhir ka = karyaAkhirDao.findByOwner(selectedMahasiswa);
        jadwalDetail.setKaryaAkhir(ka);
        jadwalDetail.setIdPeriode(getPeriode());
        List<Dosen> pengujis = new ArrayList<Dosen>();
        List<Jadwal> jadwals = Arrays.asList(jadwalDetail);
        if (getSelectedPengujis()[0] != null) {
            getSelectedPengujis()[0].setJadwalList(jadwals);
            pengujis.add(getSelectedPengujis()[0]);
        }
        if (getSelectedPengujis()[1] != null) {
            getSelectedPengujis()[1].setJadwalList(jadwals);
            pengujis.add(getSelectedPengujis()[1]);
        }
        jadwalDetail.setDosenPenguji(pengujis);
        //System.out.println(">>> banyaknya si penguji " + jadwalDetail.getDosenPenguji().size());
        
        if (getVersiJadwal() == null) {
            setVersiJadwal(
                new Date(System.currentTimeMillis())
            );
        }
        jadwalDetail.setGenerateDate(getVersiJadwal());
        jadwalDao.save(jadwalDetail);
        
        reloadVersiJadwals();
        reloadJadwal();
        
//        try {
//            FacesContext.getCurrentInstance().getExternalContext()
//                .redirect("PengelolaanJadwal.xhtml");
//        } catch (IOException ioe) {
//            LOGGER.log(Level.SEVERE, null, ioe);
//        }
        
        RequestContext.getCurrentInstance().execute("showInfo()");
    }
    
    public void editJadwalDetail(Jadwal jadwal) {
        if (periode == null) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(
                null,
                new FacesMessage(
                    FacesMessage.SEVERITY_ERROR,
                    "Periode belum dipilih",
                    "Pilih periode terlebih dahulu!"
                )
            );
            return;
        }

        jadwalDetail = jadwalDao.findComplete(jadwal.getIdJadwal());
        //jadwalDetail = jadwal;
        //System.out.println(">>> Arrrrrrghhhh!!! " + jadwalDetail.getDosenPenguji().size() + " <<<");
        reloadPengujis1();
        if (jadwalDetail.getDosenPenguji() != null &&
            jadwalDetail.getDosenPenguji().size() > 0) {
            getSelectedPengujis()[0] = jadwalDetail.getDosenPenguji().get(0);
        } else {
            getSelectedPengujis()[0] = null;
        }
        if (getSelectedPengujis()[0] != null) {
            reloadPengujis2();
        }
        if (jadwalDetail.getDosenPenguji() != null &&
            jadwalDetail.getDosenPenguji().size() > 1) {
            getSelectedPengujis()[1] = jadwalDetail.getDosenPenguji().get(1);
        } else {
            getSelectedPengujis()[1] = null;
        }

        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("UbahJadwal.xhtml");
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, null, ioe);
        }
    }
    
    public void updateJadwal() {
        //System.out.println(">>> masuk save jadwal 1 <<<");
        //System.out.println(">>> nama mhs " + selectedMahasiswa.getNamaMhs());
        //System.out.println(">>> ruangan " + jadwalDetail.getRuangan().getKdRuangan());
        //System.out.println(">>> dospeng 1 " + getSelectedPengujis()[0]);
        //System.out.println(">>> dospeng 2 " + getSelectedPengujis()[1]);
        //System.out.println(">>> versi " + VersiFormat.format(versiJadwal));
        
        //KaryaAkhir ka = karyaAkhirDao.findByOwner(selectedMahasiswa);
        //jadwalDetail.setKaryaAkhir(ka);
        //jadwalDetail.setIdPeriode(getPeriode());
        List<Dosen> pengujis = new ArrayList<Dosen>();
        List<Jadwal> jadwals = Arrays.asList(new Jadwal[] { jadwalDetail });
        if (getSelectedPengujis()[0] != null) {
            getSelectedPengujis()[0].setJadwalList(jadwals);
            pengujis.add(getSelectedPengujis()[0]);
        }
        if (getSelectedPengujis()[1] != null) {
            getSelectedPengujis()[0].setJadwalList(jadwals);
            pengujis.add(getSelectedPengujis()[1]);
        }
        jadwalDetail.setDosenPenguji(pengujis);
        //System.out.println(">>> banyaknya si penguji " + jadwalDetail.getDosenPenguji().size());
        
        if (getVersiJadwal() == null) {
            setVersiJadwal(
                new Date(System.currentTimeMillis())
            );
        }
        jadwalDetail.setGenerateDate(getVersiJadwal());
        jadwalDao.update(jadwalDetail);
        
        reloadVersiJadwals();
        reloadJadwal();
        
//        try {
//            FacesContext.getCurrentInstance().getExternalContext()
//                .redirect("PengelolaanJadwal.xhtml");
//        } catch (IOException ioe) {
//            LOGGER.log(Level.SEVERE, null, ioe);
//        }
        
        RequestContext.getCurrentInstance().execute("showInfo()");
    }
    
    public void cancelCreateJadwal() {
        jadwalDetail = null;
        
        try {
            FacesContext.getCurrentInstance().getExternalContext()
                .redirect("PengelolaanJadwal.xhtml");
        } catch (IOException ioe) {
            LOGGER.log(Level.SEVERE, null, ioe);
        }
    }
    
    public void deleteJadwal() {
        boolean doDelete = false;
        for (Jadwal j : jadwal) {
            if (j.getSelected()) {
                jadwalDao.delete(j);
                doDelete = true;
            }
        }
        
        if (doDelete) {
            RequestContext.getCurrentInstance().execute("showInfo('Jadwal sudah dihapus')");
            reloadVersiJadwals();
            reloadJadwal();
        } else {
            RequestContext.getCurrentInstance().execute("showInfo('Pilih jadwal yang ingin dihapus')");
        }
    }
    
    
    
    public List<Periode> getPeriodes() {
        if (periodes == null || periodes.isEmpty()) {
            reloadPeriodes();
        }
        return periodes;
    }
    
    public Periode getPeriode() {
        return periode;
    }
    
    public void setPeriode(Periode periode) {
        this.periode = periode;
    }
    
    public List<Date> getVersiJadwals() {
        return versiJadwals;
    }
    
    public void setVersiJadwals(List<Date> versiJadwals) {
        this.versiJadwals = versiJadwals;
    }
    
    public Date getVersiJadwal() {
        return versiJadwal;
    }
    
    public void setVersiJadwal(Date versiJadwal) {
        this.versiJadwal = versiJadwal;
    }
    
    public List<Jadwal> getJadwal() {
        return jadwal;
    }
    
    public Jadwal getJadwalDetail() {
        return jadwalDetail;
    }
    
    public List<Mahasiswa> getMahasiswas() {
        return mahasiswas;
    }
    
    public Mahasiswa getSelectedMahasiswa() {
        return selectedMahasiswa;
    }
    
    public void setSelectedMahasiswa(Mahasiswa selectedMahasiswa) {
        this.selectedMahasiswa = selectedMahasiswa;
    }
    
    public List<SlotWaktu> getSlotWaktus() {
        return slotWaktuDao.findAll();
    }
    
    public List<Ruangan> getRuangans() {
        return ruanganDao.findAll();
    }
    
    public List<Dosen> getPengujis1() {
        return pengujis1;
    }
    
    public List<Dosen> getPengujis2() {
        return pengujis2;
    }
    
    public Dosen[] getSelectedPengujis() {
        return selectedPengujis;
    }
    
    public void setSelectedPengujis(Dosen[] selectedPengujis) {
        this.selectedPengujis = selectedPengujis;
    }
}
