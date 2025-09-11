public class SaleProduct extends Product {
    protected int percent;
    private int MAX_PERCENT = 100;   // константа вместо магического числа

    public SaleProduct(String name, int cost, String manufacturer, int percent) {
        super(name, cost, manufacturer);
        this.percent = percent;
    }

    public int getPercent() {
        return percent;
    }

    @Override
    public int getCost() {
        return super.getCost() * (MAX_PERCENT - percent) / MAX_PERCENT;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(super.toString());
        result.append(", процент скидки: ");
        result.append(percent);
        return result.toString();
    }
}
