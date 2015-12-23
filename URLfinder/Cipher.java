import edu.duke.*;
import java.lang.*;
/**
 * Write a description of Cipher here.
 * this class 
 * 
 * @author Viktoria 
 * @version (a version number or a date)
 */
public class Cipher {
    public boolean isVowel (char ch){
        char c = Character.toLowerCase(ch);
        if (c == 'a'|| c == 'e' || c == 'i'|| c =='o' || c == 'u'){
            return true; 
        }
        return false;
    }

    public String replaceVowels (String phrase, char ch){
        StringBuilder changed = new StringBuilder(phrase);
        for(int i = 0; i < changed.length(); i++){
            char currentChar = changed.charAt(i);
            boolean vowel = isVowel(currentChar); 
            if(vowel == true){
                currentChar = ch;
            }
            changed.setCharAt(i,currentChar);
        }
        return changed.toString();
    }

    public String emphasize(String phrase, char ch){
        StringBuilder retutnString = new StringBuilder(phrase);
        for(int i = 0; i < retutnString.length(); i++){
            char currentChar = retutnString.charAt(i);
            char upCh = Character.toUpperCase(ch);
            if(currentChar == ch && i%2 == 0 || currentChar == upCh && i%2 == 0){
                currentChar = '*'; 
            }
            if(currentChar == ch && i%2 != 0||currentChar == upCh && i%2 != 0){
                currentChar = '+'; 
            } 
            retutnString.setCharAt(i,currentChar);
        }
         return retutnString.toString();
    }

}