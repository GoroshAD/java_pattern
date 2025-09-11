public class SaleProduct extends Product {
    protected int percent;

    public SaleProduct(String name, int cost, String manufacturer, int percent) {
        super(name, cost, manufacturer);
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    @Override
    public int getCost() {
        return super.getCost() * (100 - percent) / 100;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.append(", процент скидки: ");
        result.append(percent);
        return result.toString();
    }
}
