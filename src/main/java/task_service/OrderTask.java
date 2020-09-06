package task_service;

import client_worker_service.Worker;
import department_service.Department;
import org.javatuples.Quartet;
import org.javatuples.Triplet;
import storage_service.RawMaterial;

import java.util.LinkedList;

public class OrderTask{
    LinkedList<Worker>          executors = new LinkedList<Worker>();
    LinkedList<CommonTaskIO>    tasks;
    Department                  responsibleDepartment;

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

    public Quartet<Double, Double, LinkedList<RawMaterial>, LinkedList<RawMaterial>>
    finalizeOrder(LinkedList<CommonTaskIO> sideTask){
        totalPrice = 0.0;
        totalWage = 0.0;
        LinkedList<RawMaterial> resProducts = new LinkedList<RawMaterial>();
        LinkedList<RawMaterial> resMaterials = new LinkedList<RawMaterial>();

        for (CommonTaskIO task: tasks){
            Quartet<Double, Double, RawMaterial, RawMaterial> taskRes = task.finalizeTask();
            totalPrice += taskRes.getValue0();
            totalWage += taskRes.getValue1();
            resProducts.add(taskRes.getValue2());
            resMaterials.add(taskRes.getValue3());
        }

        for (CommonTaskIO task: sideTask){
            Quartet<Double, Double, RawMaterial, RawMaterial> taskRes = task.finalizeTask();
            totalWage += taskRes.getValue1();
            resProducts.add(taskRes.getValue2());
            resMaterials.add(taskRes.getValue3());
        }

        return  new Quartet<Double, Double, LinkedList<RawMaterial>, LinkedList<RawMaterial>>
                        (totalPrice,
                        (Double)(totalWage / executors.size()),
                        resProducts,
                        resMaterials);
    }
}
