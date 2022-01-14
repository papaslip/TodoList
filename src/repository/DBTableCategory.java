package repository;
import java.sql.*;
public class DBTableCategory {

    public static void recordCategory(String name) throws SQLException, InstantiationException {
        Connection connection = DBConnection.getConnection();
        if(connection!=null) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into categories (Name) values(?)");
            preparedStatement.setString(1, name);
            preparedStatement.execute();
        }else System.out.println("Error, connection not established");
    }

    public static void showCategories(){
        Connection connection = DBConnection.getConnection();
        if(connection!=null){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select*from categories");
                while (resultSet.next()){
                    System.out.println("ID = "+resultSet.getInt(1)+" - "+resultSet.getString(2));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else System.out.println("Error, connection not established");
    }

    public static boolean checkCategory(int i) throws SQLException {
        Connection connection = DBConnection.getConnection();
        boolean isCategoryExists = false;
        if(connection!=null){
            try (PreparedStatement ps = connection.prepareStatement("select 1 from `Categories` where `idCategories` = ?")) {
                ps.setInt(1, i);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        isCategoryExists = true;
                    }
                }
            }
        }else System.out.println("Error, connection not established");
        return isCategoryExists;
    }
    public static boolean checkCategory(String i) throws SQLException {
        Connection connection = DBConnection.getConnection();
        boolean isCategoryExists = false;
        if(connection!=null){
            try (PreparedStatement ps = connection.prepareStatement("select 1 from `Categories` where `name` = ?")) {
                ps.setString(1, i);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        isCategoryExists = true;
                    }
                }
            }
        }else System.out.println("Error, connection not established");
        return isCategoryExists;
    }

    public static boolean deleteCategory(int i){
        boolean x = false;
        Connection connection = DBConnection.getConnection();
        if(connection!=null){
            Statement deleteSratement = null;
            try {
                deleteSratement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                deleteSratement.execute("delete from Categories where idCategories = '"+i+"'");
                x=true;
            } catch (SQLException e) {
            }
        }else System.out.println("Error, connection not established");
        return x;
    }

    public static void updateCategory(int i, String name){
        Connection connection = DBConnection.getConnection();
        if(connection!=null){
            Statement updateSratement = null;
            try {
                updateSratement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                updateSratement.executeUpdate("UPDATE categories SET name = '" + name + "' WHERE idCategories = '" + i + "' ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else System.out.println("Error, connection not established");
    }


}
