package pro.sky.combined_homeworks_spring_web.service;

import org.springframework.stereotype.Service;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeNotFoundException;
import pro.sky.combined_homeworks_spring_web.model.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService{

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public String getEmployeesInDepartment(int departmentId) {
        employeeService.checkAvailabilityDepartment(departmentId);
        List<Employee> employeesList = new ArrayList<>(employeeService.getEmployees().values());
        String result = "";
        result += "Сотрудники отдела " + departmentId + " :<br>";
        result += employeesList.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .map(e -> e.getFullName() + "<br>")
                .collect(Collectors.joining());
        result += "<br>";
        return result;
    }

    @Override
    public String getListOfEmployeesByDepartment() {
        Employee employee = new Employee("test", "test", 1, 1);
        StringBuilder result = new StringBuilder("Список имён сотрудников по отделам <br>");
        for (int i = 1; i <= employee.getNumberOfDepartments(); i++) {
            result.append(getEmployeesInDepartment(i));
        }
        return result.toString();
    }

    @Override
    public Employee findHighestPaidEmployeeInDepartment(int departmentId) {
        employeeService.checkAvailabilityDepartment(departmentId);
        checkAvailabilityEmployees(departmentId);

        List<Employee> employeesList = new ArrayList<>(employeeService.getEmployees().values());
        double highestSalaryInDepartment = employeesList.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .map(Employee::getSalary)
                .max(Double::compareTo)
                .get();
        Employee highestPaidEmployeeInDepartment = employeesList.stream()
                .filter(e -> e.getSalary() == highestSalaryInDepartment).findFirst().get();
        return highestPaidEmployeeInDepartment;
    }

    @Override
    public Employee findLowestPaidEmployeeInDepartment(int departmentId) {
        employeeService.checkAvailabilityDepartment(departmentId);
        checkAvailabilityEmployees(departmentId);

        List<Employee> employeesList = new ArrayList<>(employeeService.getEmployees().values());
        double lowestSalaryInDepartment = employeesList.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .map(Employee::getSalary)
                .min(Double::compareTo)
                .get();
        Employee lowestPaidEmployeeInDepartment = employeesList.stream()
                .filter(e -> e.getSalary() == lowestSalaryInDepartment).findFirst().get();
        return lowestPaidEmployeeInDepartment;
    }

    public void checkAvailabilityEmployees(int departmentId) {
        List<Employee> employeesList = new ArrayList<>(employeeService.getEmployees().values());
        if (employeesList.isEmpty()) {
            throw new EmployeeNotFoundException("Сотрудников не найдено");
        }
        if (employeesList.stream().allMatch(e -> e.getDepartment() != departmentId)) {
            throw new EmployeeNotFoundException("В данном отделе нет сотрудников");
        }
    }
}
