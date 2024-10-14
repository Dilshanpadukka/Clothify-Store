package icet.edu.drm.repository;

import icet.edu.drm.repository.custom.impl.CustomerDaoImpl;
import icet.edu.drm.repository.custom.impl.EmployeeDaoImpl;
import icet.edu.drm.repository.custom.impl.SupplierDaoImpl;
import icet.edu.drm.util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return instance==null?instance = new DaoFactory():instance;
    }
    public <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
            case CUSTOMER:return (T) new CustomerDaoImpl();
            case SUPPLIER:return (T) new SupplierDaoImpl();
            default:return null;
        }

    }
}