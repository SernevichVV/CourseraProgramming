package Mod2.CeaserCipher;
import edu.duke.*;
import java.io.*;

/**
 * Write a description of TaskTwo here.
 * 
 * @author Viktoria 
 * @version (a version number or a date)
 */
public class TaskTwo {
    public String enctypt(String input, int key){
        StringBuilder impString = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key) + alphabet.substring(0, key);
        for(int i = 0; i < impString.length(); i++){
            char currentChar = impString.charAt(i);
            int ind = alphabet.indexOf(currentChar);
            if(ind!= -1){
                char newChar = shiftedAlphabet.charAt(ind);
                impString.setCharAt(i, newChar);
            }
            else{
                char upChar = Character.toUpperCase(impString.charAt(i));
                int ind2 = alphabet.indexOf(upChar);
                if( ind2!= -1){
                    char newChar = Character.toLowerCase(shiftedAlphabet.charAt(ind2));
                    impString.setCharAt(i, newChar);
                }
            }

        }
        return impString.toString();
    }

    public void testTaskTwo(){
        FileResource fr = new FileResource();
        String massage = fr.asString();
        int key = 21;
        int key2 = 8;
        String enctypt1 = encryptTwoKeys(massage,key,key2);
        System.out.println(enctypt1);
        String decrypt = encryptTwoKeys(massage,26,26);
        System.out.println(decrypt);

    }

    public String encryptTwoKeys (String input, int key1, int key2){
        StringBuilder impString = new StringBuilder(input);
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String shiftedAlphabet = alphabet.substring(key1) + alphabet.substring(0, key1);
        String shiftedAlphabet2 = alphabet.substring(key2) + alphabet.substring(0, key2);

        for(int i = 0; i < impString.length(); i++){
            char currentChar = impString.charAt(i);
            int ind = alphabet.indexOf(currentChar);
            char curUpChar = Character.toUpperCase(currentChar);
            int indUCh = alphabet.indexOf(curUpChar);
            if (i%2 == 0&& ind!=-1){
                char newChar = shiftedAlphabet.charAt(ind);
                impString.setCharAt(i, newChar);
            }

            else{
                if(i%2 == 0&& indUCh!=-1){
                    char newChar = Character.toLowerCase(shiftedAlphabet.charAt(indUCh));
                    impString.setCharAt(i, newChar);
                }
            }

            if(i%2!= 0 && ind!=-1){
                char newChar = shiftedAlphabet2.charAt(ind);
                impString.setCharAt(i, newChar);
            }

            else{
                if(i%2 != 0&& indUCh!=-1){
                    char newChar = Character.toLowerCase(shiftedAlphabet2.charAt(indUCh));
                    impString.setCharAt(i, newChar);
                }
            }
        }
        return impString.toString();

    }
    
}

    
    

