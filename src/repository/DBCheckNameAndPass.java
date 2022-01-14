package repository;

import java.sql.*;

public class DBCheckNameAndPass {
    public static int checkName(String name) throws SQLException {
        int i = 0;
        Connection connection = DBConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement("select id from `Users` where `Name` = ?")) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    i = rs.getInt(1);
                }
            }
        }
        return i;
    }
    public static int checkPass(String name,String pass) throws SQLException {
        Connection connection = DBConnection.getConnection();
        Statement statement = connection.createStatement();
        int possition = 3;
        ResultSet rs = statement.executeQuery("select * from users where `Name` = '"+name+"'");
        while (rs.next()){
            if(rs.getString(4).equals(pass)){
                possition=rs.getInt(3);
            }
        }
        return possition;
    }

}
