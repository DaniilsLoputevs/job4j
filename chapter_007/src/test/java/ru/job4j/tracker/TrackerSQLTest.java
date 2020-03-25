package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class TrackerSQLTest {
    Tracker tracker;

    @Before
    public void setUp() {
        this.tracker = new TrackerSQL();
    }

    @Test
    public void init() {


//        var config = new ConfigLoader("./src/main/resources/connection_config.properties");
//        Connection connection = null;
//
//
//        try {
////            DriverManager.getConnection(url, userName, password)
//            connection = DriverManager.getConnection(
//                    config.value("url"),
//                    config.value("username"),
//                    config.value("password")
//            );
//            if (connection == null) {
//                System.out.println("connection == null");
//            }
//        } catch (SQLException e) {
////            LOG.error(e.getMessage(), e);
//            System.out.println(e.getMessage() + "init problem");
//        }
//
//        TrackerSQL tracker = new TrackerSQL(connection);
    }

    @Test // TESTED
    public void add() {
        tracker.add(new Item("Тест запрос"));
    }

    @Test // TESTED
    public void replace() {
        var replaceItem = tracker.add(new Item("Запрос удалить"));
        tracker.replace(replaceItem.getId(), new Item("замена!"));
    }

    @Test // TESTED
    public void delete() {
        var deleteItem = tracker.add(new Item("Запрос удалить"));
        tracker.delete(deleteItem.getId());
    }

    @Test // TESTED
    public void findAll() {
        var list = tracker.findAll();
//        list.forEach(item -> System.out.println(item.getName()));

        assertTrue(list.size() >= 2);
    }

    @Test // TESTED
    public void findByName() {
        tracker.add(new Item("поиск запрос имя"));
        tracker.add(new Item("поиск запрос имя"));
        var list = tracker.findByName("поиск запрос");
//        list.forEach(item -> System.out.println(item.getName()));

        assertThat(list.get(0).getName(), is("поиск запрос"));
        assertTrue(list.size() >= 2);
    }

    /**
     * НЕ РАБОТАЕТ!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
     */
    @Test
    public void findById() {
//        var findItem = tracker.add(new Item("поиск запрос ИД"));
        var findItem = tracker.add(new Item("111", "поиск запрос ИД"));
        var result = tracker.findById(findItem.getId());
//        list.forEach(findItem -> System.out.println(findItem.getName()));

        assertThat(result.getName().equals("поиск запрос ИД"), is(true));
    }

    @Test
    public void close() {
    }
}