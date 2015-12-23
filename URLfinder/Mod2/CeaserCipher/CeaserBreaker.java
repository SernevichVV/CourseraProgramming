package Mod2.CeaserCipher;

/**
 * Write a description of CeaserBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CeaserBreaker {

    /* public String decrypt(String encrypted){
    TaskTwo cc = new TaskTwo();
    int[] fregs = countLetters(encrypted);
    int maxDex = maxIndex(fregs);
    int dkey = maxDex - 4;
    if (maxDex < 4){
    dkey = 26 - (4 - maxDex);
    }
    return cc.enctypt (encrypted, 26 - dkey);
    }
     */
    public int maxIndex( int[] vals){
        int maxDex = 0;
        for (int i = 0; i < vals.length; i ++){
            if(vals[i] > vals[maxDex]){
                maxDex = i;
            }

        }
        return maxDex;
    }
   

    public int[] countLetters( String message){
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int [26];
        for(int i= 0; i < message.length(); i++){
            char ch = Character.toLowerCase(message.charAt(i));
            int dex = alphabet.indexOf(ch);
            if(dex!=-1){
                counts [dex]+=1;
            }
        }
        return counts;
    }

    public String halfOfString (String message, int start){
        StringBuilder builder = new StringBuilder();
        for (int i = start; i < message.length(); i+=2){
            char currentChar = message.charAt(i);
            builder.append(currentChar);
        }
        return builder.toString();
    }

    public int getKey (String s){
        int[] fregs = countLetters(s);
        int maxDex = maxIndex(fregs);
        int dkey = maxDex - 4;
        if (maxDex < 4){
            dkey = 26 - (4 - maxDex);
        }
       return dkey;
    }

    public void testDecrypt (int key){
         TaskTwo cc = new TaskTwo();
         String message = cc.enctypt(message, 26 - key);
        }
    }

    