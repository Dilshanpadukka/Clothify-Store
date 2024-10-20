package icet.edu.drm.service.custom;

import icet.edu.drm.model.Supplier;

import java.util.List;

public interface SupplierService {
    boolean addSupplier(Supplier supplier);

    Supplier searchByName(String name);

    boolean deleteSupplier(String text);

    boolean updateSupplier(Supplier supplier);

    String generateSupplierId();
    List<Supplier> getSupplier();
}
