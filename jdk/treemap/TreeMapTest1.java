package jdk.treemap;

import jdk.common.Person;

import java.util.*;

public class TreeMapTest1 {

    public static void main(String[] args) {
        Person p1 = new Person(20, "Tom");
        Person p2 = new Person(10, "Jim");
        Person p3 = new Person(15, "Lucy");

        // TreeMap is an implementation of Red-Black tree
        TreeMap<Integer, Person> treeMap;
        treeMap = new TreeMap<>();

        treeMap.put(p1.age, p1);
        treeMap.put(p2.age, p2);
        treeMap.put(p3.age, p3);

        firstLast(treeMap);
        lowerHigher(treeMap, 15);
        floorCeil(treeMap, 15);
        iterate(treeMap);
        foreach(treeMap);

        // convert to reverse order
        NavigableMap<Integer, Person> navigableMap = treeMap.descendingMap();
        iterate(navigableMap);

        // create an instance by using Comparator to construct reverse key order
        treeMap = new TreeMap<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });

        // reverse key order by lambda expression
        treeMap = new TreeMap<>((o1, o2) -> o2.compareTo(o1));

        // reverse key order by Comparator.reverseOrder()
        treeMap = new TreeMap<>(Comparator.reverseOrder());

        treeMap.put(p1.age, p1);
        treeMap.put(p2.age, p2);
        treeMap.put(p3.age, p3);
        iterate(treeMap);
    }

    public static void firstLast(TreeMap<Integer, Person> treeMap) {
        Map.Entry<Integer, Person> firstEntry = treeMap.firstEntry();
        Map.Entry<Integer, Person> lastEntry = treeMap.lastEntry();

        System.out.println("first:");
        printEntry(firstEntry);
        System.out.println("last:");
        printEntry(lastEntry);
        System.out.println();
    }

    // strictly less / greater than the given key
    public static void lowerHigher(TreeMap<Integer, Person> treeMap, int value) {
        Map.Entry<Integer, Person> lowerEntry = treeMap.lowerEntry(value);
        Map.Entry<Integer, Person> higherEntry = treeMap.higherEntry(value);

        System.out.println("lower:");
        printEntry(lowerEntry);
        System.out.println("higher:");
        printEntry(higherEntry);
        System.out.println();
    }

    // less / greater than or equal to the given key
    public static void floorCeil(TreeMap<Integer, Person> treeMap, int value) {
        Map.Entry<Integer, Person> floorEntry = treeMap.floorEntry(value);
        Map.Entry<Integer, Person> ceilingEntry = treeMap.ceilingEntry(value);

        System.out.println("floor:");
        printEntry(floorEntry);
        System.out.println("ceil:");
        printEntry(ceilingEntry);
        System.out.println();
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

    public static void foreach(Map<Integer, Person> map) {
        System.out.println("foreach:");
        for (Map.Entry<Integer, Person> entry : map.entrySet()) {
            printEntry(entry);
        }
        System.out.println();
    }

    public static void printEntry(Map.Entry<Integer, Person> entry) {
        System.out.println("key: " + entry.getKey());
        System.out.println("value: " + entry.getValue().age + " " + entry.getValue().name);
    }

}
