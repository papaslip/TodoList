package repository;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    public static void start(){
        createTableStatuses();
        createTableCategories();
        createTableUsers();
        createTableTasks();
    }
    public static void createTableStatuses(){
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("create table Statuses(idStatuses int auto_increment primary key,name varchar(45) not null)");
            statement.execute("insert into Statuses(name) values ('Status - To do')");
            statement.execute("insert into Statuses(name) values ('In progress with')");
            statement.execute("insert into Statuses(name) values ('Done by')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTableUsers(){
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("create table Users(ID int auto_increment primary key,Name varchar(45) not null,Position int not null,Pass varchar(6) not null)");
            statement.execute("insert into Users(name, Position, pass) values ('     ',1,'111111')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTableCategories(){
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("create table Categories(idCategories int auto_increment primary key,Name varchar(45) not null)");
            statement.execute("insert into Categories(name) values ('other')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createTableTasks(){
        Connection connection = DBConnection.getConnection();
        try {
            Statement statement = connection.createStatement();
            statement.execute("create table Tasks(id int auto_increment primary key,Name varchar(45) not null,User int not null,TaskCategory int not null,Status int not null," +
                    "FOREIGN KEY (Status) REFERENCES Statuses(idStatuses) ON DELETE RESTRICT," +
                    "FOREIGN KEY (User) REFERENCES Users(ID) ON DELETE RESTRICT," +
                    "FOREIGN KEY (TaskCategory) REFERENCES Categories(idCategories) ON DELETE RESTRICT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
