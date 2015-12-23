package SCVFilesTask;

/**
 * Write a description of CSVTaskOne here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class CSVTaskOne {
    public void tester(){
        FileResource files = new FileResource();
        CSVParser parser = files.getCSVParser();
        //countryInfo(parser, "Nauru");
        //parser = files.getCSVParser();
        //listExportersTwoProducts(parser,"cotton" ,"flowers");
        parser = files.getCSVParser(); 
        int a = numberOfExporters(parser, "sugar");
        System.out.println (a);
        parser = files.getCSVParser(); 
        bigExporters(parser, "$999,999,999,999" );
        
    }

    public String countryInfo (CSVParser parser, String country){

        for (CSVRecord record: parser){
            String countryValue = record.get("Country");
            if (countryValue.contains(country)){
                // String coutry1 = record.get("Country");
                String countryAns = (countryValue + ':') + " " + (record.get("Exports")) + ':' + " " + 
                    (record.get("Value (dollars)"));
                System.out.println(countryAns);
                return countryAns;

            }

        }

        return("NOT FOUND");

    }

    public void listExportersTwoProducts (CSVParser parser, String exportItemOne, String exportItemTwo){
        for (CSVRecord record: parser){
            String exportItem = record.get("Exports");
            if (exportItem.contains(exportItemOne)&& exportItem.contains(exportItemTwo)){
                System.out.println("Have both two items :" + " " + record.get( "Country"));
            }
        }
    }

    public int numberOfExporters(CSVParser parser, String exportItem){
        int i = 0;
        for (CSVRecord record: parser){

            String exportItem1 = record.get("Exports");
            if (exportItem1.contains(exportItem)){
               // System.out.println(record.get("Country"));
                i++;

            }
        }
        return i ;    
    }

    public void bigExporters (CSVParser parser,String amount ){
        for (CSVRecord record: parser){
            String value = record.get("Value (dollars)");
            if (value.length() > amount.length()){
                System.out.println("Value more than amount :" + " " + (record.get( "Country")) + " " + value);
         
            }

        }
    }
}