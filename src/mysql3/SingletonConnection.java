package mysql3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

class SingletonConnection {

    private static SingletonConnection instance = null;
    String ConnectionURL = "jdbc:mysql://127.0.0.1/test?user=root&password=haslo";
    Connection connection;
    Statement statement;

    private SingletonConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(ConnectionURL);
            statement = connection.createStatement();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }

    public static SingletonConnection getInstance() {
        if (instance == null) {
            instance = new SingletonConnection();
        }
        return instance;
    }

    public void fillTable() {
        Random r = new Random();
        try {
            for (int i = 1000; i < 2000000; i++) {
                statement.executeUpdate("insert into speedtest values(" + i + ", " + r.nextLong() + ");");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    

}
