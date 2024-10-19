package service.custom.impl;

import javafx.collections.ObservableList;
import model.Order;
import service.custom.OrderService;

public class OrderServiceImpl implements OrderService {
    @Override
    public boolean placeOrder(Order order) {
        return false;
    }

    @Override
    public ObservableList<Order> getAll() {
        return null;
    }
}
