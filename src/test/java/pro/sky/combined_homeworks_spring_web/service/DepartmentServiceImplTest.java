package pro.sky.combined_homeworks_spring_web.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.combined_homeworks_spring_web.model.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static pro.sky.combined_homeworks_spring_web.constants.EmployeeAndDepartmentServiceTestConstant.*;
import static pro.sky.combined_homeworks_spring_web.constants.EmployeeAndDepartmentServiceTestConstant.DEPARTMENT_2;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    private DepartmentService out;
    private Map<String, Employee> employeeMapActual;

    @Mock
    private EmployeeService employeeService;

    @BeforeEach
    public void setUp() {
        out = new DepartmentServiceImpl(employeeService);

        employeeMapActual = new HashMap<>();
        Employee employee = new Employee(FIRST_NAME_IVAN, LAST_NAME_IVANOV, SALARY_10000d, DEPARTMENT_1);
        Employee employee2 = new Employee(FIRST_NAME_PETR, LAST_NAME_PETROV, SALARY_20000d, DEPARTMENT_2);
        employeeMapActual.put(FIRST_NAME_IVAN + " " + LAST_NAME_IVANOV, employee);
        employeeMapActual.put(FIRST_NAME_PETR + " " + LAST_NAME_PETROV, employee2);
    }

    @Test
    public void shouldReturnCorrectStringInMethodGetEmployeesInDepartment() {
        Mockito.when(employeeService.getEmployees()).thenReturn(employeeMapActual);
        String actual = out.getEmployeesInDepartment(DEPARTMENT_1);
        String expected = "Сотрудники отдела " + DEPARTMENT_1 + " :<br>"
                + FIRST_NAME_IVAN + " " + LAST_NAME_IVANOV + "<br>" + "<br>";

        assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnCorrectStringInMethodGetListOfEmployeesByDepartment() {
        Mockito.when(employeeService.getEmployees()).thenReturn(employeeMapActual);
        String actual = out.getListOfEmployeesByDepartment();
        String expected = "Список имён сотрудников по отделам <br>"
                + "Сотрудники отдела " + DEPARTMENT_1 + " :<br>"
                + FIRST_NAME_IVAN + " " + LAST_NAME_IVANOV + "<br>" + "<br>"
                + "Сотрудники отдела " + DEPARTMENT_2 + " :<br>"
                + FIRST_NAME_PETR + " " + LAST_NAME_PETROV + "<br>" + "<br>"
                + "Сотрудники отдела 3 :<br><br>"
                + "Сотрудники отдела 4 :<br><br>"
                + "Сотрудники отдела 5 :<br><br>";

        assertEquals(actual, expected);
    }

    @Test
    void findHighestPaidEmployeeInDepartment() {
    }

    @Test
    void findLowestPaidEmployeeInDepartment() {
    }

    @Test
    void checkAvailabilityEmployees() {
    }
}