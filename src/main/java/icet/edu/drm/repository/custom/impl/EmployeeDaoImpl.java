package icet.edu.drm.repository.custom.impl;

import icet.edu.drm.entity.EmployeeEntity;
import icet.edu.drm.repository.custom.EmployeeDao;
import icet.edu.drm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity userEntity) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        session.persist(userEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }
@Override
    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("SELECT id FROM employee ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        Session session = HibernateUtil.getSession();
        //Transaction transaction = session.getTransaction();
        List<EmployeeEntity> employeeList = session.createQuery("FROM employee").list();
        return employeeList;
    }

    @Override
    public boolean update(EmployeeEntity employeeEntity) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.merge(employeeEntity.getId(),employeeEntity);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.remove(session.get(EmployeeEntity.class,id));
        session.getTransaction().commit();
        return true;
    }
    @Override
    public EmployeeEntity search(String name) {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();

        Query query = session.createQuery("FROM employee WHERE name=:name");
        query.setParameter("name", name);

        EmployeeEntity employeeEntity = (EmployeeEntity) query.uniqueResult();
        session.close();
        return employeeEntity;
    }
}