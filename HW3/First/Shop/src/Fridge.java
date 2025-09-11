import java.util.HashMap;
import java.util.Map;

public class Fridge {
    protected Map<Product, Integer> products;

    public Fridge() {
        this.products = new HashMap<>();
    }

    public Fridge(Basket basket) {
        this.products = basket.getProducts();
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void putBasket(Basket basket) {
        for (Map.Entry<Product, Integer> entry : basket.getProducts().entrySet()) {
            Product product = entry.getKey();
            int times = entry.getValue();
            if (products.containsKey(product)) {
                times += products.get(product);
            }
            products.put(product, times);
        }
    }

    public void eatProduct(Product product, int times) throws IllegalArgumentException {
        if (!products.containsKey(product)) {
            throw new IllegalArgumentException("В холодильнике нет такого продукта.");
        }
        int productInFridge = products.get(product);
        if (productInFridge < times) {
            throw new IllegalArgumentException("В холодильнике нет этого продукта в таком количестве.");
        } else if (productInFridge == times) {
            products.remove(product);
        } else {
            products.put(product, productInFridge - times);
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Состав холодильника:\n");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            result.append(entry.getKey());
            result.append(", в количестве: ");
            result.append(entry.getValue());
            result.append('\n');
        }
        if (products.isEmpty()) {
            result.append("Холодильник пустой.\n");
        }
        result.delete(result.length() - 1, result.length());   // избавляемся от \n чтобы везде был одинаковый toString
        return result.toString();
    }
}
