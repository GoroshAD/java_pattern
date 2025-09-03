import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Logger logger = Logger.getInstance();
        Scanner scanner = new Scanner(System.in);
        logger.log("Запускаем программу");
        logger.log("Просим пользователя ввести входные данные для списка");
        System.out.print("Введите размер списка: ");
        int listSize = scanner.nextInt();
        System.out.print("Введите верхнюю границу для значений: ");
        int upperLimit = scanner.nextInt();
        logger.log("Создаём и наполняем список");
        List<Integer> source = fillList(listSize, upperLimit);
        listPrinter(source, "Вот случайный список: ");
        logger.log("Просим пользователя ввести входные данные для фильтрации");
        System.out.print("Введите порог для фильтра: ");
        int treshold = scanner.nextInt();
        Filter filter = new Filter(treshold);
        List<Integer> filteredList = filter.filterOut(source);
        logger.log("Выводим результат на экран");
        listPrinter(filteredList, "Отфильтрованный список: ");
        logger.log("Завершаем программу");
    }

    public static List<Integer> fillList(int listSize, int upperLimit) {
        List<Integer> result = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < listSize; ++i) {
            result.add(random.nextInt(upperLimit));
        }
        return result;
    }

    public static void listPrinter(List<Integer> list, String msg) {
        System.out.print(msg);
        for (int elem : list) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }
}
