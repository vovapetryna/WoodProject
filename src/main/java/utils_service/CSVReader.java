package utils_service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.javatuples.Triplet;
import storage_service.StorageChangeType;

public class CSVReader {
    BufferedReader          br = null;
    String                  line = "";
    String                  cvsSplitBy = ",";
    String[]                header;

    LinkedList<String[]> csvData;

    public CSVReader(String csvSplitBy){
        this.line = "";
        this.cvsSplitBy = csvSplitBy;
    }

    private void readData(String csvFile){
        boolean isHeader = true;
        csvData = new LinkedList<String[]>();

        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] lineData = line.split(cvsSplitBy);

                if (isHeader){
                    isHeader = false;
                    header = lineData;
                }else
                    csvData.add(lineData);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public LinkedList<Triplet<StorageChangeType, Double, String>>
    getListView(String csvFile){
        readData(csvFile);

        LinkedList<String> itemList = new LinkedList<String>();

        LinkedList<Triplet<StorageChangeType, Double, String>> changeLog =
                new LinkedList<Triplet<StorageChangeType, Double, String>>();

        for (String word: header)
            if (!word.equals(""))
                itemList.add(word);

        for (String[] line: csvData){
            for (int i=0; i<line.length; i++){
                if (!line[i].equals("")){
                    if (i % 2 == 0){
                        changeLog.add(new Triplet<StorageChangeType, Double, String>(
                                StorageChangeType.income,
                                Double.parseDouble(line[i]),
                                itemList.get(i / 2)
                        ));
                    }else{
                        changeLog.add(new Triplet<StorageChangeType, Double, String>(
                                StorageChangeType.outcome,
                                Double.parseDouble(line[i]),
                                itemList.get(i / 2)
                        ));
                    }
                }
            }
        }

        return changeLog;
    }

    @Override
    public String toString() {
        for(String[] line : csvData){
            for(String word : line){
                System.out.print("<" + word + ">");
            }
            System.out.println();
        }
        return "CSReader{" +
                "csvData=" + csvData +
                '}';
    }
}
