package icet.edu.drm.repository.custom.impl;

import icet.edu.drm.entity.EmployeeEntity;
import icet.edu.drm.entity.ItemEntity;
import icet.edu.drm.entity.SupplierEntity;
import icet.edu.drm.model.Supplier;
import icet.edu.drm.repository.custom.ItemDao;
import icet.edu.drm.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class ItemDaoImpl implements ItemDao {
    @Override
    public boolean save(ItemEntity itemEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(itemEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    @Override
    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("SELECT id FROM item ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public List<ItemEntity> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        List<ItemEntity> itemList = session.createQuery("FROM item").list();
        return itemList;
    }

    @Override
    public boolean update(ItemEntity itemEntity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(itemEntity.getId(),itemEntity);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(session.get(ItemEntity.class,id));
        session.getTransaction().commit();
        return true;
    }
    @Override
    public ItemEntity search(String name) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM item WHERE name=:name");
        query.setParameter("name", name);

        ItemEntity itemEntity = (ItemEntity) query.uniqueResult();
        session.close();
        return itemEntity;
    }

}
