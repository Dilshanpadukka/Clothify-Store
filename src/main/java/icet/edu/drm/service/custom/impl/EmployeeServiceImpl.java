package icet.edu.drm.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import icet.edu.drm.entity.EmployeeEntity;
import icet.edu.drm.model.Employee;
import icet.edu.drm.repository.DaoFactory;
import icet.edu.drm.repository.custom.EmployeeDao;
import icet.edu.drm.service.custom.EmployeeService;
import icet.edu.drm.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    @Override
    public String passwordEncrypt(String password) {
        return new String(Base64.getEncoder().encode(password.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public boolean addEmployee(Employee employee) {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        EmployeeEntity userEntity = new ObjectMapper().convertValue(employee, EmployeeEntity.class);
        return employeeDao.save(userEntity);
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

    @Override
    public List getEmployee() {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        List<EmployeeEntity> list = employeeDao.getAll();
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();

        list.forEach(userEntity -> {
            employeeList.add(new ObjectMapper().convertValue(userEntity, Employee.class));
        });
        return employeeList;
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        EmployeeEntity employeeEntity = new ObjectMapper().convertValue(employee, EmployeeEntity.class);

        return employeeDao.update(employeeEntity);
    }

    @Override
    public boolean deleteEmployee(String text) {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        return employeeDao.delete(text);
    }

    @Override
    public Employee searchByName(String name) {
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        EmployeeEntity employeeEntity = employeeDao.search(name);

        return new ObjectMapper().convertValue(employeeEntity, Employee.class);
    }
}