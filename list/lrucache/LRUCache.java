package list.lrucache;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private int maxCacheSize;
    private Map<Integer, Node> map;
    private Node head;
    public Node tail;

    public LRUCache(int maxCacheSize) {
        this.maxCacheSize = maxCacheSize;
        this.map = new HashMap<>();
    }

    /* Put key, value pair in cache. Removes old value for key if necessary.
       Inserts pair into linked list and hash table. */
    public void put(int key, String value) {
        /* Remove if already there. Later a new node will be added to the head. This reflects frequency of usage. */
        if (this.map.containsKey(key)) {
            remove(key);
        }

        /* If full, remove least recently used item from cache. */
        if (this.map.size() >= this.maxCacheSize && this.tail != null) {
            remove(this.tail.key);
        }

        /* Insert new node. */
        Node node = new Node(key, value);
        insertListNodeAtHead(node);
        this.map.put(key, node);
    }

    /* Get value for key and mark as most recently used. */
    public String get(int key) {
        if (!this.map.containsKey(key)) {
            return null;
        }

        Node node = map.get(key);

        /* Move to head to mark as most recently used. */
        if (node != this.head) {
            removeListNode(node);
            insertListNodeAtHead(node);
        }
        return node.value;
    }

    /* Remove key, value pair from cache, deleting from hash table and linked list. */
    public void remove(int key) {
        Node node = this.map.get(key);
        removeListNode(node);
        this.map.remove(key);
    }

    /* Remove node from linked list. */
    private void removeListNode(Node node) {
        if (node == null) {
            return;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node == this.tail) {
            this.tail = node.prev;
        }
        if (node == this.head) {
            this.head = node.next;
        }
    }

    /* Insert node at front of linked list. */
    private void insertListNodeAtHead(Node node) {
        if (this.head == null) {
            this.head = node;
            this.tail = node;
        } else {
            this.head.prev = node;
            node.next = this.head;
            this.head = node;
            this.head.prev = null;
        }
    }

    public void print() {
        Node node = this.head;
        System.out.print("head: " + this.head.key + " - " + this.head.value + "   ");
        System.out.print("tail: " + this.tail.key + " - " + this.tail.value + "   ");
        System.out.print("list: ");
        while (node != null) {
            System.out.print(node.key + " - " + node.value + "   ");
            node = node.next;
        }
        System.out.println();
    }

}
