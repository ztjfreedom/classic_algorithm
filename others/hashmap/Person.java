package others.hashmap;

public class Person {

    public Integer age;
    public String name;

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Person)) return false;

        if (obj == this) return true;

        Person person = (Person) obj;

        return (this.age.equals(person.age)) && this.name.equals(person.name);
    }

    /**
     * The general contract of hashCode is:
     *
     * 1. Whenever it is invoked on the same object more than once during an execution of a Java application,
     *    the hashCode method must consistently return the same integer, provided no information used in equals comparisons on the object is modified.
     *    This integer need not remain consistent from one execution of an application to another execution of the same application.
     * 2. If two objects are equal according to the equals(Object) method, then calling the hashCode method on each of the two objects must produce the same integer result.
     * 3. It is not required that if two objects are unequal according to the equals(Object) method,
     *    then calling the hashCode method on each of the two objects must produce distinct integer results.
     *    However, the programmer should be aware that producing distinct integer results for unequal objects may improve the performance of hash tables.
     */
    @Override
    public int hashCode() {
        return age.hashCode() + name.hashCode();
    }
}
