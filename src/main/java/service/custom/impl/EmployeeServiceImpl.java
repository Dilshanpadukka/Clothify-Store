package service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.EmployeeEntity;
import model.Employee;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.CustomerDao;
import repository.custom.EmployeeDao;
import service.custom.EmployeeService;
import util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

public class EmployeeServiceImpl implements EmployeeService {

    @Override
    public boolean addEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new ModelMapper().map(employee, EmployeeEntity.class);
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        return employeeDao.save(employeeEntity);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new ModelMapper().map(employee, EmployeeEntity.class);
        EmployeeDao employeeDao = DaoFactory.getInstance().getDaoType(DaoType.EMPLOYEE);
        return employeeDao.update(employeeEntity);
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