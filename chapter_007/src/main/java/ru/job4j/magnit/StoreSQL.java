package ru.job4j.magnit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StoreSQL implements AutoCloseable {
    private final Config config;
    private Connection connect;

    public StoreSQL(Config config) {
        this.config = config;
        if (config != null) {
            try {
                connect = DriverManager.getConnection(this.config.get("url"));
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public StoreSQL(Config config, String dbPath) {
        this.config = config;
        if (config != null) {
            try {
                connect = DriverManager.getConnection(this.config.get("testUrl") + '/' + dbPath);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void generate(int size) {
        createTableIfNotExists();

        try (PreparedStatement pst = connect.prepareStatement("insert into entry values (?)")) {
            for (int index = 1; index <= size;) {
                pst.setInt(1, index++);
                pst.addBatch();
            }
            pst.executeBatch();

        } catch (SQLException r) {
            r.printStackTrace();
        }

    }


    public List<Entry> load() {
        List<Entry> tempList = new ArrayList<>();

        try (PreparedStatement pr = connect.prepareStatement("select * from entry");
             var rs = pr.executeQuery()) {
            while (rs.next()) {
                tempList.add(new Entry(rs.getInt("field")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tempList;
    }

    private void createTableIfNotExists() {
        try (PreparedStatement pst = connect.prepareStatement(
                "create table if not exists entry (field integer)")) {
            pst.execute();

        } catch (SQLException r) {
            r.printStackTrace();
        }
    }

    public Connection getConnect() {
        return this.connect;
    }

    @Override
    public void close() throws Exception {
        if (connect != null) {
            connect.close();
        }
    }

}