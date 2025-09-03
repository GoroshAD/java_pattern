import java.util.OptionalInt;

public class PersonBuilder {
    protected String name;
    protected String surname;
    protected OptionalInt age = OptionalInt.empty();
    protected String address = "";

    public PersonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public PersonBuilder setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public PersonBuilder setAge(int age) {
        this.age = OptionalInt.of(age);
        return this;
    }

    public PersonBuilder setAddress(String address) {
        this.address = address;
        return this;
    }

    public Person build() {
        Person person;
        if (age.isPresent()) {
            person = new Person(name, surname, age.orElseThrow());
        } else {
            person = new Person(name, surname);
        }
        person.setAddress(address);
        return person;
    }
}