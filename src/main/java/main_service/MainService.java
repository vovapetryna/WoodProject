package main_service;

import client_worker_service.ClientWorkerService;
import client_worker_service.Specialization;
import client_worker_service.Worker;
import department_service.Department;
import department_service.Departments;
import org.javatuples.Triplet;
import storage_service.StorageChangeType;
import storage_service.StorageService;
import task_service.CommonTaskIO;
import task_service.OrderTask;
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

    public void addWorker(String name,
                            String surname){
        System.out.println("Добавление рабочего: " + name + " " + surname);

        clientWorkerService.addWorker(name,
                surname,
                Specialization.sawmill,
                5,
                15000.0,
                (Department)null);
    }

    public Worker getWorkerSurname(String surname){
        return clientWorkerService.geWorkerSurname(surname);
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
        LinkedList<CommonTaskIO> tasks = new LinkedList<CommonTaskIO>();

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
                (Department)null);
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
