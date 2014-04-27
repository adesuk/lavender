/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.genetika;

/**
 *
 * @author Asri Maspupah
 */
public class GenetikaAlgorithm {

    /* GA parameters */
     private static final double uniformRate = 0.5;
     private static final double mutationRate = 0.015;
     private static final int tournamentSize = 5;
     private static final boolean elitism = true;

    /* Public methods */

     // Evolve a population
     public static Population evolvePopulation(Population pop) {
         Population newPopulation = new Population(pop.size(), false);

        // Keep our best individual
         if (elitism) {
             newPopulation.saveIndividual(0, pop.getFittest());
         }

        // Crossover population
         int elitismOffset;
         if (elitism) {
             elitismOffset = 1;
         } else {
             elitismOffset = 0;
         }
         // Loop over the population size and create new individuals with
         // crossover
         for (int i = elitismOffset; i < pop.size(); i++) {
          //   System.out.println("cros : indv-" + i);
             Individual indiv1 = tournamentSelection(pop);
             Individual indiv2 = tournamentSelection(pop);
             Individual newIndiv = crossover(indiv1, indiv2);
             newPopulation.saveIndividual(i, newIndiv);
         }

        // Mutate population
         for (int i = elitismOffset; i < newPopulation.size(); i++) {
           //  System.out.println("mutasi : indv-" + i);
             mutate(newPopulation.getIndividual(i));
         }

        return newPopulation;
     }

    // Crossover individuals
     private static Individual crossover(Individual indiv1, Individual indiv2) {
         Individual newSol = new Individual();
         // Loop through genes
         for (int i = 0; i < indiv1.size(); i++) {
        //     System.out.println("cros : k-" + i);
             // Crossover
             Kromosom newKromosom;
             if (Math.random() <= uniformRate && indiv1.getKromosom(i).getPinalty() != 0) {
                 newKromosom = indiv1.getKromosom(i);
                 if (indiv1.getKromosom(i).getGenKA().getTopik().getIdTopik() ==
                     indiv2.getKromosom(i).getGenKA().getTopik().getIdTopik()){
                    newKromosom.setGenDosenPenguji(indiv2.getKromosom(i).getGenDosenPenguji());
                 }
                 newKromosom.setGenRuangan(indiv2.getKromosom(i).getGenRuangan());
                 newKromosom.setGenWaktu(indiv2.getKromosom(i).getGenWaktu());
                 newKromosom.setGenDate(indiv2.getKromosom(i).getGenDate());
              //   newKromosom.setPinalty();
                 newKromosom.setPinalty(FitnessCalc.getPinalty(newKromosom, newSol));
                 newSol.setKromosom(i, newKromosom);
             } else {
                 /*newKromosom = indiv2.getKromosom(i);
                 if (indiv1.getKromosom(i).getGenKA().getTopik().getIdTopik() ==
                     indiv2.getKromosom(i).getGenKA().getTopik().getIdTopik()){
                    newKromosom.setGenDosenPenguji(indiv1.getKromosom(i).getGenDosenPenguji());
                }
                 newKromosom.setGenRuangan(indiv1.getKromosom(i).getGenRuangan());
                 newKromosom.setGenWaktu(indiv1.getKromosom(i).getGenWaktu());
                 newKromosom.setGenDate(indiv1.getKromosom(i).getGenDate());
                 newKromosom.setPinalty();
                 newSol.setKromosom(i, newKromosom);*/
                 indiv1.getKromosom(i).setPinalty(FitnessCalc.getPinalty(indiv1.getKromosom(i), newSol));
                 newSol.setKromosom(i, indiv1.getKromosom(i));

             }
         }
         return newSol;
     }

    // Mutate an individual
     private static void mutate(Individual indiv) {
         // Loop through genes
         for (int i = 0; i < indiv.size(); i++) {
           //  System.out.println("mutasi : k-" + i);
             Kromosom kromosom = indiv.getKromosom(i);
             if (kromosom.getPinalty() != 0){
           /*  if (Math.random() <= mutationRate) {
                 // Create random gene
                 kromosom = Kromosom.UpdateGenDosen(kromosom);
                 // Create random gene
                 kromosom = Kromosom.UpdateGenWaktu(kromosom);
                 // Create random gene
                 kromosom = Kromosom.UpdateGenRuangan(kromosom);
                 // Create random gene
                 kromosom = Kromosom.UpdateGenDate(kromosom);
             }*/
            
                 // mutate Dosen Penguji
                 if (Math.random() <= mutationRate) {
                     // Create random gene
                     kromosom = Kromosom.UpdateGenDosen(kromosom);
                 }

                 // mutate Slot Waktu
                 if (Math.random() <= mutationRate) {
                     // Create random gene
                     kromosom = Kromosom.UpdateGenWaktu(kromosom);
                 }

                 // mutate Ruangan
                 if (Math.random() <= mutationRate) {
                     // Create random gene
                     kromosom = Kromosom.UpdateGenRuangan(kromosom);
                 }
                 // mutate Date
                  if (Math.random() <= mutationRate) {
                     // Create random gene
                     kromosom = Kromosom.UpdateGenDate(kromosom);
                 }
             
//             kromosom.setPinalty();
               kromosom.setPinalty(FitnessCalc.getPinalty(kromosom, indiv));
             }
         }
     }

    // Select individuals for crossover
     private static Individual tournamentSelection(Population pop) {
         // Create a tournament population
         Population tournament = new Population(tournamentSize, false);
         // For each place in the tournament get a random individual
         for (int i = 0; i < tournamentSize; i++) {
             int randomId = (int) (Math.random() * pop.size());
             tournament.saveIndividual(i, pop.getIndividual(randomId));
         }
         // Get the fittest
         Individual fittest = tournament.getFittest();
         return fittest;
     }
}
