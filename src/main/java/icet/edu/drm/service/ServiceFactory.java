package icet.edu.drm.service;

import icet.edu.drm.service.custom.impl.EmployeeServiceImpl;
import icet.edu.drm.util.ServiceType;

public class ServiceFactory {
    private static ServiceFactory instance;

    private ServiceFactory(){}

    public static ServiceFactory getInstance(){
        return instance!=null?instance:(instance=new ServiceFactory());
    }
    public <T extends SuperService>T getService(ServiceType type){
        switch (type){
            case EMPLOYEE:return (T)new EmployeeServiceImpl();
        }
        return null;
    }
}
