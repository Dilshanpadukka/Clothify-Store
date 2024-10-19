package service.custom;

import javafx.collections.ObservableList;
import model.Order;
import service.SuperService;

public interface OrderService extends SuperService {
    boolean placeOrder(Order order);
    ObservableList<Order> getAll();
}
