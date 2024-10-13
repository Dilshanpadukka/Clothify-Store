package icet.edu.drm.repository;

import javafx.collections.ObservableList;

import java.util.List;

public interface CrudDao<T, S> extends SuperDao {

    ObservableList<T> getAll();

    boolean save(T t);

    boolean update(T t);

    boolean delete(S s);

    T search(S s);

    String getLatestId();
}