package icet.edu.drm.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import icet.edu.drm.entity.CustomerEntity;
import icet.edu.drm.model.Customer;
import icet.edu.drm.repository.DaoFactory;
import icet.edu.drm.repository.custom.CustomerDao;
import icet.edu.drm.service.custom.CustomerService;
import icet.edu.drm.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CustomerServiceImpl implements CustomerService {
    @Override
    public boolean addCustomer(Customer customer) {
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        CustomerEntity customerEntity = new ObjectMapper().convertValue(customer, CustomerEntity.class);
        return customerDao.save(customerEntity);
    }
    @Override
    public String generateCustomerId() {
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        String lastCustomerId = customerDao.getLatestId();
        if (lastCustomerId == null) {
            return "C0001";
        }
        int number = Integer.parseInt(lastCustomerId.split("C")[1]);
        number++;
        return String.format("C%04d", number);
    }
    @Override
    public ObservableList getCustomer() {
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        ObservableList<CustomerEntity> list = customerDao.getAll();
        ObservableList<Customer> customerList = FXCollections.observableArrayList();

        list.forEach(customerEntity -> {
            customerList.add(new ObjectMapper().convertValue(customerEntity, Customer.class));
        });
        return customerList;
    }
    @Override
    public boolean updateCustomer(Customer customer) {
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        CustomerEntity customerEntity = new ObjectMapper().convertValue(customer, CustomerEntity.class);

        return customerDao.update(customerEntity);
    }
    @Override
    public boolean deleteCustomer(String text) {
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        return customerDao.delete(text);
    }
    @Override
    public Customer searchByName(String name) {
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        CustomerEntity customerEntity = customerDao.search(name);

        return new ObjectMapper().convertValue(customerEntity, Customer.class);
    }
}