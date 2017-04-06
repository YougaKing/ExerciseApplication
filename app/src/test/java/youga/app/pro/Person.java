package youga.app.pro;

/**
 * Created by YougaKing on 2017/3/18.
 */

public class Person {

    protected String name;

    public Person(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                '}';
    }
}
