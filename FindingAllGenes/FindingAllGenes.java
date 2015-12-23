package FindingAllGenes;
import edu.duke.*;
/**
 * @ created by Viktoria 
 * @ 08.11.205
 */
public class FindingAllGenes {

    public int findStopIndex(String dna, int index){
        int stop1 = dna.indexOf("tag", index);
        if (stop1 == -1 || (stop1- index)%3 != 0){
            stop1 = dna.length();
        }
        int stop2 = dna.indexOf("tga", index);
        if (stop2 == -1 || (stop2 - index)%3 != 0){
            stop2 = dna.length();
        }
        int stop3 = dna.indexOf("taa",index);
        if (stop3 == -1 || (stop3 - index)%3 != 0){
            stop3= dna.length();
        }

        return Math.min(stop1, Math.min(stop2, stop3));

    }

    public StorageResource storeAll(String dna){
        StorageResource recorder = new StorageResource();
        dna = dna.toLowerCase();
        int start = 0;
        while (true){
            int startCodonInd = dna.indexOf("atg", start);

            if (startCodonInd == -1){
                break ;
            }
            int stopReturn = findStopIndex(dna,startCodonInd + 3);

            if (stopReturn < dna.length()){
                String result = dna.substring(startCodonInd, stopReturn + 3);  
                recorder.add(result);
                start = stopReturn+3; 

            }
            else {
                start = startCodonInd+3;
            }

        }
        return recorder;        
    }

    public float cgRatio (String item){
        int countC = 0;
        int startC = 0;
        while (true) {
            int pos = item.indexOf("c", startC);
            if (pos == -1) {
                break;
            }
            countC += 1;
            startC = pos+1;
        }
        int countG = 0;
        int startG = 0; 
        while (true) {
            int pos2 = item.indexOf("g", startG);
            if (pos2 == -1) {
                break;
            }
            countG+= 1;
            startG = pos2+1;
        }
        return (float)(countC + countG)/item.length();
    }

    public void printGenes(StorageResource recorder){
        int countMoreThan60 = 0;
        int countMoreThan035 = 0;
        for (String item: recorder.data()){
            if(item.length() > 60 ){
                //System.out.println(item);
                countMoreThan60++;
            }

            float valueGC = cgRatio(item);

            if(valueGC > 0.35){
                //System.out.println(item);
                countMoreThan035++;
            }

        }
        System.out.println("More than 60 is " + countMoreThan60);
        System.out.println("cg ratio " + countMoreThan035);
    }

    public void testStorageFinder(){
        FileResource fr = new FileResource();
        String dna = fr.asString();
        StorageResource recorder = storeAll(dna);
        System.out.println("Size is " + recorder.size());
        printGenes(recorder);
         int count = 0;
         int theLongest = 0;
        for(String item: recorder.data()){
            int getItem = item.length();
            if (getItem > theLongest){
               theLongest = getItem;
            }
            int start = 0;
            while (true){
                int startCTG = item.indexOf("ctg",start);
                if (startCTG==-1){
                    break;
                }
                start = startCTG+3;
                count++;
            }
         
        }
     System.out.println("The number of CTG is" + count);
     System.out.println("The longest string is " + theLongest);
    }
}