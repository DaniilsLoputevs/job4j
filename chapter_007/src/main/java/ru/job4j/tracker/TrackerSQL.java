package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Класс менеджер заявок.
 *
 * @author Daniils Loputevs
 * @version 1.0
 * @since 25.03.20
 * Created 25.03.20
 */
public class TrackerSQL implements Tracker, AutoCloseable {
    private static final Logger LOG = LoggerFactory.getLogger(TrackerSQL.class);
    private Connection connection;

    public TrackerSQL() {
        initConnection();
        createTableIfNotExits();
    }

    private void initConnection() {
        ConfigLoader config = new ConfigLoader("./src/main/resources/connection_config.properties");
        try {
            this.connection = DriverManager.getConnection(
                    config.value("url"),
                    config.value("username"),
                    config.value("password")
            );
            if (this.connection == null) {
                System.out.println("Exception in TrackerSQL.initConnection(): this.connection == null");
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
            LOG.error("Exception in - TrackerSQL.createTableIfNotExits()", r);
        }
    }

    @Override
    public Item add(Item item) {
        try (var st = this.connection.prepareStatement("insert into items(id, name) values(?, ?);")) {
            item.setId(generateId());
            st.setString(1, item.getId());
            st.setString(2, item.getName());
            st.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Exception in - TrackerSQL.add()", e);
        }
        return item;
    }

    @Override
    public boolean replace(String id, Item item) {
        var result = false;
        try (var st = this.connection.prepareStatement("update items set name=(?) where id=(?);")) {
            st.setString(1, item.getName());
            st.setString(2, id);
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Exception in - TrackerSQL.replace()", e);
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        try (var st = this.connection.prepareStatement("delete from items where id=?")) {
            st.setString(1, id);
            st.executeUpdate();
            result = true;
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Exception in - TrackerSQL.delete()", e);
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> result = new ArrayList<>();
        try (var st = this.connection.prepareStatement("select * from items;")) {
            try (var rs = st.executeQuery()) {
                while (rs.next()) {
                    result.add(new Item(rs.getString("id"), rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Exception in - TrackerSQL.findAll()", e);
        }
        return result;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> result = null;
        try (var st = this.connection.prepareStatement("select * from items where name=?")) {
            st.setString(1, key);
            try (var rs = st.executeQuery()) {
                result = new ArrayList<>();
                while (rs.next()) {
                    result.add(new Item(rs.getString("id"), rs.getString("name")));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Exception in - TrackerSQL.findByName()", e);
        }
        return result;
    }

    @Override
    public Item findById(String id) {
        Item result = null;
        try (var st = this.connection.prepareStatement("select * from items where id=?")) {
            st.setString(1, id);
            try (var rs = st.executeQuery()) {
                if (rs.next()) {
                    result = new Item(rs.getString("id"), rs.getString("name"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            LOG.error("Exception in - TrackerSQL.findById()", e);
        }
        return result;
    }

    @Override
    public boolean containsId(String id) {
        return findById(id).getId() != null;
    }

    @Override
    public boolean containsName(String name) {
        return !findByName(name).isEmpty();
    }

    @Override
    public void close() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LOG.error(e.getMessage(), e);
            }
        }
    }

    private String generateId() {
        Random rm = new Random();
        return String.valueOf(rm.nextLong() + System.currentTimeMillis());
    }
}