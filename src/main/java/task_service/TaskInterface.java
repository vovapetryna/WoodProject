package task_service;

import client_worker_service.Worker;

import java.time.LocalDateTime;

public interface TaskInterface {
    public void changeResponsible(Worker responsible);
    public void addWorker(Worker worker);
    public void dellWorker(Worker worker);
    public void setDeadline(LocalDateTime deadline);
}
