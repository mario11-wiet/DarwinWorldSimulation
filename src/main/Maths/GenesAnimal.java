package Maths;

import java.util.Arrays;
import java.util.Random;

public class GenesAnimal extends GenesCorrect {
    private int[] genes= new int[32];

    public int[] createGenesForAnimal()
    {
        int randomGene;
        Random genarator = new Random();
        for(int i=0;i< genes.length;i++)
        {
            randomGene=genarator.nextInt(8);
            genes[i]=randomGene;

        }
        Arrays.sort(genes);
        GenesCorrect(genes);


        return genes;
    }
    public int dominatingGene(){
        int [] countGenes= new int[8];
        for (int i=0;i<8;i++)
        {
            countGenes[i]=0;
        }
        for (int i=0;i<genes.length;i++)
        {
            countGenes[genes[i]]=countGenes[genes[i]]+1;
        }
        int dominating=0;
        for(int i=1;i<countGenes.length;i++)
        {
            if(countGenes[i]>countGenes[dominating])
            {
                dominating=i;
            }
        }
        return dominating;
    }
    public int[] genesInheritFromParents(GenesAnimal genes1, GenesAnimal genes2){
        int[] randomPartitionGanes=new int[2];
        Random genarator = new Random();
        for(int i=0;i<randomPartitionGanes.length;i++)
        {
            randomPartitionGanes[i]=genarator.nextInt(32);
        }
        Arrays.sort(randomPartitionGanes);
        for(int i=0;i<genes.length;i++)
        {
            if(i<=randomPartitionGanes[0] || i>=randomPartitionGanes[1]){
                genes[i]=genes1.getAllGenes()[i];
            }
            else
            {
                genes[i]=genes2.getAllGenes()[i];
            }
        }
        Arrays.sort(genes);
        GenesCorrect(genes);
        return genes;
    }
    public int[] getAllGenes(){
        return genes;
    }
    public int getGenes(int numerOfGen){
        return genes[numerOfGen];
    }
    public String toString(){
        return Arrays.toString(genes);
    }

}
