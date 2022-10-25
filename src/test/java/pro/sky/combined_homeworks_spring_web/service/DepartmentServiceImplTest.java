package pro.sky.combined_homeworks_spring_web.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.combined_homeworks_spring_web.exeption.EmployeeNotFoundException;
import pro.sky.combined_homeworks_spring_web.model.Employee;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static pro.sky.combined_homeworks_spring_web.constants.EmployeeAndDepartmentServiceTestConstant.*;

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
        Employee employee3 = new Employee(FIRST_NAME_DENIS, LAST_NAME_DENISOV, SALARY_30000d, DEPARTMENT_1);
        employeeMapActual.put(FIRST_NAME_IVAN + " " + LAST_NAME_IVANOV, employee);
        employeeMapActual.put(FIRST_NAME_PETR + " " + LAST_NAME_PETROV, employee2);
        employeeMapActual.put(FIRST_NAME_DENIS + " " + LAST_NAME_DENISOV, employee3);
    }

    @Test
    public void shouldReturnCorrectStringInMethodGetEmployeesInDepartment() {
        Mockito.when(employeeService.getEmployees()).thenReturn(employeeMapActual);
        String actual = out.getEmployeesInDepartment(DEPARTMENT_1);
        String expected = "Сотрудники отдела " + DEPARTMENT_1 + " :<br>"
                + FIRST_NAME_IVAN + " " + LAST_NAME_IVANOV + "<br>"
                + FIRST_NAME_DENIS + " " + LAST_NAME_DENISOV + "<br>" + "<br>";

        assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnCorrectStringInMethodGetListOfEmployeesByDepartment() {
        Mockito.when(employeeService.getEmployees()).thenReturn(employeeMapActual);
        String actual = out.getListOfEmployeesByDepartment();
        String expected = "Список имён сотрудников по отделам <br>"
                + "Сотрудники отдела " + DEPARTMENT_1 + " :<br>"
                + FIRST_NAME_IVAN + " " + LAST_NAME_IVANOV + "<br>"
                + FIRST_NAME_DENIS + " " + LAST_NAME_DENISOV + "<br>" + "<br>"
                + "Сотрудники отдела " + DEPARTMENT_2 + " :<br>"
                + FIRST_NAME_PETR + " " + LAST_NAME_PETROV + "<br>" + "<br>"
                + "Сотрудники отдела 3 :<br><br>"
                + "Сотрудники отдела 4 :<br><br>"
                + "Сотрудники отдела 5 :<br><br>";

        assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnHighestPaidEmployeeInDepartment() {
        Mockito.when(employeeService.getEmployees()).thenReturn(employeeMapActual);
        Employee actual = out.findHighestPaidEmployeeInDepartment(DEPARTMENT_1);
        Employee expected = employeeMapActual.get(FIRST_NAME_DENIS + " " + LAST_NAME_DENISOV);

        assertEquals(actual, expected);
    }

    @Test
    public void shouldReturnLowestPaidEmployeeInDepartment() {
        Mockito.when(employeeService.getEmployees()).thenReturn(employeeMapActual);
        Employee actual = out.findLowestPaidEmployeeInDepartment(DEPARTMENT_1);
        Employee expected = employeeMapActual.get(FIRST_NAME_IVAN + " " + LAST_NAME_IVANOV);

        assertEquals(actual, expected);
    }

    @Test
    public void shouldDoNothingInMethodCheckAvailabilityEmployees() {
        Mockito.when(employeeService.getEmployees()).thenReturn(employeeMapActual);
        out.checkAvailabilityEmployees(DEPARTMENT_1);
        out.checkAvailabilityEmployees(DEPARTMENT_2);
    }

    @ParameterizedTest
    @MethodSource("checkAvailabilityEmployeesParamTest")
    public void shouldThrowEmployeeNotFoundExceptionInMethodCheckAvailabilityEmployees(int departmentNumber) {
        Mockito.when(employeeService.getEmployees()).thenReturn(employeeMapActual);
        assertThrows(EmployeeNotFoundException.class, () -> out.checkAvailabilityEmployees(departmentNumber));
    }

    public static Stream<Arguments> checkAvailabilityEmployeesParamTest() {
        return Stream.of(
                Arguments.of(DEPARTMENT_NEGATIVE_3),
                Arguments.of(DEPARTMENT_0),
                Arguments.of(DEPARTMENT_5),
                Arguments.of(DEPARTMENT_6)
        );
    }
}