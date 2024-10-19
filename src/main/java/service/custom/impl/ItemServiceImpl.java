package service.custom.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import entity.ItemEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Item;
import model.OrderDetails;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.EmployeeDao;
import repository.custom.ItemDao;
import repository.custom.impl.ItemDaoImpl;
import service.custom.ItemService;
import util.DaoType;

import java.util.List;

public class ItemServiceImpl implements ItemService {
    @Override
    public boolean addItem(Item item) {
        ItemEntity itemEntity = new ModelMapper().map(item, ItemEntity.class);
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        return itemDao.save(itemEntity);
    }

    @Override
    public boolean updateItem(Item item) {
        ItemEntity itemEntity = new ModelMapper().map(item, ItemEntity.class);
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        return itemDao.update(itemEntity);
    }

    @Override
    public Item searchItem(String itemCode) {
        ItemDao itemDao =  DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        ItemEntity itemEntity = itemDao.search(itemCode);
        Item items = new ObjectMapper().convertValue(itemEntity, Item.class);
        return items;
    }

    @Override
    public boolean deleteItem(String itemCode) {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        return itemDao.delete(itemCode);
    }

    @Override
    public ObservableList<Item> getAll() {
        ItemDao itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        List<ItemEntity> all = itemDao.getAll();
        ModelMapper modelMapper = new ModelMapper();
        ObservableList<Item> items = FXCollections.observableArrayList();
        for (ItemEntity entity : all) {
            Item item = modelMapper.map(entity, Item.class);
            items.add(item);
        }
        return items;
    }

    public ObservableList<String> getItemIds() {
        ItemDaoImpl itemDao = DaoFactory.getInstance().getDaoType(DaoType.ITEM);
        return itemDao.getItemIds();
    }

    @Override
    public boolean updateStock(List<OrderDetails> orderDetails) {
        for (OrderDetails orderDetails1 : orderDetails){
            Boolean isUpdateStock = updateStock((List<OrderDetails>) orderDetails1);
            if(!isUpdateStock){
                return false;
            }
        }
        return true;
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
}
