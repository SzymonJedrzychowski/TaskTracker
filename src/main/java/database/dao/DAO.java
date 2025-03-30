package database.dao;

import java.util.ArrayList;

public interface DAO<V> {
    void dropTable();

    void createTable();

    void insert(V object);

    ArrayList<V> selectAll();

    V selectByID(Integer id);
}
