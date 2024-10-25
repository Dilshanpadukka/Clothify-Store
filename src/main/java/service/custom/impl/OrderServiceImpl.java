package service.custom.impl;

import entity.OrderDetailsEntity;
import entity.OrderEntity;
import entity.SupplierEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Order;
import model.Supplier;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.CustomerDao;
import repository.custom.OrderDao;
import repository.custom.SupplierDao;
import service.custom.OrderService;
import util.DaoType;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class OrderServiceImpl implements OrderService {
    private final ModelMapper modelMapper = new ModelMapper();

    public boolean placeOrder(Order order) {
        OrderEntity orderEntity = modelMapper.map(order, OrderEntity.class);

        Set<OrderDetailsEntity> orderDetailsEntities = order.getOrderDetailsList().stream()
                .map(detailsDTO -> modelMapper.map(detailsDTO, OrderDetailsEntity.class))
                .collect(Collectors.toSet());

        orderEntity.setOrderDetails(orderDetailsEntities);

        OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);
        orderDao.save(orderEntity);

        return true;
    }

    @Override
    public ObservableList<Order> getAll() {
        OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);
        List<OrderEntity> all = orderDao.getAll();
        ModelMapper modelMapper = new ModelMapper();
        ObservableList<Order> orders = FXCollections.observableArrayList();
        for (OrderEntity entity : all) {
            Order order = modelMapper.map(entity, Order.class);
            orders.add(order);
        }
        return orders;
    }
    @Override
    public String generateOrderId() {
        OrderDao orderDao = DaoFactory.getInstance().getDaoType(DaoType.ORDER);
        String lastOrderId = orderDao.getLatestId();
        if (lastOrderId == null) {
            return "OR#0001";
        }
        int number = Integer.parseInt(lastOrderId.split("OR#")[1]);
        number++;
        return String.format("OR#%04d", number);
    }
}
