import java.util.HashMap;
import java.util.Map;

public class Basket {
    protected Map<Product, Integer> products;

    public Basket () {
        this.products = new HashMap<Product, Integer>();
    }

    public Basket (Map<Product, Integer> products) {
        this.products = products;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public void append(Product product, int times) {
        if (products.containsKey(product)) {
            times += products.get(product);
        }
        products.put(product, times);
    }

    public void delete(Product product, int times) throws IllegalArgumentException {
        if (!products.containsKey(product)) return;
        int productInBasket = products.get(product);
        if (productInBasket < times) {
            throw new IllegalArgumentException("В корзине нет этого продукта в таком количестве.");
        } else if (productInBasket == times) {
            products.remove(product);
        } else {
            products.put(product, productInBasket - times);
        }
    }

    public int getProductCost (Product product) {
        if (!products.containsKey(product)) return 0;
        return products.get(product) * product.getCost();
    }

    public int getBasketCost () {
        int cost = 0;
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            cost += getProductCost(entry.getKey());
        }
        return cost;
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("Содержимое корзины:\n");
        for (Map.Entry<Product, Integer> entry : products.entrySet()) {
            int productCost = getProductCost(entry.getKey());
            result.append(entry.getKey());
            result.append(", количество: ");
            result.append(entry.getValue());
            result.append(", суммарная цена: ");
            result.append(productCost);
            result.append('\n');
        }
        if (products.isEmpty()) {
            result.append("Корзина пуста\n");
        }
        result.append("Суммарная цена корзины: ");
        result.append(getBasketCost());
        return result.toString();
    }
}
