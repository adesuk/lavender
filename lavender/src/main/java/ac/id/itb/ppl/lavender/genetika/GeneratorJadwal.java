/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.genetika;

import ac.id.itb.ppl.lavender.model.Jadwal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Asri Maspupah
 */
public class GeneratorJadwal {
    public List<Jadwal> generateJadwal (){
        List <Jadwal> jadwalS = new ArrayList();
        // Create an initial population
         Population myPop = new Population(50, true);

         // Evolve our population until we reach an optimum solution
         int generationCount = 0;
         int konfergenCount = 0;
         float prevFitness = 0f;
         while (myPop.getFittest().getFitness() < FitnessCalc.getMaxFitness()  && konfergenCount<5000) {
             generationCount++;
             System.out.println("Generation: " + generationCount + " Fittest: " + myPop.getFittest().getFitness());
             if (myPop.getFittest().getFitness() == prevFitness){
                konfergenCount++;
             }else {
                konfergenCount = 0;
             }
             prevFitness = myPop.getFittest().getFitness();

             myPop = GenetikaAlgorithm.evolvePopulation(myPop);
         }
         
         System.out.println("Solution found!");
         System.out.println("Generation: " + generationCount+1);
         System.out.println("Fitnes: " + myPop.getFittest().getFitness());
         System.out.println("Genes:");
         System.out.println(myPop.getFittest());
         
         if(myPop.getFittest().getFitness() < 1){
             return null;
         }else {
            Individual result = myPop.getFittest();
            System.out.println("ID SLOT WAKTU" + "\t"+
                               "DATE" + "\t"+
                               "ID_RUANGAN" +"\t"+
                               "ID_KA"+"\t"+
                               "ID_PEMBIMBING"+"\t"+
                               "ID_DOSEN");
            Date genDate = Calendar.getInstance().getTime();
            for (int i = 0; i<result.size(); i++){
               Kromosom dataJadwal = result.getKromosom(i);
               System.out.println(dataJadwal.genWaktu.getIdSlot() + "\t"+
                                  dataJadwal.genDate.toString() + "\t"+
                                  dataJadwal.genRuangan.getKdRuangan() +"\t"+
                                  dataJadwal.getGenKA().getIdKa()+"\t"+
                                  dataJadwal.getGenKA().getDosenPembimbing().get(0).getInisialDosen()+"\t"+
                                  dataJadwal.getGenDosenPenguji().get(0).getInisialDosen());
               Jadwal element = new Jadwal();
               element.setDosenPenguji(dataJadwal.getGenDosenPenguji());
               element.setGenerateDate(genDate);
               element.setKaryaAkhir(dataJadwal.getGenKA());
               element.setRuangan(dataJadwal.getGenRuangan());
               element.setSlotWaktu(dataJadwal.getGenWaktu());
               element.setTanggal(dataJadwal.getGenDate());
               element.setStatusHasilPelaksanaan(BigInteger.valueOf(0));
               element.setStatusPelaksanaan(BigInteger.valueOf(0));
               jadwalS.add(element);
            }

            return jadwalS;
         }
    }

}
