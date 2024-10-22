package service.custom.impl;

import entity.OrderDetailsEntity;
import javafx.scene.control.Alert;
import org.hibernate.Session;
import org.hibernate.Transaction;
import repository.DaoFactory;
import repository.custom.OrderDetailsDao;
import repository.custom.impl.OrderDetailsDaoImpl;
import service.custom.OrderDetailService;
import util.DaoType;
import util.HibernateUtil;

public class OrderDetailsServiceImpl implements OrderDetailService {
    @Override
    public boolean addOrderDetail(OrderDetailsEntity orderDetailsEntity) {
        OrderDetailsDao orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAILS);
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            boolean result = orderDetailsDao.save(orderDetailsEntity);
            transaction.commit();
            return result;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            new Alert(Alert.AlertType.ERROR, "Error adding order detail: " + e.getMessage()).show();
        } finally {
            session.close();
        }
        return false;
    }

    @Override
    public double getTotalSalesPrice() {
        OrderDetailsDaoImpl orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAILS);
        return orderDetailsDao.getTotalSalesPrice();
    }

    @Override
    public Integer getTotalSoldItems() {
        OrderDetailsDaoImpl orderDetailsDao = DaoFactory.getInstance().getDaoType(DaoType.ORDERDETAILS);
        return orderDetailsDao.getTotalSoldItems();
    }
}
