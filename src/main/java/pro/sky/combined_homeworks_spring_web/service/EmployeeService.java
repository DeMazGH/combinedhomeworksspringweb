package pro.sky.combined_homeworks_spring_web.service;

import pro.sky.combined_homeworks_spring_web.model.Employee;

import java.util.List;

public interface EmployeeService {

    Employee addNewEmployee(String firstName, String lastName);

    Employee deleteEmployee(String firstName, String lastName);

    Employee findEmployee(String firstName, String lastName);

    List<Employee> getListOfEmployees();
}
