package client_worker_service;

final public class Person {
    final public String name;
    final public String surname;

    public Person(String name,
                  String surname){
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}
