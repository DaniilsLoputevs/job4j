package ru.job4j.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class SQLStorage {
    private static final Logger LOG = LoggerFactory.getLogger(SQLStorage.class);

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/job4_chapter_007";
        String userName = "postgres";
        String password = "password";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, userName, password);
            doSQLLogic(conn);


        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    LOG.error(e.getMessage(), e);
                }
            }
        }

    }


    private static void doSQLLogic(Connection conn) throws SQLException {
    // Простой вариант работы со Statement
//        Statement st = conn.createStatement();
//        ResultSet rs = st.executeQuery("SELECT * FROM public.car");

    // Продвинутый вариант работы со Statement (prepareStatement)
//        PreparedStatement st = conn.prepareStatement("SELECT * FROM car where car.id in (?, ?)");
//        st.setInt(1, 2);
//        st.setInt(2, 4);
//        ResultSet rs = st.executeQuery();

    // sout(result)
//        while (rs.next()) {
//            System.out.println(
//                    String.format("%s %s %s %s",
//                            rs.getString("name"),
//                            rs.getInt("car_body_id"),
//                            rs.getInt("engine_id"),
//                            rs.getInt("transmission_id")
//                    )
//            );
//        }

//        rs.close();
//        st.close();


    // Вставка "записи" + ( ,Statement.RETURN_GENERATED_KEYS )
//        PreparedStatement st = conn.prepareStatement(
//                "insert into car(name, car_body_id, engine_id, transmission_id)" +
//                " values(?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
//        st.setString(1, "Гламургый Седан");
//        st.setInt(2, 3);
//        st.setInt(3, 3);
//        st.setInt(4, 2);
//        st.executeUpdate();
//
//        ResultSet generatedKeys = st.getGeneratedKeys();
//        if (generatedKeys.next()) {
//            System.out.println(generatedKeys.getInt(1));
//        }

    // Обновление "записи" + ( ,Statement.RETURN_GENERATED_KEYS )
//        PreparedStatement st = conn.prepareStatement(
//                "update car set transmission_id=? where id=?", Statement.RETURN_GENERATED_KEYS);
//        st.setInt(1, 4);
//        st.setInt(2, 6);
//        st.executeUpdate();

    // Удаление "записи"
//        PreparedStatement st = conn.prepareStatement(
//               "delete from car where id=?");
//        st.setInt(1, 7);
//        st.executeUpdate();


    // Закрытие Statement.
//        st.close();

    }
}

























