import java.util.Map;
import java.util.NoSuchElementException;
import java.util.OptionalInt;

public class Person {
    protected final String name;
    protected final String surname;
    protected OptionalInt age = OptionalInt.empty();
    protected String address = "";
    protected final Fridge fridge;

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
        this.fridge = new Fridge();
    }

    public void setAge(int age) {
        this.age = OptionalInt.of(age);
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean hasAge() {
        return age.isPresent();
    }

    public int getAge() throws NoSuchElementException {
        return age.orElseThrow();
    }

    public String getAddress() {
        return address;
    }

    public Fridge getFridge() {
        return fridge;
    }

    public void eatProduct(Product product, int times) throws IllegalArgumentException {
        fridge.eatProduct(product, times);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Имя: ");
        result.append(name);
        result.append(", фамилия: ");
        result.append(surname);
        if (hasAge()) {
            result.append(", возраст: ");
            result.append(getAge());
        }
        if (!address.isEmpty()) {
            result.append(", адрес: ");
            result.append(address);
        }
        return result.toString();
    }
}
