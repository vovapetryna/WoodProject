package department_service;

import java.util.LinkedList;

public class Departments {
    private LinkedList<Department>          departments = new LinkedList<Department>();

    public Department addDepartment(String name){
        for (Department department: departments){
            if (department.sameName(name))
                return department;
        }
        Department tempDepartment = new Department(name);
        departments.add(tempDepartment);
        return tempDepartment;
    }

    public Department getDepartmentByName(String name){
        for (Department department: departments){
            if (department.sameName(name))
                return department;
        }
        throw new ArrayIndexOutOfBoundsException("Dapartment with name: " + name + " does not exist");
    }
}
