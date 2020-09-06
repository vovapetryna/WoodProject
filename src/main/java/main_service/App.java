package main_service;


import client_worker_service.Worker;

import java.util.LinkedList;

public class App
{
    public static void hr(){
        System.out.println("-----------------------------------------------------------------------------------");
    }

    public static void main( String[] args ) {
        System.out.println(
                "История начинаться с необходимости автоматизировать и максимально расширить учет производства.\n" +
                "Окружение проблемы состоит из множества цехов, а именно цехов-пилорам.\n" +
                        "Система состоит из 3-ох основных сервисов: \n" +
                        "\t- сервис учета рабочих \\ заказчиков \\ поставщиков\n" +
                        "\t- сервис контроля склада продукции и материалов\n" +
                        "\t- сервис контроля задач и заказов\n\n" +
                        "Перейдем к истории)");
        hr();

        MainService mainService = new MainService();

        System.out.println("Анализируемая часть производства состоит из 5-ти пилорам.\n" +
                "Добавим каждую из них используя сервис учета цехов\n");
        mainService.addDepartment("Пилорама 1");
        mainService.addDepartment("Пилорама 2");
        mainService.addDepartment("Пилорама 3");
        mainService.addDepartment("Пилорама 4");
        mainService.addDepartment("Пилорама 5");
        hr();

        System.out.println("Любое предприятие имеет рабочих.\n" +
                "Добавим рабочих при помощи сервиса учета клиентов \\ рабочих \\ поставщиков\n");
        mainService.addWorker("Василий", "Иванов");
        mainService.addWorker("Валерий", "Несторов");
        mainService.addWorker("Виталий", "Носов");
        hr();


        System.out.println("Материалы поставляются ограниченым числом поставщиков.\n" +
                "Добавим поставщиков используя сервис учета клиентов \\ рабочих \\ поставщиков\n" +
                "Для примера будет достаточно одного :-)\n");
        mainService.addProvider("Василий", "Петров",
                "050-222-1234", "vas@les.ch",
                "Les");
        hr();

        System.out.println("Предприятие отработало определенный срок без системы учета\n" +
                "Синхронизируем остатки используя csv таблицу (на очищенных и упрощенных данных)\n");
        mainService.preloadStorageService("Les",
                "Пилорама 3");
        hr();

        System.out.println("Предприятие имеет набор типовых изделий как из непосредственно бревна\n" +
                "так и из уже переработанной продукции\n" +
                "Создадим шаблоны используя сервис планировщик заданий\n");

        mainService.addCommonTask("Бревно L4 26", 0.0,
                "Шпала", 0.0,
                150.0, 50.0);
        mainService.addCommonTask("Бревно L2 20", 0.0,
                "Доска L2 15", 0.0,
                300.0, 25.0);
        hr();

        System.out.println("Основное задание любого производства - выполнение заказов." +
                "Создадим заказ из типовых операций используя сервис планирования заданий\n");

        String[][] order = {{"Бревно L4 26", "Шпала"},
                            {"Бревно L2 20", "Доска L2 15"}};
        double[] order_materials = {3.75, 1.25};
        double[] order_products = {3.5, 1.1};

        LinkedList<Worker> executors = new LinkedList<Worker>();
        executors.add(mainService.getWorkerSurname("Иванов"));
        executors.add(mainService.getWorkerSurname("Носов"));

        mainService.addOrder(order,
                            order_materials,
                            order_products,
                            executors);
        hr();

        System.out.println("После выполнения задания, сервис планирования автоматически начисляет зарплату рабочим\n" +
                "добавляет товар на склад продукцииn\n" +
                "списывает использованные материалы");
        mainService.finalizeOrders();
        hr();

        System.out.println("Отобразим последние изменения на складе товаром \\ сырья:\n");
        mainService.printLastChanges();
        hr();

        System.out.println("Таким образом видим обновленные записи учетного журнала склада");
    }
}
