package repository.custom.impl;

import entity.ItemEntity;
import entity.OrderEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.custom.OrderDao;
import util.HibernateUtil;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public boolean save(OrderEntity order) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(order);
        session.getTransaction().commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(OrderEntity orderEntity) {
        return false;
    }

    @Override
    public OrderEntity search(String id) {
        return null;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<OrderEntity> getAll() {
        ObservableList<OrderEntity> orderList = FXCollections.observableArrayList();

        try (Session session = HibernateUtil.getSession()) {
            orderList.addAll(session.createQuery("FROM OrderEntity", OrderEntity.class).list());
        }
        return orderList;
    }

    @Override
    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("SELECT orderId FROM OrderEntity ORDER BY orderId DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }
}
