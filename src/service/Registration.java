package service;
import repository.DBCheckNameAndPass;
import repository.DBRecordUser;
import validation.Validation;
import java.sql.SQLException;

public class Registration {

    public static void registration(){
        String name;
        int position;
        String pass;
        System.out.println("Enter your name");
        while (true){
            name = Validation.getString();
            try {
                if(DBCheckNameAndPass.checkName(name)==0){
                    break;
                } else System.out.println("This user exist");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Choose your position");
        System.out.println("1 - Admin");
        System.out.println("2 - User");
        while (true){
            position = Validation.getInt();
            if (position==1||position==2){
                break;
            }else System.out.println("Incorrect number of position");
        }

        System.out.println("Еnter 6-digit password");
        while (true){
        pass = Validation.getString();
            if (pass.length()==6){
                break;
            }else System.out.println("you entered a password other than a 6-digit password");
        }
        try {
            DBRecordUser.recordUser(name,position,pass);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}

