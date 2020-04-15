package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.db.ConnectionRollback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class TrackerSQLTest {
    private Tracker tracker;
    private String cfgPath = "./src/main/resources/connection_config.properties";
    private Connection connection;

    @Before
    public void setUp() {
        try {
            var config = new ConfigLoader(cfgPath);
            this.connection = DriverManager.getConnection(
                    config.value("url"),
                    config.value("username"),
                    config.value("password")
            );
            connection = ConnectionRollback.create(connection);

            this.tracker = new TrackerSQL(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        this.connection.close();
    }
    //    public void cleanBaseTracker(Item... items) {
//        //clean database from test records
//        var cleanList = new LinkedList<>(List.of(items));
//        cleanList.forEach(item -> new TrackerSQL().delete(item.getId()));
//    }

    @Test
    public void add() {
        var clean = tracker.add(new Item("Запись от - add()"));
        assertNotNull(clean);
//        cleanBaseTracker(clean);
    }

    @Test
    public void replace() {
        var replaceItem = tracker.add(new Item("Запись от - replace()"));
        var result = tracker.replace(replaceItem.getId(), new Item("Запись от - replace() - замена"));

        assertTrue(result);
//        cleanBaseTracker(replaceItem);
    }

    @Test
    public void delete() {
        var deleteItem = tracker.add(new Item("Запись от - delete()"));
        tracker.delete(deleteItem.getId());
        assertEquals(0, tracker.findByName("Запись от - delete()").size());

    }

    @Test
    public void findAll() {
        var item1 = tracker.add(new Item("Запись от - findAll()"));
        var item2 = tracker.add(new Item("Запись от - findAll()"));
        var list = tracker.findAll();
        assertTrue(list.size() >= 2);
//        cleanBaseTracker(item1, item2);
    }

    @Test
    public void findByName() {
        var item1 = tracker.add(new Item("Запись от - findByName()"));
        var item2 = tracker.add(new Item("Запись от - findByName()"));
        var list = tracker.findByName("Запись от - findByName()");

        assertEquals("Запись от - findByName()", list.get(0).getName());
        assertTrue(list.size() >= 2);
//        cleanBaseTracker(item1, item2);
    }

    @Test
    public void findById() {
        var itemForFind = tracker.add(new Item("поиск запрос ИД"));
        var result = tracker.findById(itemForFind.getId());

        assertEquals("поиск запрос ИД", result.getName());
//        cleanBaseTracker(itemForFind);
    }
}