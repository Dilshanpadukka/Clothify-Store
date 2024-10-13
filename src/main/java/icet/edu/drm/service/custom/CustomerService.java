package icet.edu.drm.service.custom;

import icet.edu.drm.model.Customer;
import javafx.collections.ObservableList;

public interface CustomerService {
    boolean addCustomer(Customer customer);

    Customer searchByName(String name);

    boolean deleteCustomer(String text);

    ObservableList getCustomer();

    boolean updateCustomer(Customer customer);

    String generateCustomerId();
}