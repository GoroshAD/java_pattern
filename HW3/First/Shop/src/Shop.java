import java.util.HashMap;
import java.util.Map;

public class Shop {   // singleton
    protected static Shop instance = null;
    protected Map<Product, Integer> products = new HashMap<>();

    private Shop() {}

    public static Shop getInstance() {
        if (instance == null) {
            instance = new Shop();
        }
        return instance;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public Map<Product, Integer> filterName(String name) {
        Map<Product, Integer> result = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getName().equals(name)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public Map<Product, Integer> filterManufacturer(String manufacturer) {
        Map<Product, Integer> result = new HashMap<>();
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            if (entry.getKey().getManufacturer().equals(manufacturer)) {
                result.put(entry.getKey(), entry.getValue());
            }
        }
        return result;
    }

    public Product getProductWithNameManufacturer(String name, String manufacturer) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            if (product.getName().equals(name) && product.getManufacturer().equals(manufacturer)) {
                return product;
            }
        }
        return null;
    }

    public int getProductQuantity(Product product) {
        if (!products.containsKey(product)) {
            return 0;
        }
        return products.get(product);
    }

    public void takeFromShelf(Product product, int times, Basket basket) throws IllegalArgumentException {
        int shopProductQuantity = getProductQuantity(product);
        if (times > shopProductQuantity) {
            throw new IllegalArgumentException("В магазине нет этого продукта в таком количестве.");
        } else if (shopProductQuantity == times) {
            products.remove(product);
        } else {
            products.put(product, shopProductQuantity - times);
        }
        basket.append(product, times);
    }

    public void returnToShelf(Product product, int times, Basket basket) throws IllegalArgumentException {
        basket.delete(product, times);
        products.put(product, getProductQuantity(product) + times);
    }

    public void addProducts(Map<Product, Integer> products) {
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            Product product = entry.getKey();
            this.products.put(product, getProductQuantity(product) + entry.getValue());
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Состав магазина:\n");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            result.append(entry.getKey());
            result.append(", в количестве: ");
            result.append(entry.getValue());
            result.append('\n');
        }
        result.delete(result.length() - 1, result.length());   // избавляемся от \n чтобы везде был одинаковый toString
        return result.toString();
    }
}
