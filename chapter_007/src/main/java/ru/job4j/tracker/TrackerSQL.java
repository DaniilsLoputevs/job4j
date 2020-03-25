package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Замена database_name в properties не работает, т.к.
 * Коннект идёт сразу к базе, это прописано в конфиге в url
 */
public class TrackerSQL implements Tracker, AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);
    private Connection connection;
    private ConfigLoader config;

    public TrackerSQL() {
        initConnection();
//        createBaseIfNotExits();
        createTableIfNotExits();
    }
    public TrackerSQL(Connection connection) {
        this.connection = connection;
        createTableIfNotExits();
    }

    private void initConnection() {
        this.config = new ConfigLoader("./src/main/resources/connection_config.properties");
        try {
            this.connection = DriverManager.getConnection(
                    config.value("url"),
                    config.value("username"),
                    config.value("password")
            );
            if (this.connection == null) {
                System.out.println("PROBLEM in TrackerSQL.initConnection(): this.connection == null");
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void createTableIfNotExits() {
        try {
            Statement st = this.connection.createStatement();
            st.execute("create table if not exists items ("
                    + "id         varchar(30) primary key,"
                    + "name       varchar(200));");
        } catch (SQLException r) {
            LOG.error("Table creating error", r);
        }
    }

    // NOT TESTED - это тут просто есть, базу раками делал.
    private void createBaseIfNotExits() {
        PreparedStatement st;
        try {
            st = this.connection.prepareStatement("create DATABASE (?)");
            st.setString(1, config.value("database_name"));
            st.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Item add(Item item) {
        PreparedStatement st = null;
        try {
            st = this.connection.prepareStatement("insert into items(id, name) values(?, ?);");
            st.setString(1, generateId());
            st.setString(2, item.getName());
            st.execute();
            item.setId(generateId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        PreparedStatement st;
        var result = false;
        try {
            st = this.connection.prepareStatement("update items set name=(?) where id=(?);");
            st.setString(1, item.getName());
            st.setString(2, id);
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        PreparedStatement st;
        boolean result = false;
        try {
            st = this.connection.prepareStatement("delete from items where id=(?);");
            st.setString(1, id);
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }

    @Override
    public List<Item> findAll() {
        PreparedStatement st;
        List<Item> result = new ArrayList<>();
        try {
            st = this.connection.prepareStatement("select * from items;");
            var rs = st.executeQuery();
            while (rs.next()) {
                result.add(new Item(rs.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        PreparedStatement st;
        List<Item> result = null;
        try {
            st = this.connection.prepareStatement("select * from items where name=(?);");
            st.setString(1, key);
            var rs = st.executeQuery();
            if (rs.next()) {
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new Item(rs.getString("id"), rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

//    @Override
//    public Item findById(String id) {
//        PreparedStatement st;
//        Item result = null;
//        try {
//            st = this.connection.prepareStatement("select * from items where id = ?");
//            st.setString(1, id);
//            var rs = st.executeQuery();
//            while (rs.next()) {
//
//            }
////            while (rs.next()) {
////                System.out.println(
////                        String.format("%s",
////                                rs.getString("name")
//////                            rs.getInt("car_body_id"),
//////                            rs.getInt("engine_id"),
//////                            rs.getInt("transmission_id")
////                        )
////                );
////            }
//
//            String tempID = null;
//            String tempName = null;
//
//////            rs.next();
////            tempID = rs.getString("id");
//////            rs.next();
////            tempName = rs.getString("name");
//
//            boolean test;
//            if (test = rs.next()) {
//                tempID = rs.getString("id");
//            }
//            if (rs.next()) {
//                tempName = rs.getString("name");
//            }
//
//            result = new Item(tempID, tempName);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
    @Override
    public Item findById(String id) {
        Item tempItem;
        try (PreparedStatement st = connection.prepareStatement("select * from items where id = ?")) {
            st.setString(1, id);
            try (ResultSet anwer = st.executeQuery()) {
                while (anwer.next()) {
                    tempItem = new Item(anwer.getString("name"));
                    tempItem.setId("5256083254250015637");
                    return tempItem;
                }
            }
        } catch (SQLException r) {
            LOG.error("FindByName item error", r);
        }
        return null;
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Генерирует уникальный ключ для заявки.
     * Т.к. у заявки нет уникального поля. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ. (далее присваивается заявке(Item item), как id).
     */
    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }


}