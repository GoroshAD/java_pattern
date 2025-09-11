import java.util.Map;

public class Customer extends Person {
    protected Basket basket;
    protected int money;

    public Customer (String name, String surname, int money) {
        super(name, surname);
        this.basket = new Basket();
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public Basket getBasket() {
        return basket;
    }

    public void putToBasket(Product product, int times) throws IllegalArgumentException {
        Shop shop = Shop.getInstance();
        shop.takeFromShelf(product, times, basket);
    }

    public void getFromBasket(Product product, int times) {
        Shop shop = Shop.getInstance();
        shop.returnToShelf(product, times, basket);
    }

    public void buyBasket() throws IllegalArgumentException {
        Shop shop = Shop.getInstance();
        int basketCost = basket.getBasketCost();
        if (basketCost > money) {
            throw new IllegalArgumentException("Недостаточно денег для покупки всей корзины.");
        } else if (basketCost == 0) {
            throw new IllegalArgumentException("Корзина пуста, покупать нечего.");
        }
        money -= basketCost;
        fridge.putBasket(basket);
        basket = new Basket();
    }

    public Product getProductWithNameManufacturerFromBasket(String name, String manufacturer) {
        for (Map.Entry<Product, Integer> entry : basket.getProducts().entrySet()) {
            Product product = entry.getKey();
            if (product.getName().equals(name) && product.getManufacturer().equals(manufacturer)) {
                return product;
            }
        }
        return null;
    }

    public Product getProductWithNameManufacturerFromFridge(String name, String manufacturer) {
        for (Map.Entry<Product, Integer> entry : fridge.getProducts().entrySet()) {
            Product product = entry.getKey();
            if (product.getName().equals(name) && product.getManufacturer().equals(manufacturer)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.append(", доступные деньги: ");
        result.append(money);
        result.append("\n");
        result.append(basket);
        return result.toString();
    }
}
