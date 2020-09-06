package task_service;

import client_worker_service.ClientWorkerService;
import client_worker_service.Worker;
import department_service.Department;
import org.javatuples.Quartet;
import storage_service.RawMaterial;
import storage_service.StorageService;

import java.util.LinkedList;

public class TaskService {
    LinkedList<OrderTask>       taskJournal = new LinkedList<OrderTask>();
    LinkedList<OrderTask>       taskList = new LinkedList<OrderTask>();

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
}
