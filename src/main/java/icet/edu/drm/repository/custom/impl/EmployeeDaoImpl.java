package icet.edu.drm.repository.custom.impl;

import icet.edu.drm.repository.custom.EmployeeDao;
import icet.edu.drm.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

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
        Query query = session.createQuery("SELECT id FROM user ORDER BY id DESC LIMIT 1");
        String id = (String) query.uniqueResult();
        session.close();
        return id;
    }
}
