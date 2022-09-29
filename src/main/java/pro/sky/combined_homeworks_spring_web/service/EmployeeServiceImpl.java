package pro.sky.combined_homeworks_spring_web.service;

import org.springframework.stereotype.Service;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeAlreadyAddedException;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeNotFoundException;
import pro.sky.combined_homeworks_spring_web.model.Employee;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees;

    public EmployeeServiceImpl() {
        this.employees = new HashMap<>();
    }

    @Override
    public Employee addNewEmployee(String firstName, String lastName, double salary, int department) {
        checkAvailabilityDepartment(department);
        Employee employee = new Employee(firstName, lastName, salary, department);
        if (employees.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException("Такой сотрудник уже существует");
        }
        employees.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee deleteEmployee(String firstName, String lastName) {
        String desiredEmployee = firstName + " " + lastName;
        if (employees.containsKey(desiredEmployee)) {
            return employees.remove(desiredEmployee);
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }
    }

    @Override
    public Employee findEmployee(String firstName, String lastName) {
        String desiredEmployee = firstName + " " + lastName;
        if (employees.containsKey(desiredEmployee)) {
            return employees.get(desiredEmployee);
        } else {
            throw new EmployeeNotFoundException("Сотрудник не найден, проверьте правильность ввода данных!");
        }
    }

    @Override
    public Map<String, Employee> getEmployees() {
        return Collections.unmodifiableMap(employees);
    }

    @Override
    public String getEmployeesInDepartment(int departmentId) {
        List<Employee> employeesList = new ArrayList<>(employees.values());
        String result = "";
        result += "Сотрудники отдела " + departmentId + " :<br>";

        result += employeesList.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .map(e -> e.getFullName() + "<br>")
                .collect(Collectors.joining());
        result += "<br>";

//        for (int i = 0; i < employeesList.size(); i++) {
//            if (employeesList.get(i).getDepartment() == departmentId) {
//                result += employeesList.get(i).getFullName() + " <br>";;
//            }
//        }
        return result;
    }

    @Override
    public String getListOfEmployeesByDepartment() {
        StringBuilder result = new StringBuilder("Список имён сотрудников по отделам <br>");
        for (int i = 1; i <= Employee.getNumberOfDepartments(); i++) {
            result.append(getEmployeesInDepartment(i));
        }
        return result.toString();
    }

    @Override
    public Employee findHighestPaidEmployeeInDepartment(int departmentId) {
        checkAvailabilityDepartment(departmentId);
        checkAvailabilityEmployees(departmentId);

        List<Employee> employeesList = new ArrayList<>(employees.values());
        double highestSalaryInDepartment = employeesList.stream()
                .filter(e -> e.getDepartment() == departmentId)
                .map(Employee::getSalary)
                .max(Double::compareTo)
                .get();
        Employee highestPaidEmployeeInDepartment = employeesList.stream()
                .filter(e -> e.getSalary() == highestSalaryInDepartment).findFirst().get();

//        for (Employee currentEmployee : employeesList) {
//            if (currentEmployee.getSalary() > highestSalaryInDepartment
//                    && currentEmployee.getDepartment() == departmentId) {
//                highestSalaryInDepartment = currentEmployee.getSalary();
//                highestPaidEmployeeInDepartment = currentEmployee;
//            }
//        }
        return highestPaidEmployeeInDepartment;
    }

    @Override
    public Employee findLowestPaidEmployeeInDepartment(int departmentId) {
        checkAvailabilityDepartment(departmentId);
        checkAvailabilityEmployees(departmentId);

        List<Employee> employeesList = new ArrayList<>(employees.values());
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
        List<Employee> employeesList = new ArrayList<>(employees.values());
        if (employeesList.isEmpty()) {
            throw new EmployeeNotFoundException("Сотрудников не найдено");
        }
        if (employeesList.stream().allMatch(e -> e.getDepartment() != departmentId)) {
            throw new EmployeeNotFoundException("В данном отделе нет сотрудников");
        }
    }

    public void checkAvailabilityDepartment(int departmentId) {
        if (departmentId <= 0 || departmentId >= Employee.getNumberOfDepartments()) {
            throw new IllegalArgumentException("Неверный номер отдела, допустимое значение от 1 до "
                    + Employee.getNumberOfDepartments());
        }
    }
}
