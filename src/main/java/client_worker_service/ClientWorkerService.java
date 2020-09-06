package client_worker_service;

import department_service.Department;

import java.util.LinkedList;

public class ClientWorkerService {
    private LinkedList<Worker>              workers = new LinkedList<Worker>();
    private LinkedList<ClientProvider>    customers = new LinkedList<ClientProvider>();
    private LinkedList<ClientProvider>    providers = new LinkedList<ClientProvider>();

    public void addWorker(Worker worker){
        workers.add(worker);
    }

    public void addWorker(String name,
                          String surname,
                          Specialization specialization,
                          int rank,
                          double targetSalary,
                          Department department){

        workers.add(new Worker(name,
                surname,
                specialization,
                rank,
                targetSalary,
                department));
    }

    public boolean deleteWorker(Worker worker){
        return workers.remove(worker);
    }

    public void addCustomer(ClientProvider customer){
        customers.add(customer);
    }

    public void addCustomer(String name,
                            String surname,
                            String phoneNumber,
                            String email,
                            String companyName){
        customers.add(new ClientProvider(name,
                surname,
                phoneNumber,
                email,
                companyName));
    }

    public ClientProvider addProvider(ClientProvider provider){
        providers.add(provider);
        return provider;
    }

    public ClientProvider addProvider(String name,
                            String surname,
                            String phoneNumber,
                            String email,
                            String companyName){

        ClientProvider provider = new ClientProvider(name,
                surname,
                phoneNumber,
                email,
                companyName);

        providers.add(provider);
        return provider;
    }

    public ClientProvider getProviderCompany(String companyName){
        for (ClientProvider provider: providers){
            if (provider.sameCompany(companyName))
                return provider;
        }
        return (ClientProvider)null;
    }

    public Worker geWorkerSurname(String surname){
        for (Worker worker: workers){
            if (worker.sameSurname(surname))
                return worker;
        }
        return (Worker) null;
    }

    public void addWorkerBalance(Worker worker, double wage){
        for (Worker tempWorker: workers){
            if (tempWorker == worker) {
                tempWorker.addBalance(wage);
                return;
            }
        }
    }
}
