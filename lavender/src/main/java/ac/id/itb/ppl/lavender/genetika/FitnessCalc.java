/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.genetika;

import ac.id.itb.ppl.lavender.model.Dosen;
import ac.id.itb.ppl.lavender.model.KetersediaanWaktuDosen;
import ac.id.itb.ppl.lavender.model.KetersediaanRuangan;
import ac.id.itb.ppl.lavender.model.Topik;
import java.util.List;

/**
 *
 * @author Asri Maspupah
 */
public class FitnessCalc {
     static Kromosom[] solution = new Kromosom[64];

    /* Public methods */
     // Set a candidate solution as a byte array
     public static void setSolution(Kromosom[] newSolution) {
         solution = newSolution;
     }

      
    // Calculate inidividuals fittness by comparing it to our candidate solution
     public static float getFitness(Individual individual) {
         float fitness = 0f;
         int pinalty = 0;

         // Loop through our individuals genes and compare them to our cadidates
         for (int i = 0; i < individual.size(); i++) {
             pinalty += individual.getKromosom(i).getPinalty();

         }
         fitness = (float) 1/(1+pinalty);
         return fitness;
     }

     private static int checkDuplicateWaktu (Kromosom kromosom, Individual individual){
           int pinalty =0;

           for (int j = 0; j<individual.size();j++){
                    Kromosom pointer2 = individual.getKromosom(j);
                    if (pointer2 != null){
                        if ((kromosom.getGenRuangan().getKdRuangan().equals(pointer2.getGenRuangan().getKdRuangan())) &&
                            (kromosom.getGenWaktu().getIdSlot() == pointer2.getGenWaktu().getIdSlot()) &&
                            (kromosom.getGenDate().compareTo(pointer2.getGenDate())==0) &&
                            (kromosom.getGenKA().getIdKa() != pointer2.getGenKA().getIdKa())){
                            pinalty++;
                        }
                    }
        }

           return pinalty;
     }

    private static int checkDosenPengujiSlotWaktu (Kromosom kromosom){
        int pinalty = 0;
         boolean statusPinalty = true;
         for (int j=0; j<kromosom.getGenDosenPenguji().size(); j++){
             Dosen tempDosen = kromosom.getGenDosenPenguji().get(j);
             List<KetersediaanWaktuDosen> listSDosen = tempDosen.getKetersediaanWaktuDosens();
             for (int i=0; i<listSDosen.size(); i++){
                if (kromosom.getGenWaktu().getIdSlot() ==  listSDosen.get(i).getSlotWaktu().getIdSlot() &&
                    kromosom.getGenDate().compareTo(listSDosen.get(i).getTanggalDsnSedia()) == 0){
                    statusPinalty = false;
                    i = listSDosen.size();
                }
             }
             if (statusPinalty){
               // System.out.println("pinalty Dosen waktu ");
                pinalty++;
             }else{
                statusPinalty = true;
             }
         }

         return pinalty;
    }

    private static int checkDosenPembimbingSlotWaktu (Kromosom kromosom){
         int pinalty = 0;
         boolean statusPinalty = true;
         List<Dosen> tempPembimbing = kromosom.getGenKA().getDosenPembimbing();
         for (int j=0; j<tempPembimbing.size(); j++){
             statusPinalty = true;
             Dosen tempDosen = tempPembimbing.get(j);
             List<KetersediaanWaktuDosen> listSDosen = tempDosen.getKetersediaanWaktuDosens();
             for (int i=0; i<listSDosen.size(); i++){
                if (kromosom.getGenWaktu().getIdSlot() ==  listSDosen.get(i).getSlotWaktu().getIdSlot() &&
                    kromosom.getGenDate().compareTo(listSDosen.get(i).getTanggalDsnSedia()) == 0){
                    statusPinalty = false;
                    i = listSDosen.size();
                }
             }
             if (statusPinalty){
               // System.out.println("pinalty Dosen waktu ");
                pinalty++;
             }  
             
         }

         return pinalty;
    }

    private static int checkDosenPengujiTopik (Kromosom kromosom){
         int pinalty = 0;
         boolean statusPinalty = true;
         for (int j=0; j<kromosom.getGenDosenPenguji().size(); j++){
             Dosen tempDosen = kromosom.getGenDosenPenguji().get(j);
             List<Topik> listSDosen = tempDosen.getBidangKeahlian();
             for (int i=0; i<listSDosen.size(); i++){
                if (kromosom.getGenKA().getTopik().getIdTopik() ==  listSDosen.get(i).getIdTopik()){
                    statusPinalty = false;
                    i = listSDosen.size();
                }
             }
             if (statusPinalty){
               // System.out.println("pinalty Dosen waktu ");
                pinalty++;
             }else{
                statusPinalty = true;
             }
         }

         return pinalty;
    }
    private static int checkDosenPengujiPembimbing (Kromosom kromosom){
         int pinalty = 0;
         boolean statusPinalty = false;
         List<Dosen> listPembimbing = kromosom.getGenKA().getDosenPembimbing();
         for (int j=0; j<listPembimbing.size(); j++){
             Dosen pembimbing = listPembimbing.get(j);
             List<Dosen> listPenguji = kromosom.getGenDosenPenguji();
             for (int i=0; i<listPenguji.size(); i++){
                if (pembimbing.getInisialDosen().equals(listPenguji.get(i).getInisialDosen())){
                    statusPinalty = true;
                    i = listPenguji.size();
                }
             }
             if (statusPinalty){
               // System.out.println("pinalty Dosen waktu ");
                pinalty++;
             }else{
                statusPinalty = false;
             }
         }

         return pinalty;
    }
    private static int checkDosenRuanganWaktu (Kromosom kromosom){
         int pinalty = 0;
         boolean statusPinalty = true;
         List<KetersediaanRuangan> listSRuangan = kromosom.getGenRuangan().getKetersediaanWaktuRuangan();
         for (int i=0; i<listSRuangan.size(); i++){
            if (kromosom.getGenWaktu().getIdSlot() ==  listSRuangan.get(i).getSlotWaktu().getIdSlot() &&
                kromosom.getGenDate().compareTo(listSRuangan.get(i).getTanggalRuanganSedia())== 0 ){
                statusPinalty = false;
                i = listSRuangan.size();
            }
         }
         if (statusPinalty){
            //System.out.println("pinalty Ruangan waktu ");
            pinalty++;
          }else{
            statusPinalty = true;
         }

         return pinalty;
    }
    public static int getPinalty(Kromosom kromosom, Individual individual) {

         int pinalty = 0;
         // cek pinalty Dosen penguji dengan waktu
         pinalty += checkDosenPengujiSlotWaktu(kromosom);
         // cek pinalty Dosen pembimbing dengan waktu
         pinalty += checkDosenPembimbingSlotWaktu(kromosom);
         // cek pinalty dosen Penguji dengan topik
         pinalty += checkDosenPengujiTopik(kromosom);
         // cek pinalty dosen Penguji dengan pembimbing
        pinalty += checkDosenPengujiPembimbing(kromosom);
         // cek pinalty Ruangan dan waktu
         pinalty += checkDosenRuanganWaktu(kromosom);
         if (individual != null){
             pinalty += checkDuplicateWaktu(kromosom, individual);
        }
         return pinalty;
     }
     // Get optimum fitness
     static int getMaxFitness() {
         int maxFitness = 1;
         return maxFitness;
     }
}
