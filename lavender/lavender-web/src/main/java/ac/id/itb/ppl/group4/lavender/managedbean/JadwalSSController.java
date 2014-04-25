package ac.id.itb.ppl.group4.lavender.managedbean;

import ac.id.itb.ppl.group4.lavender.modelz.JadwalSS;
import java.util.*;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author edbert
 */
@Named(value = "jadwalSSController")
@SessionScoped
public class JadwalSSController implements java.io.Serializable {
    private List<JadwalSS> jadwalsses;
    
    public JadwalSSController() {
        jadwalsses = new ArrayList<JadwalSS>();
        JadwalSS j = new JadwalSS();
        j.setId("1");
        j.setNamaMhs("Ucup");
        j.setJamPelaksanaan("08.00-09.00");
        j.setNimMhs("111111");
        j.setTanggal(Calendar.getInstance());
        j.setJudul("Mau tau aja");
        jadwalsses.add(j);
        j = new JadwalSS();
        j.setId("2");
        j.setNamaMhs("Bokir");
        j.setJamPelaksanaan("08.00-09.00");
        j.setNimMhs("111111");
        j.setTanggal(Calendar.getInstance());
        j.setJudul("Mau tau aja");
        jadwalsses.add(j);
        j = new JadwalSS();
        j.setId("3");
        j.setNamaMhs("Rias");
        j.setJamPelaksanaan("08.00-09.00");
        j.setNimMhs("111111");
        j.setTanggal(Calendar.getInstance());
        j.setJudul("Mau tau aja");
        jadwalsses.add(j);
    }
    
    public List<JadwalSS> getJadwalSS() {
        return jadwalsses;
    }
}
