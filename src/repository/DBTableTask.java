package repository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBTableTask {
    public static void recordTask(String name,int category) throws SQLException, InstantiationException {
        Connection connection = DBConnection.getConnection();
        if(connection!=null) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into tasks (Name, User, Status, TaskCategory) values(?, ?, ?, ?)");
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,1);
            preparedStatement.setInt(3,1);
            preparedStatement.setInt(4, category);
            preparedStatement.execute();
        }else System.out.println("Error, connection not established");
    }
    public static void showTasks(){
        Connection connection = DBConnection.getConnection();
        if(connection!=null){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select Tasks.id, Tasks.name, Categories.Name, statuses.name, users.name FROM (((Tasks INNER JOIN Categories ON Tasks.taskCategory=Categories.idCategories) INNER JOIN statuses ON Tasks.status=Statuses.idStatuses) INNER JOIN users ON Tasks.user=Users.ID)");
                System.out.println("Task №   :  Name   -   Category; Status  -  User");
                while (resultSet.next()){
                    System.out.println("Task № "+resultSet.getInt(1)+" : "+resultSet.getString(2)+" - "+resultSet.getString(3)+"; "+resultSet.getString(4)+" "+resultSet.getString(5));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else System.out.println("Error, connection not established");
    }
    public static List<Integer> showToDoTasks(){
        Connection connection = DBConnection.getConnection();
        List<Integer>list = new ArrayList<>();
        if(connection!=null){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select Tasks.id, Tasks.name, Categories.Name, statuses.name, users.name FROM (((Tasks INNER JOIN Categories ON Tasks.taskCategory=Categories.idCategories) INNER JOIN statuses ON Tasks.status=Statuses.idStatuses) INNER JOIN users ON Tasks.user=Users.ID) WHERE status = 1");
                while (resultSet.next()){
                    System.out.println("Task № "+resultSet.getInt(1)+" : "+resultSet.getString(2)+" - "+resultSet.getString(3)+"; "+resultSet.getString(4)+" "+resultSet.getString(5));
                    list.add(resultSet.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else System.out.println("Error, connection not established");
        return list;
    }
    public static List<Integer> showMyTasks(int id){
        Connection connection = DBConnection.getConnection();
        List<Integer>list = new ArrayList<>();
        if(connection!=null){
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("Select Tasks.id, Tasks.name, Categories.Name, statuses.name, users.name FROM (((Tasks INNER JOIN Categories ON Tasks.taskCategory=Categories.idCategories) INNER JOIN statuses ON Tasks.status=Statuses.idStatuses) INNER JOIN users ON Tasks.user=Users.ID) WHERE (status = 2) AND (user = "+id+")");
                while (resultSet.next()){
                    System.out.println("Task № "+resultSet.getInt(1)+" : "+resultSet.getString(2)+" - "+resultSet.getString(3)+"; "+resultSet.getString(4)+" "+resultSet.getString(5));
                    list.add(resultSet.getInt(1));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else System.out.println("Error, connection not established");
        return list;
    }
    public static void updateTusk(int number, int user ,int status){
        Connection connection = DBConnection.getConnection();
        if(connection!=null){
            Statement updateSratement = null;
            try {
                updateSratement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                updateSratement.executeUpdate("UPDATE tasks SET user = '"+user+"', status = '"+status+"' WHERE id = '"+number+"' ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else System.out.println("Error, connection not established");
    }

    public static void updateNameTask(int i, String name){
        Connection connection = DBConnection.getConnection();
        if(connection!=null){
            Statement updateSratement = null;
            try {
                updateSratement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                updateSratement.executeUpdate("UPDATE tasks SET name = '" + name + "' WHERE id = '" + i + "' ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else System.out.println("Error, connection not established");
    }

    public static void updateCategoryTask(int task, int category){
        Connection connection = DBConnection.getConnection();
        if(connection!=null){
            Statement updateSratement = null;
            try {
                updateSratement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                updateSratement.executeUpdate("UPDATE tasks SET taskCategory = '" + category + "' WHERE id = '" + task + "' ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else System.out.println("Error, connection not established");
    }

    public static void deleteTask(int i){
        Connection connection = DBConnection.getConnection();
        if(connection!=null){
            Statement deleteSratement = null;
            try {
                deleteSratement = connection.createStatement();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                deleteSratement.execute("delete from tasks where id = '"+i+"'");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else System.out.println("Error, connection not established");

    }

    public static boolean checkTask(int i)  {
        Connection connection = DBConnection.getConnection();
        boolean isTaskExists = false;
        if(connection!=null){
            try (PreparedStatement ps = connection.prepareStatement("select 1 from tasks where id = ?")) {
                ps.setInt(1, i);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        isTaskExists = true;
                    }
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }else System.out.println("Error, connection not established");
        return isTaskExists;
    }


}
