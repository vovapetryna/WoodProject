package client_worker_service;

import department_service.Department;

import java.util.LinkedList;

public final class ClientWorkerService {
    final private LinkedList<Worker>              workers = new LinkedList<>();
    final private LinkedList<ClientProvider>    customers = new LinkedList<>();
    final private LinkedList<ClientProvider>    providers = new LinkedList<>();

    //this variable \ method will be used in analytics service
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

    //this variable \ method will be used in analytics service
    public boolean deleteWorker(Worker worker){
        return workers.remove(worker);
    }

    //this variable \ method will be used in analytics service
    public void addCustomer(ClientProvider customer){
        customers.add(customer);
    }

    //this variable \ method will be used in analytics service
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

    //this variable \ method will be used in analytics service
    public ClientProvider addProvider(ClientProvider provider){
        providers.add(provider);
        return provider;
    }

    //this variable \ method will be used in analytics service
    public void addProvider(String name,
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
    }

    public ClientProvider getProviderCompany(String companyName){
        for (ClientProvider provider: providers){
            if (provider.sameCompany(companyName))
                return provider;
        }
        return null;
    }

    public Worker geWorkerSurname(String surname){
        for (Worker worker: workers){
            if (worker.sameSurname(surname))
                return worker;
        }
        return null;
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
