package helper;

import java.util.Arrays;

public class ConsoleHelper {

    public static void getMessage(String message){
        System.out.println(message);
    }

    public static void getMessage(String message, int number){
        System.out.println((message + number));
    }

    public static void getArray(int [] array){
        System.out.println(Arrays.toString(array));
    }
}
