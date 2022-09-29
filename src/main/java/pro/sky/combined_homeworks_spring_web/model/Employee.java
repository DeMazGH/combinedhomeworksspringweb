package pro.sky.combined_homeworks_spring_web.model;

import java.util.Objects;

public class Employee {
    private final String firstName;
    private final String lastName;
    private double salary;
    private int department;

    static final int numberOfDepartments = 5;

    public Employee(String firstName, String lastName, double salary, int department) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.department = department;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return firstName.equals(employee.firstName) && lastName.equals(employee.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Имя: " + firstName + ", Фамилия: " + lastName + ", Отдел: " + department + ", Зарплата: " + salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(int department) {
        this.department = department;
    }

    public int getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public static int getNumberOfDepartments() {
        return numberOfDepartments;
    }
}
