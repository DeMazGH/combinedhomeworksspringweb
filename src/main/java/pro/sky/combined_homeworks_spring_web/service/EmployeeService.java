package pro.sky.combined_homeworks_spring_web.service;

import pro.sky.combined_homeworks_spring_web.model.Employee;

import java.util.Map;

public interface EmployeeService {

    Employee addNewEmployee(String firstName, String lastName, double salary, int department);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    Map<String, Employee> getEmployees();

    String getEmployeesInDepartment(int departmentNumber);

    String getListOfEmployeesByDepartment();

    Employee findHighestPaidEmployeeInDepartment(int departmentId);
}
