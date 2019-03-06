package others.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonTest {

    public static void main(String[] args) {
        Person p1 = new Person(10, "Tom");
        Person p2 = new Person(20, "Jim");
        Person p3 = new Person(10, "Tom");

        List<Person> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);

        Map<Person, Integer> map = new HashMap<>();
        for (Person p : list) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }

        Person p4 = new Person(10, "Tom");

        System.out.println(map.get(p1));
        System.out.println(map.get(p2));
        System.out.println(map.get(p3));
        System.out.println(map.get(p4));
    }

}
