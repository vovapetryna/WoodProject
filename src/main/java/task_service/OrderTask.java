package task_service;

import client_worker_service.Worker;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class OrderTask implements TaskInterface{
    Worker                  responsible;
    LinkedList<Worker>      executors = new LinkedList<Worker>();

    CommonTaskIO            taskTemplate;

    LocalDateTime           deadline;

    public OrderTask(CommonTaskIO taskTemplate,
                     Worker responsible,
                     LocalDateTime deadline){
        this.taskTemplate = taskTemplate;
        this.responsible = responsible;
        this.deadline = deadline;
    }

    public void setTaskParameters(double materialVolume, double productVolume){
        taskTemplate.setParameters(materialVolume,
                                    productVolume);
    }

    @Override
    public void changeResponsible(Worker responsible) {
        this.responsible = responsible;
    }

    @Override
    public void addWorker(Worker worker) {
        executors.add(worker);
    }

    @Override
    public void dellWorker(Worker worker) {
        executors.remove(worker);
    }

    @Override
    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }
}
