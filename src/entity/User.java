package entity;
import validation.Validation;

public class User implements CommonAbility{
    private String name;
    private int id;

    public User(String name,int id) {
        this.name = name;
        this.id = id;
    }

    public void start(){
        System.out.println("Choose a menu number");
        while (true){
            System.out.println("1 - get task");
            System.out.println("2 - complete task");
            System.out.println("0 - Exit");
            String x = Validation.getString();
            if (x.equals("1")) {
                getTask(id);
            } else if (x.equals("2")) {
                completeTask(id);
            } else if (x.equals("0")) {
                break;
            } else System.out.println("Enter correct number");
        }
    }
}
