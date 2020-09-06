package department_service;

import storage_service.RawMaterial;

import java.util.LinkedList;

public class Department{
    private String      name;

    Department(String name){
        this.name = name;
    }

    public boolean sameName(String otherName){
        return name.equals(otherName);
    }


    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                '}';
    }
}
