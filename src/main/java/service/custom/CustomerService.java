package service.custom;

import model.Customer;
import javafx.collections.ObservableList;
import service.SuperService;

public interface CustomerService extends SuperService {
    boolean addCustomer(Customer customer);
    boolean updateCustomer(Customer customer);
    Customer searchCustomer(String  id);
    boolean deleteCustomer(String id);
    ObservableList<Customer> getAll();
    ObservableList<String> getCustomerIds();
    String generateCustomerId();
}
