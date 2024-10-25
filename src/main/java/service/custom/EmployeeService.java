package service.custom;

import javafx.collections.ObservableList;
import model.Employee;
import service.SuperService;

public interface EmployeeService extends SuperService {
    boolean addEmployee(String empId,String empName, String empNic, String empContact, String empAddress,String empEmail,String empPassword);
    boolean updateEmployee(String empId,String empName, String empNic, String empContact, String empAddress,String empEmail,String empPassword);
    Employee searchEmployee(String  id);
    boolean deleteEmployee(String id);
    ObservableList<Employee> getAll();
    String generateEmployeeId();
    Employee loginEmployee(String email, String password);
    Employee findEmployeeByEmail(String email);
    boolean sendOTPEmail(String email, String otp);
    void storeOTP(String email, String otp);
    boolean validateOTP(String email, String otp);
    void resetPassword(String email, String newPassword);
}