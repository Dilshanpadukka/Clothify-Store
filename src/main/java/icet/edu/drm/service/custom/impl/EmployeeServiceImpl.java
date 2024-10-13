package icet.edu.drm.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import icet.edu.drm.entity.EmployeeEntity;
import icet.edu.drm.model.Employee;
import icet.edu.drm.repository.DaoFactory;
import icet.edu.drm.repository.custom.impl.EmployeeDaoImpl;
import icet.edu.drm.service.custom.EmployeeService;
import icet.edu.drm.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EmployeeServiceImpl implements EmployeeService {
    
    EmployeeDaoImpl employeeDaoImpl = DaoFactory.getInstance().getDao(DaoType.EMPLOYEE);

    public boolean isValidEmail(String email) {
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
        return email.matches(regex);
    }

    public String passwordEncrypt(String password) {
        return new String(Base64.getEncoder().encode(password.getBytes(StandardCharsets.UTF_8)));
    }

    public boolean insertUser(Employee employee) {
        EmployeeEntity userEntity = new ObjectMapper().convertValue(employee, EmployeeEntity.class);
        return employeeDaoImpl.insert(userEntity);
    }

    public String generateEmployeeId() {
        String lastEmployeeId = employeeDaoImpl.getLatestId();
        if (lastEmployeeId==null){
            return "E0001";
        }
        int number = Integer.parseInt(lastEmployeeId.split("E")[1]);
        number++;
        return String.format("E%04d", number);
    }

    public ObservableList getAllUsers() {
        ObservableList<EmployeeEntity> list = employeeDaoImpl.searchAll();
        ObservableList<Employee> employeeList = FXCollections.observableArrayList();

        list.forEach(userEntity -> {
            employeeList.add(new ObjectMapper().convertValue(userEntity,Employee.class));
        });
        return employeeList;
    }
}
