package utils_service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

import org.javatuples.Triplet;
import storage_service.StorageChangeType;

public final class CsvReader {
    private BufferedReader          br = null;
    private String                  line;
    final private String            cvsSplitBy;
    private String[]                header;

    private LinkedList<String[]> csvData;

    public CsvReader(String csvSplitBy){
        this.line = "";
        this.cvsSplitBy = csvSplitBy;
    }

    private void readData(String csvFile){
        boolean isHeader = true;
        csvData = new LinkedList<>();

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

        LinkedList<String> itemList = new LinkedList<>();

        LinkedList<Triplet<StorageChangeType, Double, String>> changeLog =
                new LinkedList<>();

        for (String word: header)
            if (!word.equals(""))
                itemList.add(word);

        for (String[] line: csvData){
            for (int i=0; i<line.length; i++){
                if (!line[i].equals("")){
                    if (i % 2 == 0){
                        changeLog.add(new Triplet<>(
                                StorageChangeType.income,
                                Double.parseDouble(line[i]),
                                itemList.get(i / 2)
                        ));
                    }else{
                        changeLog.add(new Triplet<>(
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
