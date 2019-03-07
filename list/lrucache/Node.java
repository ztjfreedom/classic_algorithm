package list.lrucache;

public class Node {

    public Node next;
    public Node prev;
    public int key;
    public String value;

    public Node(int k, String v) {
        this.key = k;
        this.value = v;
    }

}
