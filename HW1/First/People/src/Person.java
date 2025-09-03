import java.util.*;

public class Person {
    protected final String name;
    protected final String surname;
    protected OptionalInt age = OptionalInt.empty();
    protected String address = "";

    public Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = OptionalInt.of(age);
    }

    public void happyBirthday() throws NoSuchElementException {
        try {
            int tmpAge = age.orElseThrow();
            age = OptionalInt.of(tmpAge + 1);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("Age was never set.");
        }
    }

    public boolean hasAge() {
        return age.isPresent();
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean hasAddress() {
        return !address.isEmpty();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public OptionalInt getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append("Name: ").append(name).append(" ")
                .append("Surname: ").append(surname).append(" ")
                .append("Age: ").append(age.isPresent() ? age.orElseThrow() : "-").append(" ")
                .append("Address: ").append(!address.isEmpty() ? address : "-")
                .toString();
    }

    @Override
    public int hashCode() {
        int hash = 0;
        List<String> tmpList = Arrays.asList(name, surname, address);
        for (String elem : tmpList) {
            hash += elem.hashCode();
        }
        if (hasAge()) {
            hash += getAge().orElseThrow();
        }
        return hash;
    }

    public PersonBuilder newChildBuilder() {
        return new PersonBuilder()
                .setSurname(getSurname())
                .setAge(0)
                .setAddress(getAddress());
    }
}
