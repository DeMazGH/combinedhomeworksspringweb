package pro.sky.combined_homeworks_spring_web.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.combined_homeworks_spring_web.Employee;
import pro.sky.combined_homeworks_spring_web.Exeption.EmployeeAlreadyAddedException;
import pro.sky.combined_homeworks_spring_web.Exeption.EmployeeNotFoundException;
import pro.sky.combined_homeworks_spring_web.Service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Object addNewEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        try {
            employeeService.addNewEmployee(newEmployee);
        } catch (EmployeeAlreadyAddedException e) {
            return e;
        }
        return newEmployee;
    }

    @GetMapping("/remove")
    public Object deleteEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        try {
            employeeService.deleteEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return e;
        }
        return newEmployee;
    }

    @GetMapping("/find")
    public Object findEmployee(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
        Employee newEmployee = new Employee(firstName, lastName);
        try {
            employeeService.findEmployee(firstName, lastName);
        } catch (EmployeeNotFoundException e) {
            return e;
        }
        return newEmployee;
    }

    @GetMapping("/list")
    public Object findEmployee() {
        return employeeService.getListOfEmployees();
    }

}
