package jdk.linkedhashmap;

import jdk.common.Person;

import java.util.*;

public class LinkedHashMapTest {

    public static void main(String[] args) {
        Person p1 = new Person(20, "Tom");
        Person p2 = new Person(10, "Jim");
        Person p3 = new Person(15, "Lucy");
        Person p4 = new Person(40, "Kate");
        Person p5 = new Person(30, "James");
        Person p6 = new Person(50, "Kobe");

        // LinkedHashMap can ensure the order we put object into map
        Map<Integer, Person> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put(p1.age, p1);
        linkedHashMap.put(p2.age, p2);
        linkedHashMap.put(p3.age, p3);
        linkedHashMap.put(p4.age, p4);
        linkedHashMap.put(p5.age, p5);
        linkedHashMap.put(p6.age, p6);
        iterate(linkedHashMap);

        // HashMap is of no order
        Map<Integer, Person> hashMap = new HashMap<>();
        hashMap.put(p1.age, p1);
        hashMap.put(p2.age, p2);
        hashMap.put(p3.age, p3);
        hashMap.put(p4.age, p4);
        hashMap.put(p5.age, p5);
        hashMap.put(p6.age, p6);
        iterate(hashMap);

        // sort map by value
        Map<Integer, Person> sortedMap = sortMapByValue(hashMap);
        iterate(sortedMap);
    }

    // Get entries' list and sort it
    // Using LinkedHashMap to keep the order
    public static Map<Integer, Person> sortMapByValue(Map<Integer, Person> map) {
        List<Map.Entry<Integer, Person>> list = new ArrayList<>(map.entrySet());

        // new Comparator
        list.sort(new Comparator<Map.Entry<Integer, Person>>() {
            @Override
            public int compare(Map.Entry<Integer, Person> o1, Map.Entry<Integer, Person> o2) {
                return o1.getValue().age.compareTo(o2.getValue().age);
            }
        });
        // lambda
        list.sort((o1, o2) -> o1.getValue().age.compareTo(o2.getValue().age));

        // Comparator.comparing
        list.sort(Comparator.comparing(o -> o.getValue().age));

        Map<Integer, Person> result = new LinkedHashMap<>();
        for (Map.Entry<Integer, Person> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static void iterate(Map<Integer, Person> map) {
        System.out.println("iterate:");
        Iterator<Map.Entry<Integer, Person>> iter = map.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry<Integer, Person> entry = iter.next();
            printEntry(entry);
        }
        System.out.println();
    }

    public static void printEntry(Map.Entry<Integer, Person> entry) {
        System.out.println("key: " + entry.getKey());
        System.out.println("value: " + entry.getValue().age + " " + entry.getValue().name);
    }

}
