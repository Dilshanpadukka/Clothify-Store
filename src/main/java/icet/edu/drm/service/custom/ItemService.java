package icet.edu.drm.service.custom;

import icet.edu.drm.model.Item;

import java.util.List;

public interface ItemService {
    boolean addItem(Item item);

    List getItem();

    Item searchByName(String name);

    boolean deleteItem(String text);

    boolean updateItem(Item item);

    String generateItemId();
}
