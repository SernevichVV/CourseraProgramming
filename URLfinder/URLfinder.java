
/**
 * Write a description of URLfinder here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import java.io.*;
public class URLfinder {

    public StorageResource findURL(String url){
        URLResource testTwo = new URLResource(url);
        String allURL = testTwo.asString();
        String lowURL = allURL.toLowerCase();
        StorageResource stor = new StorageResource();
        int startCount = 0;
        while(true){
            int hrefIndex = lowURL.indexOf("href=", startCount);
            if(hrefIndex == -1){
                break;
            }
            int firstQ = hrefIndex + 6;
            int endQ = lowURL.indexOf("\"", firstQ);
            String sub = lowURL.substring(firstQ, endQ );
            if(sub.startsWith("http")){
                stor.add(sub);

            }
            startCount = endQ+1;
        }
        return stor;
    }

    public void testURLWithStorage(){
        StorageResource st1 = findURL("http://www.dukelearntoprogram.com/course2/data/newyorktimes.html");
        System.out.println(st1);
        System.out.println("The numbers of all URL is " + st1.size());
        int countS = 0;
        int countCom = 0;
        int countComor = 0;
        int countChar = 0;
        for (String link: st1.data()){
            int startCount=0;
             while (true){
                int dotIndex = link.indexOf('.', startCount);
                if(dotIndex ==-1){
                    break;

                }
                startCount = dotIndex+1;
                countChar++;
            }
            if (link.startsWith("https")){
                countS++;
            }
            if (link.indexOf(".com")!=-1){
                countCom++;
            }
            if (link.endsWith(".com/")||link.endsWith(".com")){
                countComor++;
            }
           
           
        }
        System.out.println("The number of https links is  " + countS);
        System.out.println("The number of com. links is  " + countCom);
        System.out.println("The number of comor. links is  " + countComor);
        System.out.println("The number of char in  links is  " + countChar);
    }
}