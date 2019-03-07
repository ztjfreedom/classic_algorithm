package list.lrucache;

public class LRUCacheTest {

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(5);
        cache.put(1, "Tom");
        cache.put(2, "Jim");
        cache.put(3, "Lily");
        cache.put(4, "Lucy");
        cache.put(5, "Kate");
        cache.put(6, "Kobe");
        cache.print();
        cache.put(5, "Kate");
        cache.print();
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        cache.print();
        cache.remove(3);
        cache.print();
    }

}
