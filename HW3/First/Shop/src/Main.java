import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final Shop shop = Shop.getInstance();

    public static void main(String[] args) {
        System.out.println("Магазин открылся!");
        Map<Product, Integer> newProducts = new HashMap<>();
        newProducts.put(new Product("Молоко", 100, "Простоквашино"), 20);
        newProducts.put(new SaleProduct("Молоко", 100, "Село Зелёное", 15), 20);
        newProducts.put(new Product("Хлеб Белый", 50, "Черёмушки"), 30);
        newProducts.put(new Product("Хлеб Чёрный", 70, "Бородинский"), 20);
        newProducts.put(new SaleProduct("Сосиски", 400, "Ближние Горки", 25), 10);
        newProducts.put(new Product("Сосиски", 350, "Папа может!"), 15);
        newProducts.put(new Product("Вода", 60, "Святой источник"), 30);
        newProducts.put(new SaleProduct("Вода", 50, "Сенежская", 10), 20);
        shop.addProducts(newProducts);
        System.out.println("В магазин завезли продукты!");
        System.out.println(shop);

        System.out.println("Добро пожаловать в наш магазин!");
        Customer customer = customerArrival();
        System.out.println("Вы вошли в магазин как:");
        System.out.println(customer);

        launchInterface(customer);
        System.out.println("Итоги:");
        System.out.println(customer);
        System.out.println(customer.getFridge());
    }

    public static Customer customerArrival() {
        System.out.println("Пожалуйста, введите имя:");
        String name = scanner.nextLine();
        System.out.println("Пожалуйста, введите фамилию:");
        String surname = scanner.nextLine();
        System.out.println("Пожалуйста, введите баланс:");
        int money = scanner.nextInt();
        Customer customer = new Customer(name, surname, money);
        System.out.println("Хотите дополнительно указать возраст и адрес?");
        scanner.nextLine();
        String answer = "";
        while (!answer.equalsIgnoreCase("да") && !answer.equalsIgnoreCase("нет")) {
            System.out.println("Пожалуйста, напишите да или нет:");
            answer = scanner.nextLine();
        }
        if (answer.equalsIgnoreCase("да")) {
            System.out.println("Пожалуйста, введите возраст:");
            int age = scanner.nextInt();
            customer.setAge(age);
            scanner.nextLine();
            System.out.println("Пожалуйста, введите адрес:");
            String address = scanner.nextLine();
            customer.setAddress(address);
        }
        return customer;
    }

    public static void launchInterface(Customer customer) {
        int answer = 0;
        try {
            while (true) {
                System.out.println("Доступные действия:");
                System.out.println("1 - положить в корзину продукт");
                System.out.println("2 - узнать содержимое магазина");
                System.out.println("3 - поиск продукта");
                System.out.println("4 - узнать содержимое корзины");
                System.out.println("5 - убрать из корзины продукт");
                System.out.println("6 - купить содержимое корзины");
                System.out.println("7 - посмотреть баланс");
                System.out.println("8 - узнать что уже есть в холодильнике дома");
                System.out.println("9 - перекусить тем что есть в холодильнике дома");
                System.out.println("10 - покинуть магазин");
                try {
                    answer = scanner.nextInt();
                } catch (RuntimeException e) {
                    System.out.println("Пожалуйста, выберите из доступных действий.");
                    scanner.next();
                    continue;
                }

                switch (answer) {
                    case 1:
                        putProductToBasket(customer);
                        break;
                    case 2:
                        System.out.println(shop);
                        break;
                    case 3:
                        filterProductSearching(customer);
                        break;
                    case 4:
                        System.out.println(customer.getBasket());
                        break;
                    case 5:
                        deleteProductFromBasket(customer);
                        break;
                    case 6:
                        buyProductsFromBasket(customer);
                        break;
                    case 7:
                        System.out.println("Баланс составляет: " + customer.getMoney());
                        break;
                    case 8:
                        System.out.println(customer.getFridge());
                        break;
                    case 9:
                        eatProductsFromFridge(customer);
                        break;
                    case 10:
                        if (customer.getBasket().isEmpty()) {
                            System.out.println("До новых встреч!");
                            throw new Exception("Уходим из магазина");
                        } else {
                            System.out.println("Сначала заплатите за корзину или очистите её.");
                        }
                    default:
                        System.out.println("Пожалуйста, выберите из доступных действий.");
                }
            }
        } catch (Exception e) {
            System.out.println("Вы вышли из магазина.");
        }
    }

    public static void putProductToBasket(Customer customer) {
        scanner.nextLine();
        System.out.println("Введите название продукта:");
        String name = scanner.nextLine();
        System.out.println("Введите название производителя:");
        String manufacturer = scanner.nextLine();
        Product product = shop.getProductWithNameManufacturer(name, manufacturer);
        if (product == null) {
            System.out.println("Такого продукта в магазине нет.");
        } else {
            System.out.println("Введите необходимое количество продукта:");
            int times = scanner.nextInt();
            try {
                customer.putToBasket(product, times);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("Продукт успешно добавлен в корзину!");
        }
    }

    public static void filterProductSearching(Customer customer) {
        System.out.println("Вас интересует поиск продукта по названию или по производителю?");
        String filter = "";
        while (!filter.equalsIgnoreCase("по названию") && !filter.equalsIgnoreCase("по производителю")) {
            scanner.nextLine();
            System.out.println("Введите \"по названию\" или \"по производителю\"");
            filter = scanner.nextLine();
        }
        if (filter.equalsIgnoreCase("по названию")) {
            System.out.println("Введите название продукта:");
            String name = scanner.nextLine();
            printMap(shop.filterName(name));
        } else {
            System.out.println("Введите название производителя:");
            String manufacturer = scanner.nextLine();
            printMap(shop.filterManufacturer(manufacturer));
        }
    }

    public static void deleteProductFromBasket(Customer customer) {
        scanner.nextLine();
        System.out.println("Введите название продукта:");
        String name = scanner.nextLine();
        System.out.println("Введите название производителя:");
        String manufacturer = scanner.nextLine();
        Product product = customer.getProductWithNameManufacturerFromBasket(name, manufacturer);
        if (product == null) {
            System.out.println("Такого продукта в корзине нет.");
        } else {
            System.out.println("Введите необходимое количество продукта:");
            int times = scanner.nextInt();
            try {
                customer.getFromBasket(product, times);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("Продукт успешно удалён из корзины!");
        }
    }

    public static void buyProductsFromBasket(Customer customer) {
        try {
            customer.buyBasket();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }
        System.out.println("Содержимое корзины успешно куплено!");
    }

    public static void eatProductsFromFridge(Customer customer) {
        scanner.nextLine();
        System.out.println("Введите название продукта:");
        String name = scanner.nextLine();
        System.out.println("Введите название производителя:");
        String manufacturer = scanner.nextLine();
        Product product = customer.getProductWithNameManufacturerFromFridge(name, manufacturer);
        if (product == null) {
            System.out.println("Такого продукта в холодильнике нет.");
        } else {
            System.out.println("Введите необходимое количество продукта:");
            int times = scanner.nextInt();
            try {
                customer.eatProduct(product, times);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                return;
            }
            System.out.println("Продукт успешно съеден!");
        }
    }

    public static void printMap(Map<Product, Integer> products) {
        System.out.println("Результаты:");
        if (products.isEmpty()) {
            System.out.println("Таких продуктов не найдено.");
        } else {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                System.out.println(entry.getKey() + ", в количестве: " + entry.getValue());
            }
        }
    }
}
