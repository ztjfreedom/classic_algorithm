package list.unrolledlinkedlist;

public class Node {

    public Node next;
    public Node previous;
    public Object[] elements;
    public int elementsNum;

    public Node(int nodeCapacity) {
        this.elements = new Object[nodeCapacity];
    }

}
