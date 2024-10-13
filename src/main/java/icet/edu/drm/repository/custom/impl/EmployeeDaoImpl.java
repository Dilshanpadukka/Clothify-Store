package icet.edu.drm.repository.custom.impl;

import icet.edu.drm.entity.EmployeeEntity;
import icet.edu.drm.repository.custom.EmployeeDao;
import icet.edu.drm.util.HibernateUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean insert(EmployeeEntity userEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(userEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("SELECT id FROM employee ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public EmployeeEntity search(String s) {
        return null;
    }

    @Override
    public ObservableList<EmployeeEntity> searchAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        List<EmployeeEntity> employeeList = session.createQuery("FROM employee").list();
        ObservableList<EmployeeEntity> list = FXCollections.observableArrayList();
        session.close();
        employeeList.forEach(employeeEntity -> {
            list.add(employeeEntity);
        });
        return list;
    }

    @Override
    public boolean update(EmployeeEntity employeeEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("UPDATE employee SET name =:name,contact =:contact,nic =:nic,address =:address,email =:email,password =:password WHERE id =:id");
        query.setParameter("name", employeeEntity.getName());
        query.setParameter("contact", employeeEntity.getContact());
        query.setParameter("nic", employeeEntity.getNic());
        query.setParameter("address", employeeEntity.getAddress());
        query.setParameter("email", employeeEntity.getEmail());
        query.setParameter("password", employeeEntity.getPassword());
        query.setParameter("id", employeeEntity.getId());

        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();

        return i > 0;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("DELETE FROM employee WHERE id=:id");
        query.setParameter("id", id);
        int i = query.executeUpdate();
        session.getTransaction().commit();
        session.close();
        return i > 0;
    }
}
