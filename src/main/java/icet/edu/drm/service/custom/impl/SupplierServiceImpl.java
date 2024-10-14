package icet.edu.drm.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import icet.edu.drm.entity.SupplierEntity;
import icet.edu.drm.model.Supplier;
import icet.edu.drm.repository.DaoFactory;
import icet.edu.drm.repository.custom.SupplierDao;
import icet.edu.drm.service.custom.SupplierService;
import icet.edu.drm.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    @Override
    public boolean addSupplier(Supplier supplier) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        SupplierEntity supplierEntity = new ObjectMapper().convertValue(supplier, SupplierEntity.class);
        return supplierDao.save(supplierEntity);
    }

    @Override
    public String generateSupplierId() {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        String lastSupplierId = supplierDao.getLatestId();
        if (lastSupplierId == null) {
            return "S0001";
        }
        int number = Integer.parseInt(lastSupplierId.split("S")[1]);
        number++;
        return String.format("S%04d", number);
    }

    @Override
    public List getSupplier() {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        List<SupplierEntity> list = supplierDao.getAll();
        ObservableList<Supplier> supplierList = FXCollections.observableArrayList();

        list.forEach(supplierEntity -> {
            supplierList.add(new ObjectMapper().convertValue(supplierEntity, Supplier.class));
        });
        return supplierList;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        SupplierEntity supplierEntity = new ObjectMapper().convertValue(supplier, SupplierEntity.class);

        return supplierDao.update(supplierEntity);
    }

    @Override
    public boolean deleteSupplier(String text) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        return supplierDao.delete(text);
    }

    @Override
    public Supplier searchByName(String name) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        SupplierEntity supplierEntity = supplierDao.search(name);

        return new ObjectMapper().convertValue(supplierEntity, Supplier.class);
    }

}
