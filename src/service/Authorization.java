package service;
import entity.Admin;
import entity.User;
import repository.DBCheckNameAndPass;
import validation.Validation;
import java.sql.SQLException;


public class Authorization {

    public static void authorization(){
        System.out.println("Enter your name");
        System.out.println("0 - Exit");
        while (true){
            String name = Validation.getString();
            try {
                if (DBCheckNameAndPass.checkName(name)!=0){
                    System.out.println("Hi "+name+ ", enter your pass");
                    System.out.println("0 - Exit");
                    while (true){
                        String pass = Validation.getString();
                        if(pass.equals("0")){
                            break;
                        }else {
                            int i = DBCheckNameAndPass.checkPass(name,pass);
                            if(i==1){
                                System.out.println("Hello Admin!");
                                Admin admin = new Admin(name,DBCheckNameAndPass.checkName(name));
                                admin.start();
                                break;
                            } else if(i==2){
                                System.out.println("Hello User!");
                                User user = new User(name,DBCheckNameAndPass.checkName(name));
                                user.start();
                                break;
                            } else System.out.println("Wrong password");
                        }

                    }
                break;
                }else if(name.equals("0")){
                    break;
                }else System.out.println("This user is not in the database ");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
