package service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.CustomerEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Customer;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.CustomerDao;
import repository.custom.impl.CustomerDaoImpl;
import service.custom.CustomerService;
import util.DaoType;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public boolean addCustomer(Customer customer) {
        CustomerEntity customerEntity = new ModelMapper().map(customer, CustomerEntity.class);
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        return customerDao.save(customerEntity);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        CustomerEntity customerEntity = new ModelMapper().map(customer, CustomerEntity.class);
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        return customerDao.update(customerEntity);
    }

    @Override
    public Customer searchCustomer(String id) {
        CustomerDao customerDao =  DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        CustomerEntity customerEntity = customerDao.search(id);
        Customer customers = new ObjectMapper().convertValue(customerEntity, Customer.class);
        System.out.println(customers);
        return  customers;
    }

    @Override
    public boolean deleteCustomer(String id) {
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        return customerDao.delete(id);
    }

    @Override
    public ObservableList<Customer> getAll() {
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        List<CustomerEntity> all = customerDao.getAll();
        ObservableList<Customer> customers = FXCollections.observableArrayList();
        for (CustomerEntity entity : all) {
            customers.add(new ModelMapper().map(entity, Customer.class));
        }
        return customers;
    }

    @Override
    public ObservableList<String> getCustomerIds() {
        CustomerDaoImpl customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        return customerDao.getCustomerIds();
    }
}
