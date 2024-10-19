package service.custom;

import entity.OrderDetailsEntity;
import service.SuperService;

public interface OrderDetailService extends SuperService {
    boolean addOrderDetail(OrderDetailsEntity orderDetailsEntity);
    double getTotalSalesPrice();
    Integer getTotalSoldItems();
}
