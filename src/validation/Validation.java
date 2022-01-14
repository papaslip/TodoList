package validation;
import java.util.Scanner;

public class Validation {
    private static Scanner scanner = new Scanner(System.in);

    public static String getString(){
        while (true){
            String x = scanner.nextLine();
            if(x.length()<=45){
                return x;
            }else System.out.println("Length must be less than 45 characters");
        }

    }

    public static int getInt(){
        int i;
        while (true){
            if(scanner.hasNext()){
                if (scanner.hasNextInt()){
                    i = scanner.nextInt();
                    String x = scanner.nextLine();
                    break;
                }else System.out.println(scanner.next()+" - this not number");
            }
        }
        return i;
    }
}
