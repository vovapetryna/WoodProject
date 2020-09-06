package utils_service;

import junit.framework.TestCase;
import org.javatuples.Triplet;
import org.junit.Test;
import storage_service.StorageChangeType;

import java.lang.reflect.Method;
import java.util.LinkedList;

public class CSVReaderTest extends TestCase {
    CSVReader reader = new CSVReader(",");

    @Test
    public void testConvertCsvDataToActionList(){
        LinkedList<Triplet<StorageChangeType, Double, String>> testList =
            reader.getListView("src/data/StorageServiceTestValuse.csv");

//        for (Triplet<StorageChangeType, Double, String> item: testList)
//            System.out.println(item.getValue0().name() + " " +
//                    item.getValue1().toString() + " " +
//                    item.getValue2());

        assertEquals(testList.size(), 107);

    }
}