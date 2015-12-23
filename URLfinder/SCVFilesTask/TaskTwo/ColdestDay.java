package SCVFilesTask.TaskTwo;
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

/**
 * Write a description of HottesDay here.
 * 
 * @author Viktoriia 
 * @version (a version number or a date)
 */
public class ColdestDay {
    public CSVRecord coldestHourInAFile(CSVParser parser){
        CSVRecord largestSoFar = null;
        for (CSVRecord currentRow :parser){
            largestSoFar = getLargestOFTwo(currentRow, largestSoFar);
        }
        return largestSoFar;
    }

    public String coldestInManyDays(){
        CSVRecord largestSoFar = null;
        String nameOfFile = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = coldestHourInAFile(fr.getCSVParser ());
            largestSoFar = getLargestOFTwo(currentRow, largestSoFar);
            if (largestSoFar == currentRow){
                nameOfFile = f.getName();
            }

        }
        return nameOfFile;
    }

    public CSVRecord getLargestOFTwo(CSVRecord currentRow, CSVRecord largestSoFar){

        if (largestSoFar == null){
            largestSoFar = currentRow;
        }
        double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
        double largestTemp = Double.parseDouble(largestSoFar.get("TemperatureF"));
        if (currentTemp == -9999){
            currentRow = largestSoFar;
        }
        else {

            if(currentTemp < largestTemp){
                largestSoFar = currentRow;
            }

        }
        return largestSoFar;
    }

    public void testColdestInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
       // String nameOfTheColdestFile = coldestInManyDays();
        CSVRecord coldestHour = coldestHourInAFile(parser);
        System.out.println("The coldest hour was " + coldestHour.get("TemperatureF")); //+ coldest.get("TemperatureF") + "at the time of " + coldest.get ("TimeEST"));

       // System.out.println("The coldest day was in a file " + nameOfTheColdestFile);

    }

    public CSVRecord lowestHumidityInFile (CSVParser parser){
        CSVRecord lowestHumiditySoFar = null;
        for (CSVRecord currentRow:parser){
            lowestHumiditySoFar = theLowestHumidityOFTwo(currentRow,lowestHumiditySoFar);
        }
        return lowestHumiditySoFar;
    }

    public CSVRecord theLowestHumidityOFTwo(CSVRecord currentRow, CSVRecord lowestHumiditySoFar){
        if (lowestHumiditySoFar == null){
            lowestHumiditySoFar = currentRow;
        }
        double lowestHum = Double.parseDouble(lowestHumiditySoFar.get("Humidity"));
        String humidity = currentRow.get("Humidity");
        if ( humidity.equals("N/A")){}
        else{
            double currentHum = Double.parseDouble(humidity);
            if(currentHum < lowestHum){
                lowestHumiditySoFar = currentRow;
            }

        }
        return lowestHumiditySoFar;
    }

    public void testLowestHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was "  + csv.get("Humidity") + " at " + csv.get("DateUTC")) ;
    }

    public CSVRecord LowestHumidityInManyFiles(){
        CSVRecord largestSoFar = null;
        DirectoryResource dr = new DirectoryResource();
        for(File f: dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currentRow = lowestHumidityInFile(fr.getCSVParser ());
            largestSoFar = theLowestHumidityOFTwo(currentRow, largestSoFar);
        }
        return largestSoFar;

    }

    public double averageTemperatureInFile (CSVParser parser){
        int i = 0;
        double total = 0;
        for (CSVRecord cr: parser){
            double temp = Double.parseDouble(cr.get("TemperatureF"));
            total += temp;
            i++;
        }
        double summ = total/i;
        return summ;
    }

    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double average =  averageTemperatureInFile(parser);
        System.out.println("The average temperature in file is " + average);
    }

    public double averageTemperatureWithHighHumidityInFile (CSVParser parser, int value){
        int i = 0;
        double total = 0;
        for (CSVRecord rc :parser){
            int humidityL = Integer.parseInt(rc.get("Humidity"));
            
            if( humidityL >= value){
                double temp = Double.parseDouble(rc.get("TemperatureF"));
                total += temp;
                i++;

            }
            else {
                System.out.println ("No temperatures with that humidity");
            }
        }
        double summ = total/i;
        return summ;
    }

    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        double avarageWithHumidity = averageTemperatureWithHighHumidityInFile(parser, 80);
        System.out.println("Average Temp when high Humidity is " + avarageWithHumidity );
    }
}
