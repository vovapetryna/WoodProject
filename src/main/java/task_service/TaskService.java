package task_service;

import client_worker_service.ClientProvider;
import client_worker_service.ClientWorkerService;
import client_worker_service.Worker;
import department_service.Department;
import org.javatuples.Quartet;
import storage_service.RawMaterial;
import storage_service.StorageChangeType;
import storage_service.StorageService;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class TaskService {
    LinkedList<OrderTask>       taskJournal = new LinkedList<OrderTask>();
    LinkedList<OrderTask>       taskList = new LinkedList<OrderTask>();

    LinkedList<CommonTaskIO>       templates = new LinkedList<CommonTaskIO>();

    StorageService              storageServiceEndPoint;
    ClientWorkerService         workerServiceEndPoint;

    public TaskService(StorageService storageService,
                       ClientWorkerService workerService){
        this.storageServiceEndPoint = storageService;
        this.workerServiceEndPoint = workerService;
    }

    public OrderTask addOrder(LinkedList<CommonTaskIO> tasks,
                         LinkedList<Worker> executors,
                         Department responsibleDepartment){

        OrderTask tempOrder = new OrderTask(tasks,
                                            executors,
                                            responsibleDepartment);

        taskList.add(tempOrder);
        taskJournal.add(tempOrder);

        return tempOrder;
    }

    public void finalizeOrders(){
        for (OrderTask order: taskList){
            finalizeOrder(order, new LinkedList<CommonTaskIO>());
        }
    }

    public void finalizeOrder(OrderTask finalizedOrder,
                              LinkedList<CommonTaskIO> sideTasks){

        Quartet<Double, Double, LinkedList<RawMaterial>, LinkedList<RawMaterial>> orderRes =
                finalizedOrder.finalizeOrder(sideTasks);

        for (Worker worker: finalizedOrder.getExecutors()){
            workerServiceEndPoint.addWorkerBalance(worker, orderRes.getValue1());
        }

        storageServiceEndPoint.PushMaterials(orderRes.getValue2());
        storageServiceEndPoint.GetMaterials(orderRes.getValue3());

        taskList.remove(finalizedOrder);
    }

    public CommonTaskIO createCommonTask(String materialType,
                                      double materialValue,
                                      String productType,
                                      double productValue,
                                      double pricePerUnit,
                                      double wageFundPerUnit
                                      ){
        RawMaterial material = new RawMaterial(materialType, materialValue);
        RawMaterial product = new RawMaterial(productType, productValue);

        CommonTaskIO tempTask = new CommonTaskIO(material,
                product,
                pricePerUnit,
                wageFundPerUnit);

        return tempTask;
    }

    public void addCommonTask(String materialType,
                         double materialValue,
                         String productType,
                         double productValue,
                         double pricePerUnit,
                         double wageFundPerUnit){

        templates.add(createCommonTask(materialType,
                materialValue,
                productType,
                productValue,
                pricePerUnit,
                wageFundPerUnit));
    }

    public CommonTaskIO getCommonTask(String materialType,
                                      String productType){

        CommonTaskIO tempTask = createCommonTask(materialType,
                                                0.0,
                                                productType,
                                                0.0,
                                                0.0,
                                                0.0);

        for (CommonTaskIO task: templates){
            if (task.sameMaterialProduct(tempTask))
                return task;
        }

        return (CommonTaskIO)null;
    }
}
