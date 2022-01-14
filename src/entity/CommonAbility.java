package entity;

import repository.DBTableTask;
import validation.Validation;
import java.util.List;

public interface CommonAbility {
    public default void getTask(int id){
        System.out.println("Choose task");
        List<Integer> list = DBTableTask.showToDoTasks();
        System.out.println("0 - Exit");
        while(true){
            int x = Validation.getInt();
            if(list.contains(x)){
                DBTableTask.updateTusk(x,id,2);
                break;
            }else if(x==0){
                break;
            }else System.out.println("Incorrect number");
        }
    }
    public default void completeTask(int id){
        System.out.println("Choose task");
        List<Integer> list = DBTableTask.showMyTasks(id);
        System.out.println("0 - Exit");
        while(true){
            int x = Validation.getInt();
            if(list.contains(x)){
                DBTableTask.updateTusk(x,id,3);
                break;
            }else if(x==0){
                break;
            }else System.out.println("Incorrect number");
        }
    }
}
