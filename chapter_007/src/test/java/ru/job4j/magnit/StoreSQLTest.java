package ru.job4j.magnit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.db.ConnectionRollback;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertEquals;

public class StoreSQLTest {
    private StoreSQL storeSQl;
    private static final Logger LOG = LoggerFactory.getLogger(StoreSQLTest.class);

    @Before
    public void setUp() {
        try {
            var config = new Config();
            config.init();

            var dbPath = tempFolder.newFile("magnitShopTask.sqlite3").getPath();

            var connect = DriverManager.getConnection(
                    config.get("testUrl") + '/' + dbPath);

            // just make a comment line below if you don't need a rollback connection
            connect = ConnectionRollback.create(connect);

            storeSQl = new StoreSQL(connect);

        } catch (SQLException | IOException e) {
            LOG.error(e.getMessage(), e);
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
}