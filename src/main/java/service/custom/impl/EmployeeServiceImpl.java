package service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.EmployeeEntity;
import model.Employee;
import org.mindrot.jbcrypt.BCrypt;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import repository.custom.impl.EmployeeDaoImpl;
import service.custom.EmployeeService;
import util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.EmailUtil;
import util.OTPUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmployeeServiceImpl implements EmployeeService {
    private final Map<String, String> otpStorage = new HashMap<>();

    @Override
    public boolean addEmployee(String empId,String empName, String empNic, String empContact, String empAddress,String empEmail,String empPassword) {
        if (isValidPassword(empPassword) && isUniqueEmail(empEmail)) {
            String hashedPassword = hashPassword(empPassword);
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setEmployeeId(empId);
            employeeEntity.setEmployeeName(empName);
            employeeEntity.setEmployeeNic(empNic);
            employeeEntity.setContactNumber(empContact);
            employeeEntity.setEmployeeAddress(empAddress);
            employeeEntity.setEmployeeEmailAddress(empEmail);
            employeeEntity.setPassword(hashedPassword);
            EmployeeDaoImpl employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
            return employeeDao.save(employeeEntity);
        }
        return false;
    }
    @Override
    public Employee loginEmployee(String email, String password) {
        EmployeeDaoImpl employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        Employee employee = employeeDao.getEmployeeByEmail(email);
        System.out.println(employee);
        if (employee != null) {
            String storedPassword = employee.getPassword();

            if (storedPassword == null) {
                System.out.println("Password for employee is not set: " + email);
                return null;
            }
            boolean isPasswordValid = authenticate(password, storedPassword);
            if (isPasswordValid) {
                return employee;
            } else {
                System.out.println("Invalid password for employee: " + email);
            }
        } else {
            System.out.println("No user found with employee: " + email);
        }
        return null;
    }
    @Override
    public Employee findEmployeeByEmail(String email) {
        EmployeeDaoImpl employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        return employeeDao.getEmployeeByEmail(email);
    }
    @Override
    public boolean sendOTPEmail(String email, String otp) {
        return EmailUtil.sendOTPEmail(email, otp);
    }
    @Override
    public void storeOTP(String email, String otp) {
        System.out.println("Email OTP :"+otp);
        otpStorage.put(email, otp);
        System.out.println("Stored OTP: " + otp + " for email: " + email);
    }
    @Override
    public boolean validateOTP(String email, String otp) {
        String storedOtp = otpStorage.get(email);
        System.out.println("Validating OTP for email: " + email);
        System.out.println("Entered OTP: " + otp);
        System.out.println("Stored OTP: " + storedOtp);
        return storedOtp != null && storedOtp.equals(otp);
    }
    @Override
    public void resetPassword(String email, String newPassword) {
        EmployeeDaoImpl employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        Employee employee = employeeDao.getEmployeeByEmail(email);
        if (employee != null) {
            String hashedPassword = OTPUtil.hashPassword(newPassword);
            employee.setPassword(hashedPassword);
            EmployeeEntity employee1 = new ModelMapper().map(employee, EmployeeEntity.class);
            employeeDao.update(employee1);
        }
    }

    private boolean isValidPassword(String password) {
        return password.length() >= 8;
    }
    private boolean isUniqueEmail(String email) {
        EmployeeDaoImpl employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        return employeeDao.getEmployeeByEmail(email) == null;
    }
    @Override
    public boolean updateEmployee(String empId,String empName, String empNic, String empContact, String empAddress,String empEmail,String empPassword) {
        if (isValidPassword(empPassword)) {
            String hashedPassword = hashPassword(empPassword);
            EmployeeEntity employeeEntity = new EmployeeEntity();
            employeeEntity.setEmployeeId(empId);
            employeeEntity.setEmployeeName(empName);
            employeeEntity.setEmployeeNic(empNic);
            employeeEntity.setContactNumber(empContact);
            employeeEntity.setEmployeeAddress(empAddress);
            employeeEntity.setEmployeeEmailAddress(empEmail);
            employeeEntity.setPassword(hashedPassword);
            EmployeeDaoImpl employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
            return employeeDao.update(employeeEntity);
        }
        return false;
    }

    public String hashPassword(String plainPassword) {
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }
    public boolean checkPassword(String plainPassword, String hashedPassword) {
        System.out.println("Plain Password: " + plainPassword);
        System.out.println("Hashed Password: " + hashedPassword);
        if (plainPassword == null || hashedPassword == null) {
            throw new IllegalArgumentException("Passwords cannot be null");
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }
    private boolean authenticate(String password, String storedPassword) {
        if (storedPassword == null) {
            System.out.println("Stored password is null");
            return false;
        }
        return checkPassword(password, storedPassword);
    }
    @Override
    public Employee searchEmployee(String id) {
        EmployeeDao employeeDao =  DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        EmployeeEntity employeeEntity = employeeDao.search(id);
        Employee employees =  new ObjectMapper().convertValue(employeeEntity, Employee.class);
        return employees;
    }

    @Override
    public boolean deleteEmployee(String id) {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        return employeeDao.delete(id);
    }

    @Override
    public ObservableList<Employee> getAll() {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        List<EmployeeEntity> all = employeeDao.getAll();
        ModelMapper modelMapper = new ModelMapper();
        ObservableList<Employee> employees = FXCollections.observableArrayList();
        for (EmployeeEntity entity : all) {
            Employee employee = modelMapper.map(entity, Employee.class);
            employees.add(employee);
        }
        return employees;
    }

    @Override
    public String generateEmployeeId() {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        String lastEmployeeId = employeeDao.getLatestId();
        if (lastEmployeeId == null) {
            return "E0001";
        }
        int number = Integer.parseInt(lastEmployeeId.split("E")[1]);
        number++;
        return String.format("E%04d", number);
    }
}