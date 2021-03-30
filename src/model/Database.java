package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
    public Connection connection() {
        String bd = "jdbc:sqlite:mydatabase.db";
        String tb = "CREATE TABLE IF NOT EXISTS item (id integer, name string, "
                + "description string, quantity integer, price decimal)";

        Connection con = null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection(bd);
            Statement st = con.createStatement();
            st.execute(tb);
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
        }

        return con;
    }
}
