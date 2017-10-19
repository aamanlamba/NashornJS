package NashornJS;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

public class ImportRunnable implements Runnable {
    private String file;
    public List<Record> listOfRecords = new ArrayList<Record>();
    public Map<String, Record> mapOfRecords = new HashMap<>();
    
    public ImportRunnable(String file) {
        this.file = file;
    }

    @Override
    public void run() {
        Reader in;
        Iterable<CSVRecord> records = null;
        try {
            in = new FileReader(file);
            records = CSVFormat.EXCEL.parse(in);
           for(CSVRecord record :records) {
        	   	listOfRecords.add(new Record(record));
        	   	mapOfRecords.put(record.get(1), new Record(record));
        	   	System.out.println(record);
           }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e) {
        		e.printStackTrace();
        }

        try {
            int count = 0;
  

            assert records != null;
            for (CSVRecord record : records) {
                count++;

                String input = record.get("input");
      
                String output = record.get("output");
      

         
            }

 
        } finally {

        }

    }
}