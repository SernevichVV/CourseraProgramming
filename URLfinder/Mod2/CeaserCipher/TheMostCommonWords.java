package Mod2.CeaserCipher;
import edu.duke.*;
/**
 * Write a description of TheMostCommonWords here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TheMostCommonWords {
    public String[] getCommon(){
        // it will throw Exception, change the name of fr for valid way to file
        FileResource fr = new FileResource("D:\\Vika\\Coursera\\CommonWordsData.zip\\common.txt");
        String [] common = new String [20];
        int index = 0;
        for(String a: fr.words()){
            common[index]= a;
            index +=1;
        }
        return common;
    }

    public int indexOf(String[]list,String word){
        for(int i = 0; i < list.length; i++){
            if(list[i].equals(word)){
                return i;
            }
        }
        return -1;
    }

    public void countWords(FileResource fr, String [] common, int [] counts){
        for (String word:fr.words()){
            word = word.toLowerCase();
            int index = indexOf(common,word);
            if (index != -1){
                counts[index]+=1;
            }

        }
    }

    public void CountShakespeare(){
        String[] plays = {"small.txt"};
        String[] common = getCommon(); 
        int[] counts = new int [common.length];
        for (int i = 0; i < plays.length; i++){
            FileResource resource = new FileResource("D:\\Vika\\Coursera\\CommonWordsData.zip\\" + plays[i]);
            countWords(resource, common, counts);
            System.out.println("Done with" + plays[i]);
        }

        for(int k = 0; k <common.length; k++){
            System.out.println(common[k] + "\t" + counts[k]);

        }

    }
}
