import helper.ConsoleHelper;
import model.Aquarium;
import model.InvalidValuesException;

public class Main {
    public static void main(String[] args) throws InvalidValuesException {


        int[] array = {4, 2, 3, 2, 5, 0, 1, 3};
        Aquarium aquarium = new Aquarium(array);


        ConsoleHelper.getMessage("Последовательная высота столбцов в аквариуме: ");
        ConsoleHelper.getArray(array);
        ConsoleHelper.getMessage("Объем оставшейся воды между кубиками: ",aquarium.getWater());


    }

}
