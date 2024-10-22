package service.custom;

import javafx.collections.ObservableList;
import model.Supplier;
import service.SuperService;

public interface SupplierService extends SuperService {
    boolean addSupplier(Supplier supplier);
    boolean updateSupplier(Supplier supplier);
    Supplier searchSupplier(String  id);
    boolean deleteSupplier(String id);
    ObservableList<Supplier> getAll();
    String generateSupplierId();
}
