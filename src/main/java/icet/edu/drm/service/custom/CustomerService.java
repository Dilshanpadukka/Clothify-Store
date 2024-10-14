package icet.edu.drm.service.custom;

import icet.edu.drm.model.Customer;
import javafx.collections.ObservableList;

import java.util.List;

public interface CustomerService {
    boolean addCustomer(Customer customer);

    Customer searchByName(String name);

    boolean deleteCustomer(String text);

    List getCustomer();

    boolean updateCustomer(Customer customer);

    String generateCustomerId();
}