package task_service;

import client_worker_service.Worker;
import department_service.Department;
import storage_service.RawMaterial;

import java.util.LinkedList;

public final class OrderTask{
    final private LinkedList<Worker>          executors;
    final private LinkedList<CommonTaskIO>    tasks;
    final private Department                  responsibleDepartment; //Will be used with additional analytics service

    double totalWage;
    double totalPrice;

    public OrderTask(LinkedList<CommonTaskIO> tasks,
                     LinkedList<Worker> executors,
                     Department responsibleDepartment){

        this.tasks = tasks;
        this.executors = executors;
        this.responsibleDepartment = responsibleDepartment;
    }

    public LinkedList<Worker> getExecutors(){
        return executors;
    }

    public OrderResLog finalizeOrder(LinkedList<CommonTaskIO> sideTask){
        totalPrice = 0.0;
        totalWage = 0.0;
        LinkedList<RawMaterial> resProducts = new LinkedList<>();
        LinkedList<RawMaterial> resMaterials = new LinkedList<>();

        for (CommonTaskIO task: tasks){
            TaskResLog taskRes = task.finalizeTask();
            totalPrice += taskRes.totalPrice;
            totalWage += taskRes.totalWage;
            resProducts.add(taskRes.product);
            resMaterials.add(taskRes.material);
        }

        for (CommonTaskIO task: sideTask){
            TaskResLog taskRes = task.finalizeTask();
            totalWage += taskRes.totalWage;
            resProducts.add(taskRes.product);
            resMaterials.add(taskRes.material);
        }

        return  new OrderResLog(totalPrice,
                (totalWage / executors.size()),
                resProducts,
                resMaterials);
    }
}
