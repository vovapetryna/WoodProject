package client_worker_service;

import department_service.Department;

public class Worker {
    private String              name;
    private String              surname;

    private Specialization      specialization;
    private int                 rank;
    private double              targetSalary;
    private double              currentBalance;
    private Department          department;

    Worker(String name,
           String surname,
           Specialization specialization,
           int rank,
           double targetSalary,
           Department department) {
        this.name = name;
        this.surname = surname;
        this.specialization = specialization;
        this.rank = rank;
        this.targetSalary = targetSalary;
        this.department = department;
        this.currentBalance = 0;
    }

    public void addBalance(double wage){
        this.currentBalance += wage;
    }

    public void decreaseBalance(double wage){
        this.currentBalance -= wage;
    }

    private static void validateRank(int rank){
        if (rank <= 0)
            throw new IllegalArgumentException("Invalid rank (must be positive)");
    }

    private static void validateSalary(double targetSalary){
        if (targetSalary <= 0)
            throw new IllegalArgumentException("Invalid targetSalary (must be positive)");
    }

    public void setRank(int rank){
        validateRank(rank);
        this.rank = rank;
    }

    public void setTargetSalary(double targetSalary){
        validateSalary(targetSalary);
        this.targetSalary = targetSalary;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", specialization=" + specialization +
                ", rank=" + rank +
                '}';
    }
}
