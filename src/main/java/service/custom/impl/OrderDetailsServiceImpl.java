package service.custom.impl;

import entity.OrderDetailsEntity;
import service.custom.OrderDetailService;

public class OrderDetailsServiceImpl implements OrderDetailService {
    @Override
    public boolean addOrderDetail(OrderDetailsEntity orderDetailsEntity) {
        return false;
    }

    @Override
    public double getTotalSalesPrice() {
        return 0;
    }

    @Override
    public Integer getTotalSoldItems() {
        return null;
    }
}
