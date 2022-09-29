package pro.sky.combined_homeworks_spring_web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.combined_homeworks_spring_web.model.Employee;
import pro.sky.combined_homeworks_spring_web.service.EmployeeService;

import java.util.Map;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee addNewEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName,
            @RequestParam("salary") double salary,
            @RequestParam("department") int department) {
        return employeeService.addNewEmployee(firstName, lastName, salary, department);
    }

    @GetMapping("/remove")
    public Employee deleteEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        return employeeService.deleteEmployee(firstName, lastName);
    }

    @GetMapping("/find")
    public Employee findEmployee(
            @RequestParam("firstName") String firstName,
            @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, lastName);
    }

    @GetMapping("/list")
    public Map<String, Employee> findEmployee() {
        return employeeService.getEmployees();
    }

    @GetMapping("/departments/all")
    public String getEmployeesInDepartment(@RequestParam("departmentId")int departmentId) {
        return employeeService.getEmployeesInDepartment(departmentId);
    }

    @GetMapping("/departments/allList")
    public String getListOfEmployeesByDepartment() {
        return employeeService.getListOfEmployeesByDepartment();
    }

    @GetMapping("/departments/max-salary")
    public String findHighestPaidEmployeeInDepartment(int departmentId) {
        return "Самый высокоплачиваемый сотрудник в отделе " + departmentId + ": <br>"
                + employeeService.findHighestPaidEmployeeInDepartment(departmentId).toString();
    }
}
