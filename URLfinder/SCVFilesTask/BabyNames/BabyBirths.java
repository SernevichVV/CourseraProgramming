package SCVFilesTask.BabyNames;
import edu.duke.*;
import java.io.*;
import org.apache.commons.csv.*;

/**
 * Write a description of BabyBirths here.
 * 
 * @author Viktoria 
 * @version 21 11 2015 */
public class BabyBirths {
    public void printNames(){
        FileResource fr = new FileResource();
        for(CSVRecord record:fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(record.get(2));
            if (numBorn <=100){
                System.out.println("Name is  " +  record.get(0) + " " + 
                    "Gender is  " + record.get(1) + " " + 
                    "Num born  " + record.get(2));

            }
        }
    }

    public void totalBirths(FileResource fr){
        int totalBirths = 0;
        int totalGirls= 0;
        int totalBoys = 0;
        int numbTotalName = 0;
        int numbGirlsName = 0;
        int numbBoysName = 0;
        for(CSVRecord record:fr.getCSVParser(false)){
            int numBorn = Integer.parseInt(record.get(2));
            totalBirths += numBorn;
            numbTotalName++;
            if (record.get(1).equals("F")){
                totalGirls+=numBorn;
                numbGirlsName++;
            }
            else{
                totalBoys+=numBorn;
                numbBoysName++;
            }
        }
        System.out.println("Total Births =  " + totalBirths);
        System.out.println("Total Girls =  " + totalGirls);
        System.out.println("Total Boys =  " + totalBoys);
        System.out.println("Total Births name =  " + numbTotalName);
        System.out.println("Total Girls name =  " + numbGirlsName);
        System.out.println("Total Boys name =  " + numbBoysName);
    }

    public void testTotalBirths(){
        FileResource fr = new FileResource();
        totalBirths(fr);
    }

    public FileResource convertIntToFile(int year){
        // изменить в зависимости от года и задания (year yob1888)(decade yob1900s)
        String nameOfFile = "D:\\Vika\\Desktop\\Coursera img\\us_babynames_by_year\\yob"+year+".csv";
        FileResource nameFr = new FileResource(nameOfFile);
        return nameFr;
    }

    public int getRank(int year, String name,String gender){
        FileResource frRank = convertIntToFile(year);
        int girlRank = 0;
        int boyRank = 0;
        for(CSVRecord record:frRank.getCSVParser(false)){
            if (record.get(1).equals("F")){
                if (gender.equals("F")) {
                    girlRank++;
                    if (name.equals(record.get(0))){
                        return girlRank;
                    }
                }
            }

            if (record.get(1).equals("M")){
                if (gender.equals("M")){
                    boyRank++;
                    if (name.equals(record.get(0))){
                        return boyRank;
                    }
                }   
            }
        }
        return -1;
    }

    public void testGetRank(){
        int nameRank = getRank(1960,"Emily", "F");
        System.out.println(nameRank);

    }

    public String getName(int year, int rank, String gender){
        FileResource frRank = convertIntToFile(year);
        int girlRank = 0;
        int boyRank = 0;
        for(CSVRecord record:frRank.getCSVParser(false)){
            if (record.get(1).equals("F")){
                if (gender.equals("F")){
                    girlRank++;
                    if (girlRank == rank){
                        String rankGirlName = record.get(0);
                        return rankGirlName;
                    }
                }
            }
            if (record.get(1).equals("M")){
                if (gender.equals("M")){
                    boyRank++;
                    if (boyRank == rank){
                        String rankBoysName =  record.get(0);
                        return rankBoysName;
                    }
                }   
            }
        }
        return "no name";
    }

    public void testGetName(){
        String name  = getName(1980, 350   ,"F");
        System.out.println(name);

    }

    public String whatIsNameInYear (String name, int year, int newYear, String gender){
        //FileResource frRank = convertIntToFile(year);
        //FileResource frRank = convertIntToFile(newYear);
        int nameRankForNY = getRank(year, name,gender);
        System.out.println("The rank of the name is " + nameRankForNY);
        String newName = getName(newYear,nameRankForNY, gender);
        System.out.println( name + "  born in  " + year + "  would be  " + newName + "  if she was born in  " + newYear);
        return newName;
    }

    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int largestSoFar = 0;
        String nameOfFile = null;
        for(File f: dr.selectedFiles()){
            String nameF = f.getName();
            int year = Integer.parseInt(nameF.substring(3,7));
            FileResource fr = new FileResource(f);
            int currentRow = getRank(year,name,gender);

            int newLargestSoFar = getLargestOFTwo(currentRow, largestSoFar);
            if (newLargestSoFar != largestSoFar){
                nameOfFile = f.getName();
                largestSoFar = newLargestSoFar;
            }
        }
        int yearHidestRank = Integer.parseInt(nameOfFile.substring(3,7));
        return yearHidestRank;
    }

    public int getLargestOFTwo( int currentRow, int largestSoFar){

        if (largestSoFar == 0){
            largestSoFar = currentRow;
        }
        else {

            if(currentRow > largestSoFar){
                largestSoFar = currentRow;
            }

        }
        return largestSoFar;
    }

    public double getAverageRank (String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int totalRank = 0;
        double numOfRank = 0;
        for(File f :dr.selectedFiles()){
            String nameF = f.getName();
            int year = Integer.parseInt(nameF.substring(3,7));
            int yearRank = getRank(year,name,gender);
            totalRank +=yearRank;
            numOfRank++;
        }
        double averageRank = totalRank/numOfRank;
        return averageRank;
    }

    public int getTotalBirthsRankedHigher (int year, String name,String gender){
        FileResource fr = convertIntToFile(year);
        int summRank = 0;
        for(CSVRecord record:fr.getCSVParser(false)){
            if(record.get(1).equals(gender)){
                if (record.get(0).equals(name)){
                    return summRank;
                }
                summRank += Integer.parseInt(record.get(2));
            }

        }
        return -1;
    }
}
