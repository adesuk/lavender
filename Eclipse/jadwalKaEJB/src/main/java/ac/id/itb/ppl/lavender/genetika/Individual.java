/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ac.id.itb.ppl.lavender.genetika;

/**
 *
 * @author Asri Maspupah
 */
public class Individual {
     static int defaultGeneLength = DataSource.getInstance().getListGenKaryaAkhir().size();
     private Kromosom[] kromosoms = new Kromosom[defaultGeneLength];
     // Cache
     private float fitness = 0f;
    
     public Individual(){
       DataSource.resetData();
     }

     // Create a random individual
     public void generateIndividual() {
         for (int i = 0; i < size(); i++) {
          //   System.out.println("k-" + i);
             Kromosom gene = Kromosom.GenerateKromosom();
             kromosoms[i] = gene;
         }
     }

    /* Getters and setters */
     // Use this if you want to create individuals with different gene lengths
     public static void setDefaultGeneLength(int length) {
         defaultGeneLength = length;
     }

     public Kromosom getKromosom(int index) {
         return kromosoms[index];
     }

    public void setKromosom(int index, Kromosom value) {
         kromosoms[index] = value;
         fitness = 0;
     }

    /* Public methods */
     public int size() {
         return kromosoms.length;
     }

    public float getFitness() {
         if (fitness == 0f) {
             this.fitness = FitnessCalc.getFitness(this);
         }
         return this.fitness;
     }

    @Override
     public String toString() {
         String geneString = "";
         for (int i = 0; i < size(); i++) {
             geneString += getKromosom(i);
         }
         return geneString;
     }
}
