package repository.custom.impl;

import entity.EmployeeEntity;
import model.Employee;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.custom.EmployeeDao;
import util.HibernateUtil;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity employee) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        session.persist(employee);
        session.getTransaction().commit();
        session.close();
        return true;
    }
    @Override
    public boolean update(EmployeeEntity employee) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        EmployeeEntity employeeToUpdate = session.get(EmployeeEntity.class, employee.getEmployeeId());
        if (employeeToUpdate != null) {
            employeeToUpdate.setEmployeeId(employee.getEmployeeId());
            employeeToUpdate.setEmployeeName(employee.getEmployeeName());
            employeeToUpdate.setEmployeeNic(employee.getEmployeeNic());
            employeeToUpdate.setEmployeeAddress(employee.getEmployeeAddress());
            employeeToUpdate.setEmployeeEmailAddress(employee.getEmployeeEmailAddress());
            employeeToUpdate.setContactNumber(employee.getContactNumber());
            employeeToUpdate.setPassword(employee.getPassword());
            session.update(employeeToUpdate);
            session.getTransaction().commit();
            session.close();
            return true;
        } else {
            System.out.println("Employee not found!");
            session.getTransaction().rollback();
            session.close();
            return false;
        }
    }

    @Override
    public EmployeeEntity search(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        EmployeeEntity employee = session.get(EmployeeEntity.class, id);

        session.getTransaction().commit();
        session.close();
        if (employee != null) {
            return employee;
        } else {
            System.out.println("Employee not found!");
            return null;
        }
    }

    @Override
    public boolean delete(String id) {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        EmployeeEntity employeeToDelete = session.get(EmployeeEntity.class, id);

        if (employeeToDelete != null) {
            session.delete(employeeToDelete);
            session.getTransaction().commit();
            session.close();
            return true;
        } else {
            System.out.println("Customer not found!");
            session.getTransaction().rollback();
            session.close();
            return false;
        }
    }

    @Override
    public List<EmployeeEntity> getAll() {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        List<EmployeeEntity> employeeList = session.createQuery("FROM EmployeeEntity", EmployeeEntity.class).list();

        session.getTransaction().commit();
        session.close();

        return employeeList;
    }

    @Override
    public String getLatestId() {
        Session session = HibernateUtil.getSession();
        session.getTransaction().begin();
        Query query = session.createQuery("SELECT id FROM EmployeeEntity ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }

    public Employee  getEmployeeByEmail(String employeeEmailAddress) {
        Transaction transaction = null;
        Employee employee = null;

        try (Session session = HibernateUtil.getSession()) {
            transaction = session.beginTransaction();

            jakarta.persistence.Query query = session.createQuery("FROM EmployeeEntity WHERE employeeEmailAddress = :employeeEmailAddress", EmployeeEntity.class);
            query.setParameter("employeeEmailAddress", employeeEmailAddress);

            EmployeeEntity employeeEntity = (EmployeeEntity) ((org.hibernate.query.Query<?>) query).uniqueResult();

            if (employeeEntity != null) {
                employee = convertToEmployee(employeeEntity);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }

        return employee;
    }

    private Employee convertToEmployee(EmployeeEntity employeeEntity){
        Employee employee = new Employee();
        employee.setEmployeeId(employeeEntity.getEmployeeId());
        employee.setEmployeeName(employeeEntity.getEmployeeName());
        employee.setEmployeeNic(employeeEntity.getEmployeeNic());
        employee.setContactNumber(employeeEntity.getContactNumber());
        employee.setEmployeeEmailAddress(employeeEntity.getEmployeeEmailAddress());
        employee.setPassword(employeeEntity.getPassword());
        return employee;
    }
}
