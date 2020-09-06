package main_service;

import client_worker_service.ClientWorkerService;
import department_service.Departments;
import org.javatuples.Triplet;
import storage_service.StorageChangeType;
import storage_service.StorageService;
import task_service.TaskService;
import utils_service.CSVReader;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class MainService {
    private Departments departments =                   new Departments();
    private StorageService storageService =             new StorageService();
    private ClientWorkerService clientWorkerService =   new ClientWorkerService();
    private TaskService taskService =                   new TaskService(storageService,
                                                                clientWorkerService);

    private String csvFile = "src/data/StorageServiceTestValuse.csv";



    public void addDepartment(String name){
        System.out.println("Доавление цеха под названием: " + name);
        departments.addDepartment(name);
    }

    public void addProvider(String name,
                            String surname,
                            String phoneNumber,
                            String email,
                            String companyName){
        System.out.println("Добавление поставщика: " + companyName);

        clientWorkerService.addProvider(name,
                surname,
                phoneNumber,
                email,
                companyName);
    }

    public void preloadStorageService(String providerCompany,
                                      String departmentName){
        System.out.println("Записываем последние изменения по: " + departmentName + " связанные с :" + providerCompany);

        CSVReader reader = new CSVReader(",");

        LinkedList<Triplet<StorageChangeType, Double, String>> incomeOutcomeJournal =
                reader.getListView(csvFile);

        for (Triplet<StorageChangeType, Double, String> item: incomeOutcomeJournal){
            if (item.getValue0() == StorageChangeType.income){
                storageService.PushMaterials(item.getValue2(),
                        clientWorkerService.getProviderCompany(providerCompany),
                        0.0,
                        item.getValue1(),
                        departments.getDepartmentByName(departmentName),
                        LocalDateTime.now()
                );
            }else{
                storageService.GetMaterials(item.getValue2(),
                        clientWorkerService.getProviderCompany(providerCompany),
                        0.0,
                        item.getValue1(),
                        departments.getDepartmentByName(departmentName),
                        LocalDateTime.now()
                );
            }
        }
    }

}
