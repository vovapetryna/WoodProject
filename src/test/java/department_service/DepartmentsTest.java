package department_service;

import junit.framework.TestCase;
import org.junit.Test;

public class DepartmentsTest extends TestCase {
    Departments departments = new Departments();

    @Test
    public void testForAddGetOperation(){
        departments.addDepartment("TestName0");
        departments.addDepartment("TestName1");
        Department tempDepartment = departments.addDepartment("TestName2");

        assertEquals(tempDepartment, departments.getDepartmentByName("TestName2"));
    }
}