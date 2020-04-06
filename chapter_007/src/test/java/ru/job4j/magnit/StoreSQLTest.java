package ru.job4j.magnit;

import org.junit.*;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

// todo - разбиение на строки в конечном файле
public class StoreSQLTest {
    private StoreSQL storeSQl;

    @Before
    public void setUp() {
        String tmpPathToBase = null;
        try {
            tmpPathToBase = tempFolder.newFile("magnitShopTask.sqlite3").getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        var config = new Config();
        config.init();
        storeSQl = new StoreSQL(config, tmpPathToBase);

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

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();


    @Test
    public void storeSQLTestRun() {
        storeSQl.generate(10);

        var temp = storeSQl.load();
        assertEquals(10, temp.size());
    }

    @Ignore
    public void cleanBases() {
        storeSQl.cleanBase();
    }
}