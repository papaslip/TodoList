package entity;
import repository.DBTableCategory;
import repository.DBTableTask;
import validation.Validation;
import java.sql.SQLException;

public class Admin implements CommonAbility{
    private String name;
    private int id;

    public Admin(String name,int id) {
        this.name = name;
        this.id = id;
    }

    public void start(){
        System.out.println("Choose a menu number");
        while (true){
            System.out.println("1 - Operations with categories");
            System.out.println("2 - Operations with tasks");
            System.out.println("3 - Users tasks");
            System.out.println("0 - Exit");
            String x = Validation.getString();
            if (x.equals("1")) {
            while (true){
                System.out.println("1 - add category");
                System.out.println("2 - delete category");
                System.out.println("3 - show categories");
                System.out.println("4 - update categories");
                System.out.println("0 - Exit");
                x = Validation.getString();
                if (x.equals("1")) {
                    addCategory();
                }else if (x.equals("2")) {
                    deleteCategory();
                } else if (x.equals("3")) {
                    showCategories();
                } else if (x.equals("4")) {
                    updateCategories();
                } else if (x.equals("0")) {
                    x="1";
                    break;
                } else System.out.println("Enter correct number");
            }
            } else if (x.equals("2")) {
                while (true){
                    System.out.println("1 - add task");
                    System.out.println("2 - show task");
                    System.out.println("3 - update task");
                    System.out.println("4 - delete task");
                    System.out.println("0 - Exit");
                    x = Validation.getString();
                    if (x.equals("1")) {
                        addTask();
                    }else if (x.equals("2")) {
                        showTasks();
                    } else if (x.equals("3")) {
                        updateTask();
                    } else if (x.equals("4")) {
                        deleteTask();
                    } else if (x.equals("0")) {
                       x="2";
                       break;
                    } else System.out.println("Enter correct number");
                }
            } else if(x.equals("3")){
                while (true){
                    System.out.println("1 - get task");
                    System.out.println("2 - complete task");
                    System.out.println("0 - Exit");
                    x = Validation.getString();
                    if (x.equals("1")) {
                        getTask(id);
                    } else if (x.equals("2")) {
                        completeTask(id);
                    } else if (x.equals("0")) {
                        x="3";
                        break;
                    } else System.out.println("Enter correct number");
                }
            }if (x.equals("0")) {
            break;
            } else System.out.println("Enter correct number");
        }
    }

    private void addCategory(){
        String name;
        System.out.println("Enter new category");
        while (true){
            name = Validation.getString();
            try {
                if(!DBTableCategory.checkCategory(name)){
                    break;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        try {
            DBTableCategory.recordCategory(name);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    private void deleteCategory(){
        int category;
        while (true){
            System.out.println("Select ID category which you want to delete");
            DBTableCategory.showCategories();
            while (true){
                category = Validation.getInt();
                try {
                    if(DBTableCategory.checkCategory(category)){
                        break;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if(DBTableCategory.deleteCategory(category)){
                break;
            }else System.out.println("You cannot delete this category because it is used in tasks");
        }
    }

    private void showCategories(){
        DBTableCategory.showCategories();
        while (true){
            System.out.println("0 - Exit");
            String x = Validation.getString();
            if(x.equals("0")) break;
        }
    }

    private void updateCategories(){
        int category;
        String newCategory;
        System.out.println("Select ID which you want rename category");
        DBTableCategory.showCategories();
        while (true){
            category = Validation.getInt();
            try {
                if(DBTableCategory.checkCategory(category)){
                    break;
                }else System.out.println("Incorrect number");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Enter new name");
        newCategory = Validation.getString();
        DBTableCategory.updateCategory(category,newCategory);
    }

    private void addTask(){
        int idcategory;
        String nameTask;
        System.out.println("Enter new task");
        nameTask = Validation.getString();
        System.out.println("Choose ID a category");
        DBTableCategory.showCategories();
        while (true){
            idcategory = Validation.getInt();
            try {
                if (DBTableCategory.checkCategory(idcategory)){
                    try {
                        DBTableTask.recordTask(nameTask,idcategory);
                        break;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e) {
                        e.printStackTrace();
                    }
                }else System.out.println("Incorrect number");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void showTasks(){
        DBTableTask.showTasks();
        while (true){
            System.out.println("0 - Exit");
            String x = Validation.getString();
            if(x.equals("0")) break;
        }
    }

    private void deleteTask(){
        int id;

            System.out.println("Select № Task which you want to delete");
            System.out.println("0 - Exit");
            DBTableTask.showTasks();
            while (true){
                id=Validation.getInt();
                 if(id==0) {
                     break;
                 }else if(DBTableTask.checkTask(id)){
                     DBTableTask.deleteTask(id);
                     break;
                 }System.out.println("Incorrect №");
            }

    }

    private void updateTask(){
        int number;
        System.out.println("Which № Task do you want to update");
        System.out.println("0 - Exit");
        DBTableTask.showTasks();
        while (true){
            number = Validation.getInt();
            if(DBTableTask.checkTask(number)){
                System.out.println("What do you want to update?");
                System.out.println("1 - Name");
                System.out.println("2 - Category");
                System.out.println("0 - Exit");
                    while (true){
                        String x = Validation.getString();
                        if(x.equals("1")){
                            System.out.println("Enter new Name");
                            String newName = Validation.getString();
                            DBTableTask.updateNameTask(number,newName);
                            break;
                        } else if (x.equals("2")){
                            System.out.println("Choose new Category");
                            DBTableCategory.showCategories();
                            while (true){
                                int i = Validation.getInt();
                                try {
                                    if(DBTableCategory.checkCategory(i)){
                                        DBTableTask.updateCategoryTask(number,i);
                                        break;
                                    }else System.out.println("Incorrect ID");
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                            }
                            break;
                        }else if(x.equals("0")){
                            break;
                        }else System.out.println("Incorrect number");

                }
                    break;
            }else if(number==0){
                break;
            }
            else System.out.println("Incorrect №");
        }
    }

}
