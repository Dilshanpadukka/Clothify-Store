package icet.edu.drm.repository;

import icet.edu.drm.repository.custom.impl.EmployeeDaoImpl;
import icet.edu.drm.util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;

    private DaoFactory(){}

    public static DaoFactory getInstance(){
        return instance!=null?instance:(instance=new DaoFactory());
    }

    public <T extends SuperDao>T getDao(DaoType type){
        switch (type){
            case EMPLOYEE:return (T)new EmployeeDaoImpl();
//            case CUSTOMER:return (T)new CustomerDaoImpl();
//            case SUPPLIER:return (T)new SupplierDaoImpl();
//            case PRODUCT:return (T)new ProductDaoImpl();
//            case ORDER:return (T)new OrderDaoImpl();

        }
        return null;
    }
}
