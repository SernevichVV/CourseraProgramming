
/**
 * Write a description of TagFinder here.
 * 
 * @author Viktoria 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;

public class TagFinder {
    public String findGenes(String dna){
        dna.toLowerCase();
        int start = dna.indexOf("atg");
        int end = dna.indexOf("tag",start+3);
        int endTwo = dna.indexOf("tga", start+3);
        int endThree = dna.indexOf("taa",start+3);
        if (start == -1){
        return "The tag was not found";
        }
        if((end - start)%3 ==0){
           return dna.substring(start,end + 3);
        }
        if((endTwo - start)%3 ==0){
            return dna.substring(start,endTwo + 3);
        }
        if((endThree - start)%3 ==0){
            return dna.substring(start,endThree + 3);
        }
        
        return "";
    }
}