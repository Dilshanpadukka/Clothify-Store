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

    public ObservableList<EmployeeEntity> searchAll() {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        List<EmployeeEntity> employeeList = session.createQuery("FROM employee").list();
        ObservableList<EmployeeEntity> list= FXCollections.observableArrayList();
        session.close();
        employeeList.forEach(employeeEntity -> {
            list.add(employeeEntity);
        });
        return list;
    }
}
