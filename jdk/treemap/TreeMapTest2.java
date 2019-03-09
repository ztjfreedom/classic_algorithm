package jdk.treemap;

import jdk.common.Person;

import java.util.*;

public class TreeMapTest2 {

    public static void main(String[] args) {
        Person p1 = new Person(20, "Tom");
        Person p2 = new Person(10, "Jim");
        Person p3 = new Person(15, "Lucy");

        Map<Integer, Person> hashMap = new HashMap<>();
        hashMap.put(1, p1);
        hashMap.put(2, p2);
        hashMap.put(3, p3);

        TreeMap<Integer, Person> treeMap = new TreeMap<>(new PersonComparator(hashMap));
        treeMap.putAll(hashMap);

        iterate(treeMap);

        Person p4 = new Person(5, "Harden");
        hashMap.put(4, p4);
        treeMap.put(4, p4);

        iterate(treeMap);
        firstLast(treeMap);
        lowerHigher(treeMap, 3);  // key=3 -> age=15, lower -> 10, higher -> 20
    }

    public static class PersonComparator implements Comparator<Integer> {

        private Map<Integer, Person> map;

        public PersonComparator(Map<Integer, Person> map) {
            this.map = map;
        }

        @Override
        public int compare(Integer o1, Integer o2) {
            return this.map.get(o1).age.compareTo(this.map.get(o2).age);
        }
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

    public static void firstLast(TreeMap<Integer, Person> treeMap) {
        Map.Entry<Integer, Person> firstEntry = treeMap.firstEntry();
        Map.Entry<Integer, Person> lastEntry = treeMap.lastEntry();

        System.out.println("first:");
        printEntry(firstEntry);
        System.out.println("last:");
        printEntry(lastEntry);
        System.out.println();
    }

    public static void lowerHigher(TreeMap<Integer, Person> treeMap, int value) {
        Map.Entry<Integer, Person> lowerEntry = treeMap.lowerEntry(value);
        Map.Entry<Integer, Person> higherEntry = treeMap.higherEntry(value);

        System.out.println("lower:");
        printEntry(lowerEntry);
        System.out.println("higher:");
        printEntry(higherEntry);
        System.out.println();
    }

    public static void printEntry(Map.Entry<Integer, Person> entry) {
        System.out.println("key: " + entry.getKey());
        System.out.println("value: " + entry.getValue().age + " " + entry.getValue().name);
    }



}
