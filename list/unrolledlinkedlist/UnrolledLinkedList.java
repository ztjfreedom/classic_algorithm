package list.unrolledlinkedlist;

public class UnrolledLinkedList<T> {

    private Node firstNode;
    private Node lastNode;
    private int nodeCapacity;
    private int size = 0;

    public UnrolledLinkedList(int nodeCapacity) {
        this.nodeCapacity = nodeCapacity;
        this.firstNode = new Node(nodeCapacity);
        this.lastNode = firstNode;
    }

    public int size() { return size; }

    public boolean isEmpty() { return size == 0; }

    public T get(int index) {
        if (index < 0 || index >= size) return null;

        Node node;
        int pos = 0;
        if (this.size - index > index) {
            node = this.firstNode;
            while (pos <= index - node.elementsNum) {
                pos += node.elementsNum;
                node = node.next;
            }
        } else {
            node = this.lastNode;
            pos = this.size;
            while ((pos -= node.elementsNum) > index) {
                node = node.previous;
            }
        }

        NodeAndIndex nodeAndIdx = convertIdxInList2IdxInNode(index);

        return (T) nodeAndIdx.node.elements[nodeAndIdx.index];

//        return (T) node.elements[index - pos];
    }

    public void insert(T element) {
        insertIntoNode(this.lastNode, this.lastNode.elementsNum, element);
    }

    public void insert(int idxInList, T element) {
        if (idxInList < 0 || idxInList > this.size) return;
        NodeAndIndex nodeAndIdx = convertIdxInList2IdxInNode(idxInList);
        insertIntoNode(nodeAndIdx.node, nodeAndIdx.index, element);
    }

    private void insertIntoNode(Node node, int idxInNode, T element) {

        // if the node is full
        if (node.elementsNum == nodeCapacity) {
            // move half of the elements to the new node
            Node newNode = new Node(this.nodeCapacity);
            int elementsToMove = this.nodeCapacity / 2;
            int startIndex = this.nodeCapacity - elementsToMove;
            for (int i=0; i<elementsToMove; i++) {
                newNode.elements[i] = node.elements[startIndex + i];
                node.elements[startIndex + i] = null;
            }
            node.elementsNum -= elementsToMove;
            newNode.elementsNum = elementsToMove;

            // insert the new node into the list
            newNode.next = node.next;
            newNode.previous = node;
            if (node.next != null) node.next.previous = newNode;
            node.next = newNode;
            if (node == this.lastNode) this.lastNode = newNode;

            // check whether the element should be inserted into the original node or into the new node
            if (idxInNode > node.elementsNum) {
                idxInNode -= node.elementsNum;
                node = newNode;
            }
        }
        for (int i = node.elementsNum; i > idxInNode; i--) {
            node.elements[i] = node.elements[i - 1];
        }
        node.elements[idxInNode] = element;
        node.elementsNum ++;
        this.size ++;
    }

    private NodeAndIndex convertIdxInList2IdxInNode(int idxInList) {
        if (idxInList < this.size / 2) {
            Node node = this.firstNode;
            int num = 0;
            while (idxInList >= num) {
                num += node.elementsNum;
                node = node.next;
            }
            node = node.previous;
            return new NodeAndIndex(node, idxInList - (num - node.elementsNum));
        } else {
            Node node = this.lastNode;
            int num = this.size;
            while (num > idxInList) {
                num -= node.elementsNum;
                node = node.previous;
            }
            node = node.next;
            return new NodeAndIndex(node, idxInList - num);
        }
    }

    public void print() {
        Node node = this.firstNode;
        while (node != null) {
            for (Object o : node.elements) {
                System.out.print(o + " ");
            }
            System.out.println();
            node = node.next;
        }
        System.out.println();
    }

}
