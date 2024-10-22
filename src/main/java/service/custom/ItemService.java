package service.custom;

import javafx.collections.ObservableList;
import model.Item;
import model.OrderDetails;
import service.SuperService;

import java.util.List;

public interface ItemService extends SuperService {
    boolean addItem(Item item);
    boolean updateItem(Item item);
    Item searchItem(String  itemCode);
    boolean deleteItem(String itemCode);
    ObservableList<Item> getAll();
    ObservableList<String> getItemIds();
    boolean updateStock(List<OrderDetails> items);
    String generateItemId();
}
