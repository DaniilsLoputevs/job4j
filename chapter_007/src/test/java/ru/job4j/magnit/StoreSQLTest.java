package ru.job4j.magnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

// todo - перенести создание базы в TempFolder
// todo - разбиение на строки в конечном файле
public class StoreSQLTest {
    private StoreSQL storeSQl;

    @Before
    public void setUp() {
        var config = new Config();
        config.init();
        storeSQl = new StoreSQL(config);

        try {
            storeSQl.getConnect().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @After
    public void finish() {
        try {
            storeSQl.getConnect().rollback();
            storeSQl.getConnect().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void storeSQLTestRun() {
//        var store = new StoreSQL(config, "stub");
//        store.createNewDatabase("magnitShopTask");

        storeSQl.generate(10);

        var temp = storeSQl.load();
        assertEquals(10, temp.size());
    }

    @Ignore
    public void cleanBases() {

        storeSQl.cleanBase();
    }
}