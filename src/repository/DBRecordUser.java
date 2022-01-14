package repository;
import java.sql.*;
public class DBRecordUser {

    public static void recordUser(String name,int position,String pass) throws SQLException, InstantiationException {
        Connection connection = DBConnection.getConnection();
        if(connection!=null) {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into users (Name, Position, Pass) values(?, ?, ?)");
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, position);
            preparedStatement.setString(3, pass);
            preparedStatement.execute();
        }else System.out.println("Error, connection not established");
    }


}
