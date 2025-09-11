public class Product {
    protected final String name;
    protected final int cost;
    protected final String manufacturer;

    public Product(String name, int cost, String manufacturer) {
        this.name = name;
        this.cost = cost;
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    @Override
    public int hashCode() {
        return (getName() + getManufacturer()).hashCode();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Имя продукта: ");
        result.append(name);
        result.append(", цена: ");
        result.append(cost);
        result.append(", производитель: ");
        result.append(manufacturer);
        return result.toString();
    }
}
