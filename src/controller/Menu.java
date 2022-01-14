package controller;
import repository.CreateTables;
import service.Authorization;
import service.Registration;
import validation.Validation;

public class Menu {
    public void menu(){
        System.out.println("Create all tables?");
        System.out.println("1 - Yes");
        System.out.println("2 - No");
       while (true){
           String i = Validation.getString();
           if(i.equals("1")){
               CreateTables.start();
               break;
           } else if (i.equals("2")) break;
           else System.out.println("Incorrect number");
       }
        System.out.println("Hello, choose a menu number");
        while (true){
            System.out.println("1 - Registration");
            System.out.println("2 - Authorization");
            System.out.println("0 - Exit");
            String x = Validation.getString();
            if(x.equals("1")){
                Registration.registration();
            }else if(x.equals("2")){
                Authorization.authorization();
            }else if(x.equals("0")){
                break;
            }else System.out.println("Enter correct number");
        }
    }
}
