package icet.edu.drm.service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import icet.edu.drm.entity.ItemEntity;
import icet.edu.drm.model.Item;
import icet.edu.drm.repository.DaoFactory;
import icet.edu.drm.repository.custom.ItemDao;
import icet.edu.drm.service.custom.ItemService;
import icet.edu.drm.util.DaoType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    @Override
    public boolean addItem(Item item) {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        ItemEntity itemEntity = new ObjectMapper().convertValue(item, ItemEntity.class);
        return itemDao.save(itemEntity);
    }

    @Override
    public String generateItemId() {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        String lastItemId = itemDao.getLatestId();
        if (lastItemId == null) {
            return "P0001";
        }
        int number = Integer.parseInt(lastItemId.split("P")[1]);
        number++;
        return String.format("P%04d", number);
    }

    @Override
    public List getItem() {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        List<ItemEntity> list = itemDao.getAll();
        ObservableList<Item> itemList = FXCollections.observableArrayList();

        list.forEach(itemEntity -> {
            itemList.add(new ObjectMapper().convertValue(itemEntity, Item.class));
        });
        return itemList;
    }

    @Override
    public boolean updateItem(Item item) {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        ItemEntity itemEntity = new ObjectMapper().convertValue(item, ItemEntity.class);
        return itemDao.update(itemEntity);
    }

    @Override
    public boolean deleteItem(String text) {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        return itemDao.delete(text);
    }

    @Override
    public Item searchByName(String name) {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        ItemEntity itemEntity = itemDao.search(name);
        return new ObjectMapper().convertValue(itemEntity, Item.class);
    }

}
