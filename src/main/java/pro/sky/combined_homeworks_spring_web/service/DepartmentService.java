package pro.sky.combined_homeworks_spring_web.service;

import pro.sky.combined_homeworks_spring_web.model.Employee;

public interface DepartmentService {
    String getEmployeesInDepartment(int departmentNumber);

    String getListOfEmployeesByDepartment();

    Employee findHighestPaidEmployeeInDepartment(int departmentId);

    Employee findLowestPaidEmployeeInDepartment(int departmentId);

    void checkAvailabilityEmployees(int departmentId);
}
