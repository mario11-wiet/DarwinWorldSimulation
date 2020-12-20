package Maths;

import java.util.Arrays;
import java.util.Random;

public class GenesCorrect {
    public int[] GenesCorrect(int[] genes){
        int numberGene = 0;
        int randomPosition;
        Random genarator = new Random();
        while (numberGene!=7)
        {
            numberGene=-1;
            for (int gene : genes) {
                if (gene > numberGene + 1) {
                    numberGene = gene - 1;
                    randomPosition=genarator.nextInt(32);
                    genes[randomPosition]=numberGene;
                    Arrays.sort(genes);
                    break;
                }
                if (numberGene != gene) {
                    numberGene = gene;
                }
            }
            if(genes[genes.length-1]!=7)
            {
                randomPosition=genarator.nextInt(32);
                genes[randomPosition]=7;
                Arrays.sort(genes);
            }
        }
        return genes;
    }

}
