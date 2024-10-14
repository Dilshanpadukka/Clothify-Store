package icet.edu.drm.repository.custom.impl;

import icet.edu.drm.entity.CustomerEntity;
import icet.edu.drm.repository.custom.CustomerDao;
import icet.edu.drm.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public boolean save(CustomerEntity customerEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(customerEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    @Override
    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("SELECT id FROM customer ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public List<CustomerEntity> getAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        List<CustomerEntity> customerList = session.createQuery("FROM customer").list();
        ObservableList<CustomerEntity> list = FXCollections.observableArrayList();
        session.close();
        customerList.forEach(customerEntity -> {
            list.add(customerEntity);
        });
        return list;
    }

    @Override
    public boolean update(CustomerEntity customerEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE customer SET title =:title,name =:name,contact =:contact,address =:address,city =:city,province =:province,postalCode =:postalCode,email =:email WHERE id =:id");
        query.setParameter("id", customerEntity.getId());
        query.setParameter("title", customerEntity.getTitle());
        query.setParameter("name", customerEntity.getName());
        query.setParameter("contact", customerEntity.getContact());
        query.setParameter("address", customerEntity.getAddress());
        query.setParameter("city", customerEntity.getCity());
        query.setParameter("province", customerEntity.getProvince());
        query.setParameter("postalCode", customerEntity.getPostalCode());
        query.setParameter("email", customerEntity.getEmail());

        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i > 0;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM customer WHERE id=:id");
        query.setParameter("id", id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i > 0;
    }
    @Override
    public CustomerEntity search(String name) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM customer WHERE name=:name");
        query.setParameter("name", name);

        CustomerEntity customerEntity = (CustomerEntity) query.uniqueResult();
        session.close();

        return customerEntity;
    }
}