package department_service;

public final class Department{
    final private String name;

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
