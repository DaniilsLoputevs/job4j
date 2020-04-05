package ru.job4j.magnit;

import java.sql.*;
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

    public void generate(int size) {
        createTableIfNotExists();

        try (PreparedStatement pst = connect.prepareStatement("insert into entry values (?)")) {
            for (int index = 1; index <= size; ) {
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

    public void createNewDatabase(String fileName) {
        String url = config.get("url") + "/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
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

    public void cleanBase() {
        try (PreparedStatement pst = connect.prepareStatement("drop table entry")) {
            pst.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}