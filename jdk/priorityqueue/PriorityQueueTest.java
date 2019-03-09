package jdk.priorityqueue;

import jdk.common.Person;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class PriorityQueueTest {

    public static void main(String[] args) {
        Person p1 = new Person(20, "Tom");
        Person p2 = new Person(10, "Jim");
        Person p3 = new Person(15, "Lucy");

        // PriorityQueue is a Java implementation of Heap
        PriorityQueue<Person> pq;

        // Traditional way to create a priority queue instance by using Comparator
        pq = new PriorityQueue<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.age.compareTo(o2.age);
            }
        });

        // Lambda expression to create a priority queue instance
        pq = new PriorityQueue<>((o1, o2) -> o1.age.compareTo(o2.age));

        // Comparator.comparing to create a queue instance
        pq = new PriorityQueue<>(Comparator.comparing(o -> o.age));

        pq.offer(p1);
        pq.offer(p2);
        pq.offer(p3);

        peekAndElement(pq);
        iterate(pq);
        foreach(pq);
        toArray(pq);
        dequeue(pq);
    }

    // Take a look at the top element of PQ, but not remove it
    public static void peekAndElement(PriorityQueue<Person> pq) {
        System.out.println("peekAndElement:");
        System.out.println(pq.peek().name + ": " + pq.peek().age);
        System.out.println(pq.element().name + ": " + pq.element().age);
        System.out.println();
    }

    // Since Priority Queue is a Heap, not all elements inside are guaranteed in order
    public static void iterate(PriorityQueue<Person> pq) {
        System.out.println("iterate:");
        Iterator<Person> iter = pq.iterator();
        while (iter.hasNext()) {
            Person p = iter.next();
            System.out.println(p.name + ": " + p.age);
        }
        System.out.println();
    }

    // Same to iterate way
    public static void foreach(PriorityQueue<Person> pq) {
        System.out.println("foreach:");
        for (Person p : pq) {
            System.out.println(p.name + ": " + p.age);
        }
        System.out.println();
    }

    // Convert PQ to an array, then sort it
    public static void toArray(PriorityQueue<Person> pq) {
        System.out.println("toArray:");
        Person[] array = pq.toArray(new Person[0]);
        Arrays.sort(array, Comparator.comparing(o -> o.age));
        for (Person p : array) {
            System.out.println(p.name + ": " + p.age);
        }
        System.out.println();
    }

    // When top element is removed from Priority Queue, PQ will automatically shift
    // Elements out of PQ will be in order, but it's not cheap
    // Go through the entire priority queue is an O(n log(n)) operation
    public static void dequeue(PriorityQueue<Person> pq) {
        System.out.println("dequeue:");
        while (!pq.isEmpty()) {
            Person p = pq.poll();
            System.out.println(p.name + ": " + p.age);
        }
        System.out.println();
    }

}
