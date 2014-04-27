/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.genetika;

import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.KaryaAkhir;
import ac.id.itb.ppl.lavender.model.Periode;
import ac.id.itb.ppl.lavender.model.Ruangan;
import ac.id.itb.ppl.lavender.model.SlotWaktu;
import ac.id.itb.ppl.lavender.model.Topik;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Asri Maspupah
 */
public class DataSource {


    private List<Dosen> listGenDosen;
    private List<SlotWaktu> listGenSlotWaktu;
    private List<Ruangan> listGenRuangan;
    private List<KaryaAkhir> listGenKaryaAkhir;
    private List<KaryaAkhir> tempKaryaAkhir;
    private List<Date> listDatePeriode;
    private Periode periodeJadwal;

    private static int index = 0;
   
    
    private static DataSource instance = new DataSource();

    private DataSource (){

//        listGenDosen = new ArrayList();
//        listGenSlotWaktu = new ArrayList();
//        listGenRuangan = new ArrayList();
//        listGenKaryaAkhir = new ArrayList();
//        periodeJadwal = new Periode();
//
//        // set data Periode
//        DateFormat formatter = null;
//        // Creating SimpleDateFormat with yyyyMMdd format e.g."20110914"
//        formatter = new SimpleDateFormat("yyyyMMdd");
//        try {
//            periodeJadwal.setPeriodeAwal((Date) formatter.parse("20140210"));
//            periodeJadwal.setPeriodeAkhir((Date) formatter.parse("20140214"));
//
//            setPeriodeJadwal(periodeJadwal);
//            // List Mahasiswa
//            //1
//            Mahasiswa M1 = new Mahasiswa();
//            M1.setNim("13508079");
//            M1.setNamaMhs("Zain Fathoni");
//            M1.setJenjang("S1");
//            //2
//            Mahasiswa M2 = new Mahasiswa();
//            M2.setNim("13508086");
//            M2.setNamaMhs("Agung Dwi lambang Gito S");
//            M2.setJenjang("S1");
//            //3
//            Mahasiswa M3 = new Mahasiswa();
//            M3.setNim("13508088");
//            M3.setNamaMhs("Adhi Darmawan Sutjiadi");
//            M3.setJenjang("S1");
//            //4
//            Mahasiswa M4 = new Mahasiswa();
//            M4.setNim("13509003");
//            M4.setNamaMhs("Muhammad Reza Mandala Putra");
//            M4.setJenjang("S1");
//            //5
//            Mahasiswa M5 = new Mahasiswa();
//            M5.setNim("13509023");
//            M5.setNamaMhs("Dita Anindhika");
//            M5.setJenjang("S1");
//            //5
//            Mahasiswa M6 = new Mahasiswa();
//            M6.setNim("13509058");
//            M6.setNamaMhs("Okihita Hasiholan Sihaloho");
//            M6.setJenjang("S1");
//            //7
//            Mahasiswa M7 = new Mahasiswa();
//            M7.setNim("13509061");
//            M7.setNamaMhs("Steven Andrew");
//            M7.setJenjang("S1");
//            //8
//            Mahasiswa M8 = new Mahasiswa();
//            M8.setNim("13509072");
//            M8.setNamaMhs("Gilbran Imami");
//            M8.setJenjang("S1");
//            //9
//            Mahasiswa M9 = new Mahasiswa();
//            M9.setNim("13509082");
//            M9.setNamaMhs("Samuel Cahyawijaya");
//            M9.setJenjang("S1");
//            //10
//            Mahasiswa M10 = new Mahasiswa();
//            M10.setNim("13509085");
//            M10.setNamaMhs("Andre Novelando");
//            M10.setJenjang("S1");
//
//            // List Topik
//            Topik T1 = new Topik();
//            T1.setIdTopik(1);
//            T1.setNamaTopik("Big Data");
//
//            Topik T2 = new Topik();
//            T2.setIdTopik(2);
//            T2.setNamaTopik("Software Quality");
//
//            Topik T3 = new Topik();
//            T3.setIdTopik(3);
//            T3.setNamaTopik("Visualisasi Data");
//
//            Topik T4 = new Topik();
//            T4.setIdTopik(4);
//            T4.setNamaTopik("Machine Learning");
//
//            Topik T5 = new Topik();
//            T5.setIdTopik(5);
//            T5.setNamaTopik("Search Engine");
//
//            // ############## LIST SLOT WAKTU
//            SlotWaktu SlotWaktu1 = new SlotWaktu();
//            SlotWaktu1.setIdSlot(1);
//            SlotWaktu1.setSesi((short) 1);
//            listGenSlotWaktu.add(SlotWaktu1);
//
//            SlotWaktu SlotWaktu2 = new SlotWaktu();
//            SlotWaktu2.setIdSlot(2);
//            SlotWaktu2.setSesi((short) 1);
//            listGenSlotWaktu.add(SlotWaktu2);
//
//            SlotWaktu SlotWaktu3 = new SlotWaktu();
//            SlotWaktu3.setIdSlot(3);
//            SlotWaktu3.setSesi((short) 1);
//            listGenSlotWaktu.add(SlotWaktu3);
//
//            SlotWaktu SlotWaktu4 = new SlotWaktu();
//            SlotWaktu4.setIdSlot(4);
//            SlotWaktu4.setSesi((short) 2);
//            listGenSlotWaktu.add(SlotWaktu4);
//
//            SlotWaktu SlotWaktu5 = new SlotWaktu();
//            SlotWaktu5.setIdSlot(5);
//            SlotWaktu5.setSesi((short) 2);
//            listGenSlotWaktu.add(SlotWaktu5);
//
//            SlotWaktu SlotWaktu6 = new SlotWaktu();
//            SlotWaktu6.setIdSlot(6);
//            SlotWaktu6.setSesi((short) 2);
//            listGenSlotWaktu.add(SlotWaktu6);
//
//            SlotWaktu SlotWaktu7 = new SlotWaktu();
//            SlotWaktu7.setIdSlot(7);
//            SlotWaktu7.setSesi((short) 3);
//            listGenSlotWaktu.add(SlotWaktu7);
//
//            SlotWaktu SlotWaktu8 = new SlotWaktu();
//            SlotWaktu8.setIdSlot(8);
//            SlotWaktu8.setSesi((short) 3);
//            listGenSlotWaktu.add(SlotWaktu8);
//
//            SlotWaktu SlotWaktu9 = new SlotWaktu();
//            SlotWaktu9.setIdSlot(9);
//            SlotWaktu9.setSesi((short) 3);
//            listGenSlotWaktu.add(SlotWaktu9);
//
//            SlotWaktu SlotWaktu10 = new SlotWaktu();
//            SlotWaktu10.setIdSlot(10);
//            SlotWaktu10.setSesi((short) 4);
//            listGenSlotWaktu.add(SlotWaktu10);
//
//            SlotWaktu SlotWaktu11 = new SlotWaktu();
//            SlotWaktu11.setIdSlot(11);
//            SlotWaktu11.setSesi((short) 4);
//            listGenSlotWaktu.add(SlotWaktu11);
//
//            SlotWaktu SlotWaktu12 = new SlotWaktu();
//            SlotWaktu12.setIdSlot(10);
//            SlotWaktu12.setSesi((short) 4);
//            listGenSlotWaktu.add(SlotWaktu12);
//
//           // List DOSEN
//            KetersediaanDosen tempSedia;
//            //########## DOSEN AI
//            Dosen AI = new Dosen();
//            AI.setInisialDosen("AI");
//            AI.setKetersediaanWaktuDosens(new ArrayList());
//            // 1
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu2);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140214"));
//            AI.getKetersediaanWaktuDosens().add(tempSedia);
//            // 2
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu3);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140214"));
//            AI.getKetersediaanWaktuDosens().add(tempSedia);
//            // 3
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu4);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140211"));
//            AI.getKetersediaanWaktuDosens().add(tempSedia);
//            // 4
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu5);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140211"));
//            AI.getKetersediaanWaktuDosens().add(tempSedia);
//            // 5
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu8);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            AI.getKetersediaanWaktuDosens().add(tempSedia);
//            // 6
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu9);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            AI.getKetersediaanWaktuDosens().add(tempSedia);
//
//            // Dosen Topik
//            AI.setBidangKeahlian(new ArrayList());
//            AI.getBidangKeahlian().add(T4);
//            AI.getBidangKeahlian().add(T3);
//
//            listGenDosen.add(AI);
//
//            //####### DOSEN AM
//            Dosen AM = new Dosen();
//            AM.setInisialDosen("AM");
//            AM.setKetersediaanWaktuDosens(new ArrayList());
//            // 1
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu12);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140211"));
//            AM.getKetersediaanWaktuDosens().add(tempSedia);
//
//            // Dosen Topik
//            AM.setBidangKeahlian(new ArrayList());
//            AM.getBidangKeahlian().add(T1);
//            AM.getBidangKeahlian().add(T4);
//            AM.getBidangKeahlian().add(T5);
//
//            listGenDosen.add(AM);
//
//            //####### DOSEN AP
//            Dosen AP = new Dosen();
//            AP.setInisialDosen("AP");
//            AP.setKetersediaanWaktuDosens(new ArrayList());
//            // 1
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu2);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140210"));
//            AP.getKetersediaanWaktuDosens().add(tempSedia);
//            // 2
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu6);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            AP.getKetersediaanWaktuDosens().add(tempSedia);
//            // 3
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu7);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            AP.getKetersediaanWaktuDosens().add(tempSedia);
//            // 4
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu10);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140213"));
//            AP.getKetersediaanWaktuDosens().add(tempSedia);
//            // 5
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu11);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140213"));
//            AP.getKetersediaanWaktuDosens().add(tempSedia);
//            // 6
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu12);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140213"));
//            AP.getKetersediaanWaktuDosens().add(tempSedia);
//
//            // Dosen Topik
//            AP.setBidangKeahlian(new ArrayList());
//            AP.getBidangKeahlian().add(T1);
//            AP.getBidangKeahlian().add(T2);
//            AP.getBidangKeahlian().add(T5);
//
//            listGenDosen.add(AP);
//
//            // ########### DOSEN BS
//            Dosen BS = new Dosen();
//            BS.setInisialDosen("BS");
//            BS.setKetersediaanWaktuDosens(new ArrayList());
//            // 1
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu1);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140214"));
//            BS.getKetersediaanWaktuDosens().add(tempSedia);
//            // 2
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu2);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140214"));
//            BS.getKetersediaanWaktuDosens().add(tempSedia);
//            // 3
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu3);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140214"));
//            BS.getKetersediaanWaktuDosens().add(tempSedia);
//            // 4
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu4);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140211"));
//            BS.getKetersediaanWaktuDosens().add(tempSedia);
//            // 5
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu5);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140211"));
//            BS.getKetersediaanWaktuDosens().add(tempSedia);
//            // 6
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu6);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140211"));
//            BS.getKetersediaanWaktuDosens().add(tempSedia);
//            // 7
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu7);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140210"));
//            BS.getKetersediaanWaktuDosens().add(tempSedia);
//            // 8
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu8);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140210"));
//            BS.getKetersediaanWaktuDosens().add(tempSedia);
//            // 9
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu10);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            BS.getKetersediaanWaktuDosens().add(tempSedia);
//            // 10
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu11);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            BS.getKetersediaanWaktuDosens().add(tempSedia);
//            // 11
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu12);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            BS.getKetersediaanWaktuDosens().add(tempSedia);
//
//            // Dosen Topik
//            BS.setBidangKeahlian(new ArrayList());
//            BS.getBidangKeahlian().add(T1);
//            BS.getBidangKeahlian().add(T3);
//            BS.getBidangKeahlian().add(T4);
//
//            listGenDosen.add(BS);
//
//            //########### DOSEN BY
//            Dosen BY = new Dosen();
//            BY.setInisialDosen("BY");
//            BY.setKetersediaanWaktuDosens(new ArrayList());
//            // 1
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu2);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140211"));
//            BY.getKetersediaanWaktuDosens().add(tempSedia);
//            // 2
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu6);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            BY.getKetersediaanWaktuDosens().add(tempSedia);
//            // 3
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu8);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140214"));
//            BY.getKetersediaanWaktuDosens().add(tempSedia);
//
//            // Dosen Topik
//            BY.setBidangKeahlian(new ArrayList());
//            BY.getBidangKeahlian().add(T1);
//            BY.getBidangKeahlian().add(T2);
//            BY.getBidangKeahlian().add(T3);
//
//            listGenDosen.add(BY);
//
//            //############ DOSEN CS
//            Dosen CS = new Dosen();
//            CS.setInisialDosen("CS");
//            CS.setKetersediaanWaktuDosens(new ArrayList());
//            // 1
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu1);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            CS.getKetersediaanWaktuDosens().add(tempSedia);
//            // 2
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu2);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            CS.getKetersediaanWaktuDosens().add(tempSedia);
//            // 3
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu4);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140211"));
//            CS.getKetersediaanWaktuDosens().add(tempSedia);
//            // 4
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu10);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            CS.getKetersediaanWaktuDosens().add(tempSedia);
//
//            // Dosen Topik
//            CS.setBidangKeahlian(new ArrayList());
//            CS.getBidangKeahlian().add(T2);
//            CS.getBidangKeahlian().add(T4);
//            CS.getBidangKeahlian().add(T5);
//
//            listGenDosen.add(CS);
//
//            //############### DOSEN DHW
//            Dosen DHW = new Dosen();
//            DHW.setInisialDosen("DHW");
//            DHW.setKetersediaanWaktuDosens(new ArrayList());
//            // 1
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu1);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140210"));
//            DHW.getKetersediaanWaktuDosens().add(tempSedia);
//            // 2
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu3);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140211"));
//            DHW.getKetersediaanWaktuDosens().add(tempSedia);
//            // 3
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu4);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140211"));
//            DHW.getKetersediaanWaktuDosens().add(tempSedia);
//            // 4
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu5);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140211"));
//            DHW.getKetersediaanWaktuDosens().add(tempSedia);
//            // 5
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu6);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            DHW.getKetersediaanWaktuDosens().add(tempSedia);
//            // 6
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu7);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            DHW.getKetersediaanWaktuDosens().add(tempSedia);
//            // 7
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu12);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140213"));
//            DHW.getKetersediaanWaktuDosens().add(tempSedia);
//
//            // Dosen Topik
//            DHW.setBidangKeahlian(new ArrayList());
//            DHW.getBidangKeahlian().add(T2);
//            DHW.getBidangKeahlian().add(T3);
//            DHW.getBidangKeahlian().add(T4);
//
//            listGenDosen.add(DHW);
//
//            //############### DOSEN DPL
//            Dosen DPL = new Dosen();
//            DPL.setInisialDosen("DPL");
//            DPL.setKetersediaanWaktuDosens(new ArrayList());
//             // 1
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu2);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140211"));
//            DPL.getKetersediaanWaktuDosens().add(tempSedia);
//             // 2
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu3);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140211"));
//            DPL.getKetersediaanWaktuDosens().add(tempSedia);
//             // 3
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu6);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            DPL.getKetersediaanWaktuDosens().add(tempSedia);
//             // 4
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu7);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            DPL.getKetersediaanWaktuDosens().add(tempSedia);
//             // 5
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu8);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            DPL.getKetersediaanWaktuDosens().add(tempSedia);
//
//            // Dosen Topik
//            DPL.setBidangKeahlian(new ArrayList());
//            DPL.getBidangKeahlian().add(T2);
//            DPL.getBidangKeahlian().add(T4);
//
//            listGenDosen.add(DPL);
//
//            //############### DOSEN FNA
//
//            Dosen FNA = new Dosen();
//            FNA.setInisialDosen("FNA");
//            FNA.setKetersediaanWaktuDosens(new ArrayList());
//             // 1
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu1);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140214"));
//            FNA.getKetersediaanWaktuDosens().add(tempSedia);
//             // 2
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu8);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140210"));
//            FNA.getKetersediaanWaktuDosens().add(tempSedia);
//             // 3
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu11);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140213"));
//            FNA.getKetersediaanWaktuDosens().add(tempSedia);
//
//            // Dosen Topik
//            FNA.setBidangKeahlian(new ArrayList());
//            FNA.getBidangKeahlian().add(T1);
//            FNA.getBidangKeahlian().add(T2);
//            FNA.getBidangKeahlian().add(T3);
//            FNA.getBidangKeahlian().add(T4);
//            FNA.getBidangKeahlian().add(T5);
//
//            listGenDosen.add(FNA);
//
//            //############### DOSEN HLL
//
//            Dosen HLL = new Dosen();
//            HLL.setInisialDosen("HLL");
//            HLL.setKetersediaanWaktuDosens(new ArrayList());
//             // 1
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu3);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140213"));
//            HLL.getKetersediaanWaktuDosens().add(tempSedia);
//             // 2
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu4);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140213"));
//            HLL.getKetersediaanWaktuDosens().add(tempSedia);
//             // 3
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu7);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            HLL.getKetersediaanWaktuDosens().add(tempSedia);
//             // 4
//            tempSedia = new KetersediaanDosen();
//            tempSedia.setSlotWaktu(SlotWaktu8);
//            tempSedia.setTanggalDsnSedia((Date) formatter.parse("20140212"));
//            HLL.getKetersediaanWaktuDosens().add(tempSedia);
//
//            HLL.setBidangKeahlian(new ArrayList());
//            HLL.getBidangKeahlian().add(T2);
//            HLL.getBidangKeahlian().add(T3);
//            HLL.getBidangKeahlian().add(T4);
//
//            listGenDosen.add(HLL);
//
//            //%%%%%%%%%% RUANGAN
//            KetersediaanRuangan tempSediaRuangan;
//            //#### RUANGAN 7601
//            Ruangan R7601 = new Ruangan();
//            R7601.setKdRuangan("7601");
//            R7601.setKetersediaanWaktuRuangan(new ArrayList());
//            // 1
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu1);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7601.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 2
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu1);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7601.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 3
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu2);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7601.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 4
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu9);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7601.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 5
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu10);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7601.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 6
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu11);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7601.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 7
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu6);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140212"));
//            R7601.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 8
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu7);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140212"));
//            R7601.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 9
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu11);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7601.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 10
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu12);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7601.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//
//            listGenRuangan.add(R7601);
//
//            // ########### RUANGAN 7602
//            Ruangan R7602 = new Ruangan();
//            R7602.setKdRuangan("7602");
//            R7602.setKetersediaanWaktuRuangan(new ArrayList());
//            // 1
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu7);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7602.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 2
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu8);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7602.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 3
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu10);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7602.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//
//            listGenRuangan.add(R7602);
//
//            // ########### RUANGAN 7603
//            Ruangan R7603 = new Ruangan();
//            R7603.setKdRuangan("7603");
//            R7603.setKetersediaanWaktuRuangan(new ArrayList());
//            // 1
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu3);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7603.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 2
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu4);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7603.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 3
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu5);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7603.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 4
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu3);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7603.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 5
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu4);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7603.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 6
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu5);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7603.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 7
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu7);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7603.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 8
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu7);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140212"));
//            R7603.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 9
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu10);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7603.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 10
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu11);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7603.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//
//            listGenRuangan.add(R7603);
//
//            // ########### RUANGAN 7604
//            Ruangan R7604 = new Ruangan();
//            R7604.setKdRuangan("7604");
//            R7604.setKetersediaanWaktuRuangan(new ArrayList());
//            // 1
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu2);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7604.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 2
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu6);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7604.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 3
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu6);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7604.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 4
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu6);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7604.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 5
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu7);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7604.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 6
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu8);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7604.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 7
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu10);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7604.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 8
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu11);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7604.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//
//            listGenRuangan.add(R7604);
//
//            // ########### RUANGAN 7605
//            Ruangan R7605 = new Ruangan();
//            R7605.setKdRuangan("7605");
//            R7605.setKetersediaanWaktuRuangan(new ArrayList());
//            // 1
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu2);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7605.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 2
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu3);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7605.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 3
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu4);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7605.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 4
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu5);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140212"));
//            R7605.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 5
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu6);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140212"));
//            R7605.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 6
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu7);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140212"));
//            R7605.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 7
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu8);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7605.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 8
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu9);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7605.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 9
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu11);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7605.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 10
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu12);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7605.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//
//            listGenRuangan.add(R7605);
//
//            // ########### RUANGAN 7606
//            Ruangan R7606 = new Ruangan();
//            R7606.setKdRuangan("7606");
//            R7606.setKetersediaanWaktuRuangan(new ArrayList());
//            // 1
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu3);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7606.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 2
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu4);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7606.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 3
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu5);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7606.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//
//            listGenRuangan.add(R7606);
//
//            // ########### RUANGAN 7607
//            Ruangan R7607 = new Ruangan();
//            R7607.setKdRuangan("7607");
//            R7607.setKetersediaanWaktuRuangan(new ArrayList());
//            // 1
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu2);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7607.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 2
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu3);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7607.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 3
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu4);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7607.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 4
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu6);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7607.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 5
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu7);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7607.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 6
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu8);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7607.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 7
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu12);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140212"));
//            R7607.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 8
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu12);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7607.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//
//            listGenRuangan.add(R7607);
//
//            // ########### RUANGAN 7608
//            Ruangan R7608 = new Ruangan();
//            R7608.setKdRuangan("7608");
//            R7608.setKetersediaanWaktuRuangan(new ArrayList());
//            // 1
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu1);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7608.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 2
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu10);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140212"));
//            R7608.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 3
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu12);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7608.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//
//            listGenRuangan.add(R7608);
//
//            // ########### RUANGAN 7609
//            Ruangan R7609 = new Ruangan();
//            R7609.setKdRuangan("7609");
//            R7609.setKetersediaanWaktuRuangan(new ArrayList());
//            // 1
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu1);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7609.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 2
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu6);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7609.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 3
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu7);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7609.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 4
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu8);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7609.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 5
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu8);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7609.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 6
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu9);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7609.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 7
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu10);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7609.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 8
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu11);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7609.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 9
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu12);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140214"));
//            R7609.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            listGenRuangan.add(R7609);
//
//            // ########### RUANGAN 7610
//            Ruangan R7610 = new Ruangan();
//            R7610.setKdRuangan("7610");
//            R7610.setKetersediaanWaktuRuangan(new ArrayList());
//            // 1
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu4);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7610.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 2
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu5);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7610.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 3
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu6);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140213"));
//            R7610.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//
//            listGenRuangan.add(R7610);
//
//            // ########### RUANGAN 7611
//            Ruangan R7611 = new Ruangan();
//            R7611.setKdRuangan("7611");
//            R7611.setKetersediaanWaktuRuangan(new ArrayList());
//            // 1
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu5);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7611.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 2
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu6);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7611.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//            // 3
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu7);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140211"));
//            R7611.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 4
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu9);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7611.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 5
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu10);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7611.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 6
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu11);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7611.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//             // 7
//            tempSediaRuangan = new KetersediaanRuangan();
//            tempSediaRuangan.setSlotWaktu(SlotWaktu12);
//            tempSediaRuangan.setTanggalRuanganSedia((Date) formatter.parse("20140210"));
//            R7611.getKetersediaanWaktuRuangan().add(tempSediaRuangan);
//
//            listGenRuangan.add(R7611);
//
//            // ############## LIST KARYA AKHIR
//              // List KA
//            KaryaAkhir KA1 = new KaryaAkhir();
//            KA1.setIdKa(1);
//            KA1.setJudulKa("Pengembangan Aplikasi Mobile Pada Sistem Operasi Android Untuk Pemeriksaan Bacaan Dan Hafalan Al-Quran");
//            KA1.setMahasiswa(M1);
//            KA1.setTopik(T3);
//            KA1.setDosenPembimbing(new ArrayList());
//            KA1.getDosensPembimbing().add(BY);
//            KA1.setStatusKa('1');
//            listGenKaryaAkhir.add(KA1);
//
//            KaryaAkhir KA2 = new KaryaAkhir();
//            KA2.setIdKa(2);
//            KA2.setJudulKa("Memprediksi Golongan Umur Pengguna Twitter Menggunakan Fitur Sosiolinguistik");
//            KA2.setMahasiswa(M2);
//            KA2.setTopik(T2);
//            KA2.setDosenPembimbing(new ArrayList());
//            KA2.getDosensPembimbing().add(CS);
//            KA2.setStatusKa('1');
//            listGenKaryaAkhir.add(KA2);
//
//            KaryaAkhir KA3 = new KaryaAkhir();
//            KA3.setIdKa(3);
//            KA3.setJudulKa("Pembangunan Aplikasi Pencarian dan Visualisasi Protein Sequence Dengan Varian Algoritma BLAST");
//            KA3.setMahasiswa(M3);
//            KA3.setTopik(T2);
//            KA3.setDosenPembimbing(new ArrayList());
//            KA3.getDosensPembimbing().add(BY);
//            KA3.setStatusKa('1');
//            listGenKaryaAkhir.add(KA3);
//
//            KaryaAkhir KA4 = new KaryaAkhir();
//            KA4.setIdKa(4);
//            KA4.setJudulKa("Pengembangan Aplikasi Kamar Pas Daring (On-line)");
//            KA4.setMahasiswa(M4);
//            KA4.setTopik(T2);
//            KA4.setDosenPembimbing(new ArrayList());
//            KA4.getDosensPembimbing().add(AP);
//            KA4.setStatusKa('1');
//            listGenKaryaAkhir.add(KA4);
//
//            KaryaAkhir KA5 = new KaryaAkhir();
//            KA5.setIdKa(5);
//            KA5.setJudulKa("Pembangunan E-Commerce Untuk Petani Pedesaan");
//            KA5.setMahasiswa(M5);
//            KA5.setTopik(T2);
//            KA5.setDosenPembimbing(new ArrayList());
//            KA5.getDosensPembimbing().add(AP);
//            KA5.setStatusKa('1');
//            listGenKaryaAkhir.add(KA5);
//
//            KaryaAkhir KA6 = new KaryaAkhir();
//            KA6.setIdKa(6);
//            KA6.setJudulKa("Pembangunan Framework Aplikasi-Klien Untuk Bisnis Peer-toPeer Berbasis Lokasi Pada Platform Android");
//            KA6.setMahasiswa(M6);
//            KA6.setTopik(T4);
//            KA6.setDosenPembimbing(new ArrayList());
//            KA6.getDosensPembimbing().add(HLL);
//            KA6.setStatusKa('1');
//            listGenKaryaAkhir.add(KA6);
//
//            KaryaAkhir KA7 = new KaryaAkhir();
//            KA7.setIdKa(7);
//            KA7.setJudulKa("Aplikasi Pengidentifikasi Penutur Berbasis Support Vector Machine");
//            KA7.setMahasiswa(M7);
//            KA7.setTopik(T4);
//            KA7.setDosenPembimbing(new ArrayList());
//            KA7.getDosensPembimbing().add(AI);
//            KA7.setStatusKa('1');
//            listGenKaryaAkhir.add(KA7);
//
//            KaryaAkhir KA8 = new KaryaAkhir();
//            KA8.setIdKa(8);
//            KA8.setJudulKa("Interaksi Aplikasi Pada Mobile Device Untuk Mendukung Software Development");
//            KA8.setMahasiswa(M8);
//            KA8.setTopik(T3);
//            KA8.setDosenPembimbing(new ArrayList());
//            KA8.getDosensPembimbing().add(AI);
//            KA8.setStatusKa('1');
//            listGenKaryaAkhir.add(KA8);
//
//            KaryaAkhir KA9 = new KaryaAkhir();
//            KA9.setIdKa(9);
//            KA9.setJudulKa("Pengembangan Model 3D Parametrik Dengan Penambahan Tulang Pada Objek Manusia");
//            KA9.setMahasiswa(M9);
//            KA9.setTopik(T5);
//            KA9.setDosenPembimbing(new ArrayList());
//            KA9.getDosensPembimbing().add(FNA);
//            KA9.setStatusKa('1');
//            listGenKaryaAkhir.add(KA9);
//
//            KaryaAkhir KA10 = new KaryaAkhir();
//            KA10.setIdKa(10);
//            KA10.setJudulKa("Alat Bantu Manajemen Proyek Perangkat Lunak Dengan Scrum Di Lingkungan Akademis");
//            KA10.setMahasiswa(M10);
//            KA10.setTopik(T4);
//            KA10.setDosenPembimbing(new ArrayList());
//            KA10.getDosensPembimbing().add(FNA);
//            KA10.setStatusKa('1');
//            listGenKaryaAkhir.add(KA10);
//
//
//
//        } catch (ParseException ex) {
//            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }

    public static DataSource getInstance (){
        return instance;
    }

    public static void resetData() {
        //instance = new DataSource();
        index = 0;       
    }
    
    public List<Dosen> getRandDosen(KaryaAkhir ka) {
        List<Dosen> listDosen = new ArrayList();
        boolean duplicateStatus, topikStatus;
        int i=0;
        int counter = 0;
        while (i<Konstanta.SIZE_DOSEN_PENGUJI){
            duplicateStatus = false;
            topikStatus = false;
            int index = (int) (Math.random() * listGenDosen.size());
            for (int j=0; j<listDosen.size(); j++) {
                if (listDosen.get(j).getInisialDosen().equals(listGenDosen.get(index).getInisialDosen())) {
                    duplicateStatus = true;
                    j = listDosen.size(); // stop looping when same dosen
                }
            }
            
            List<Topik> listTopik = listGenDosen.get(index).getBidangKeahlian();
            for (int k=0; k<listTopik.size(); k++) {
                if (listTopik.get(k).getIdTopik() == ka.getTopik().getIdTopik()) {
                    topikStatus = true;
                    k = listTopik.size(); // stop looping when same topik
                }
            }

            if (!duplicateStatus) {
                if (topikStatus || (counter > (listGenDosen.size() * listGenDosen.size()))) {
                    listDosen.add(listGenDosen.get(index));
                    i++;
                    counter = 0;
                }
            }
            counter++;
        }

        return listDosen;
    }

    public KaryaAkhir getRandKA() {
        KaryaAkhir temp = listGenKaryaAkhir.get(index);
        index++;
        return temp;
    }

    public Ruangan getRandRuangan() {
        int index = (int) (Math.random() * listGenRuangan.size());

        return listGenRuangan.get(index);
    }

    public SlotWaktu getRandWaktu() {
        int index = (int) (Math.random() * listGenSlotWaktu.size());

        return listGenSlotWaktu.get(index);
    }

    public Date getRandDate() {
        int index = (int) (Math.random() * listDatePeriode.size());
        return listDatePeriode.get(index);
    }

    public List<Dosen> getListGenDosen() {
        return listGenDosen;
    }

    public void setListGenDosen(List<Dosen> listGenDosen) {
        this.listGenDosen = listGenDosen;
    }

    public List<KaryaAkhir> getListGenKaryaAkhir() {
        return listGenKaryaAkhir;
    }

    public void setListGenKaryaAkhir(List<KaryaAkhir> listGenKaryaAkhir) {
        this.listGenKaryaAkhir = listGenKaryaAkhir;
    }

    public List<Ruangan> getListGenRuangan() {
        return listGenRuangan;
    }

    public void setListGenRuangan(List<Ruangan> listGenRuangan) {
        this.listGenRuangan = listGenRuangan;
    }

    public List<SlotWaktu> getListGenSlotWaktu() {
        return listGenSlotWaktu;
    }

    public void setListGenSlotWaktu(List<SlotWaktu> listGenSlotWaktu) {
        this.listGenSlotWaktu = listGenSlotWaktu;
    }

    public Periode getPeriodeJadwal() {
        return periodeJadwal;
    }

    public void setPeriodeJadwal(Periode periodeJadwal) {
        this.periodeJadwal = periodeJadwal;

            Calendar tempPeriodeAwal = Calendar.getInstance();
            tempPeriodeAwal.setTime(periodeJadwal.getPeriodeAwal());
            Calendar tempPeriodeAkhir = Calendar.getInstance();
            tempPeriodeAkhir.setTime(periodeJadwal.getPeriodeAkhir());
            listDatePeriode = new ArrayList();
            while(tempPeriodeAwal.before(tempPeriodeAkhir)){
                listDatePeriode.add(tempPeriodeAwal.getTime());
                tempPeriodeAwal.add(Calendar.DATE, 1);
            }
    }
}
