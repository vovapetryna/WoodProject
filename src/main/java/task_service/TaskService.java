package task_service;

import client_worker_service.ClientWorkerService;
import client_worker_service.Worker;
import department_service.Department;
import storage_service.RawMaterial;
import storage_service.StorageService;
import java.util.LinkedList;

public final class TaskService {
    final private LinkedList<OrderTask>    taskJournal = new LinkedList<>();
    final private LinkedList<OrderTask>    taskList = new LinkedList<>();
    final private LinkedList<CommonTaskIO> templates = new LinkedList<>();
    final private StorageService           storageServiceEndPoint;
    final private ClientWorkerService      workerServiceEndPoint;

    public TaskService(StorageService storageService,
                       ClientWorkerService workerService){
        this.storageServiceEndPoint = storageService;
        this.workerServiceEndPoint = workerService;
    }

    public void addOrder(LinkedList<CommonTaskIO> tasks,
                         LinkedList<Worker> executors,
                         Department responsibleDepartment){

        OrderTask tempOrder = new OrderTask(tasks,
                executors,
                responsibleDepartment);

        taskList.add(tempOrder);
        taskJournal.add(tempOrder);
    }

    public void finalizeOrders(){
        for (OrderTask order: taskList){
            finalizeOrder(order, new LinkedList<>());
        }
    }

    public void finalizeOrder(OrderTask finalizedOrder,
                              LinkedList<CommonTaskIO> sideTasks){

        OrderResLog orderRes = finalizedOrder.finalizeOrder(sideTasks);

        for (Worker worker: finalizedOrder.getExecutors()){
            workerServiceEndPoint.addWorkerBalance(worker, orderRes.totalWage);
        }

        storageServiceEndPoint.PushMaterials(orderRes.listProducts);
        storageServiceEndPoint.GetMaterials(orderRes.listMaterials);

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

        return new CommonTaskIO(material,
                product,
                pricePerUnit,
                wageFundPerUnit);
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

        return null;
    }
}
