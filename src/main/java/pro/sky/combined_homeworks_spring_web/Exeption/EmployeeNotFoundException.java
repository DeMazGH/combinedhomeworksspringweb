package pro.sky.combined_homeworks_spring_web.Exeption;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
