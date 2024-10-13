package icet.edu.drm.service.custom;

import icet.edu.drm.model.Employee;
import javafx.collections.ObservableList;

public interface EmployeeService {
    boolean addEmployee(Employee employee);

    ObservableList getEmployee();

    Employee searchByName(String name);

    boolean deleteEmployee(String text);

    boolean updateEmployee(Employee employee);

    String generateEmployeeId();

    String passwordEncrypt(String password);

    boolean isValidEmail(String email);
}