package org.example;

import client_worker_service.ClientProvider;
import client_worker_service.ClientWorkerService;
import department_service.Departments;
import org.javatuples.Triplet;
import storage_service.StorageChangeType;
import storage_service.StorageService;
import utils_service.CSVReader;

import java.time.LocalDateTime;
import java.util.LinkedList;


public class App 
{
    public static void main( String[] args ) {
        CSVReader reader = new CSVReader(",");
        String csvFile = "src/data/StorageServiceTestValuse.csv";

        //Services declaration
        Departments departments = new Departments();
        StorageService storageService = new StorageService();
        ClientWorkerService clientWorkerService = new ClientWorkerService();

        departments.addDepartment("Sawmill_0");
        departments.addDepartment("Sawmill_1");
        departments.addDepartment("Sawmill_2");
        departments.addDepartment("Sawmill_3");

        ClientProvider provider1 = clientWorkerService.addProvider("Vasyl",
                                                                "Petrov",
                                                                "111-111-1111",
                                                                "test@gmail.com",
                                                                "DTEK"
                                                                );

        ClientProvider provider2 = clientWorkerService.addProvider("Petro",
                                                                "Tatarov",
                                                                "111-111-2222",
                                                                "test@gmail.com",
                                                                "CherkassLes"
                                                                 );


        System.out.println("Storage servise testing (income / outcome)");
        LinkedList<Triplet<StorageChangeType, Double, String>> incomeOutcomeJournal =
                reader.getListView(csvFile);

        for (Triplet<StorageChangeType, Double, String> item: incomeOutcomeJournal){
            if (item.getValue0() == StorageChangeType.income){
                storageService.PushMaterials(item.getValue2(),
                        provider1,
                        0.0,
                        item.getValue1(),
                        departments.getDepartmentByName("Sawmill_2"),
                        LocalDateTime.now()
                        );
            }else{
                storageService.GetMaterials(item.getValue2(),
                        provider1,
                        0.0,
                        item.getValue1(),
                        departments.getDepartmentByName("Sawmill_2"),
                        LocalDateTime.now()
                );
            }
        }

    }
}
