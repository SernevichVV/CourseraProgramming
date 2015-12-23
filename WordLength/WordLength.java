package Mod2.CeaserCipher;
import edu.duke.*;
/**
 * Write a description of WordLength here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class WordLength {
    public int[]countWordLengths(FileResource resource, int[] count){

        for (String s: resource.words()){
            int length = s.length();
            char start = s.charAt(0);
            char end = s.charAt(length-1);
            if(Character.isLetter(start)==false){
                length -= 1;
            }
            if(Character.isLetter(end)==false){
                length -= 1;
            }
            if (length < 29){
                count[length] +=1;   
            }
            else {
                count [29]+=1;
            }

        }
        return count;
    }

    public int indexOfMax(int[] values){
        int maxInd = 0;
        for(int i = 0; i < values.length; i++){
            if(values[i] > values[maxInd]){
                maxInd = i;
            }
        }
        return maxInd;
    }

    public void testCountWordLengths (){
        FileResource fr = new FileResource();
        int[]count = new int[31];
        int countWords[] = countWordLengths(fr, count);
        for(int i = 0; i <countWords.length; i++){
            System.out.println("The number of "  + i + " words, is  " + countWords[i]);
        }
        int maX = indexOfMax(countWords);
        System.out.println(maX);
    }
}

