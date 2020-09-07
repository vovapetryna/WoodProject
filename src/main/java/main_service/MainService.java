package main_service;

import client_worker_service.*;
import department_service.Departments;
import org.javatuples.Triplet;
import storage_service.StorageChangeType;
import storage_service.StorageService;
import task_service.CommonTaskIO;
import task_service.TaskService;
import utils_service.CsvReader;

import java.time.LocalDateTime;
import java.util.LinkedList;

public final class MainService {
    final private Departments departments =                   new Departments();
    final private StorageService storageService =             new StorageService();
    final private ClientWorkerService clientWorkerService =   new ClientWorkerService();
    final private TaskService taskService =                   new TaskService(storageService,
            clientWorkerService);

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

        Person newProvider = new Person(name, surname);
        Contacts newContacts = new Contacts(phoneNumber, email);

        clientWorkerService.addProvider(newProvider,
                newContacts,
                companyName);
    }

    public void addWorker(String name,
                          String surname){
        System.out.println("Добавление рабочего: " + name + " " + surname);

        Person newWorker = new Person(name, surname);
        clientWorkerService.addWorker(newWorker, null);
    }

    public Worker getWorkerSurname(String surname){
        return clientWorkerService.geWorkerSurname(surname);
    }

    public void preloadStorageService(String providerCompany,
                                      String departmentName){
        System.out.println("Записываем последние изменения по: " + departmentName + " связанные с :" + providerCompany);

        CsvReader reader = new CsvReader(",");

        String csvFile = "src/data/StorageServiceTestValuse.csv";
        LinkedList<Triplet<StorageChangeType, Double, String>> incomeOutcomeJournal =
                reader.getListView(csvFile);

        for (Triplet<StorageChangeType, Double, String> item: incomeOutcomeJournal){
            if (item.getValue0() == StorageChangeType.income){
                storageService.PushMaterials(item.getValue2(),
                        clientWorkerService.getProviderCompany(providerCompany),
                        0.0,
                        item.getValue1(),
                        departments.getDepartmentByName(departmentName));
            }else{
                storageService.GetMaterials(item.getValue2(),
                        clientWorkerService.getProviderCompany(providerCompany),
                        0.0,
                        item.getValue1(),
                        departments.getDepartmentByName(departmentName));
            }
        }
    }

    public void addCommonTask(String materialType,
                              double materialValue,
                              String productType,
                              double productValue,
                              double pricePerUnit,
                              double wageFundPerUnit){
        System.out.println("Создаем темплейт задачи производства: " +
                materialType + " => " + productType);

        taskService.addCommonTask(materialType,
                materialValue,
                productType,
                productValue,
                pricePerUnit,
                wageFundPerUnit);
    }

    public void addOrder(String[][] orderMap,
                         double[] valueMaterial,
                         double[] valueProduct,
                         LinkedList<Worker> executors){
        LinkedList<CommonTaskIO> tasks = new LinkedList<>();

        for (int i=0; i<orderMap.length; i++){
            CommonTaskIO tempTask = taskService.getCommonTask(orderMap[i][0], orderMap[i][1]).clone();
            tempTask.setParameters(valueMaterial[i], valueProduct[i]);
            tasks.add(tempTask);
        }

        System.out.println("Размещаем заказ следующих операций:");
        for (CommonTaskIO task: tasks){
            System.out.println(task.toString());
        }
        System.out.println("Для следующих рабочих");
        for (Worker worker: executors){
            System.out.println(worker.toString());
        }

        taskService.addOrder(tasks,
                executors,
                null);
    }

    public void finalizeOrders(){
        System.out.println("Закрытие всех активных Заказов с последующим анализом результатов");

        taskService.finalizeOrders();
    }

    public void printLastChanges(){
        System.out.println("Сохраненные послдение изменения на складе:");

        storageService.printLastChanges();
    }

}
