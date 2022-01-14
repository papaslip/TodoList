package repository;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DBConnection {
    static String url = "jdbc:mysql://localhost:3306/taskmanager?useUnicode=true&serverTimezone=UTC";
    static String username = "root";
    static String password = "admin";
    static Connection badConnection = null;
    static Connection getConnection(){
        try{Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try {
                Connection connection = DriverManager.getConnection(url,username,password);
                return connection;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
                return badConnection;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return badConnection;
        } catch (InvocationTargetException e) {
            e.printStackTrace();
            return badConnection;
        } catch (InstantiationException e) {
            e.printStackTrace();
            return badConnection;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return badConnection;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
            return badConnection;
        }
    }
}
