package tree.bst;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree {

    public static void main(String[] args) {
        Node root = new Node(50);
        insert(root, 30);
        insert(root, 80);
        insert(root, 20);
        insert(root, 35);
        insert(root, 34);
        insert(root, 32);
        insert(root, 40);
        insert(root, 70);
        insert(root, 75);
        insert(root, 100);
        print(root);
        delete(root, 20);
        print(root);
        delete(root, 70);
        print(root);
        delete(root, 35);
        print(root);
    }

    public static class Node {
        public Integer val;
        public Node left;
        public Node right;
        public Node(int val) {
            this.val = val;
        }
    }

    public static boolean search(Node node, int val) {
        if (node == null) return false;

        if (node.val == val) {
            return true;
        } else if (node.val > val) {
            return search(node.left, val);
        } else {
            return search(node.right, val);
        }
    }

    public static Node insert(Node node, int val) {
        if (node == null) return new Node(val);

        if (node.val > val) {
            node.left = insert(node.left, val);
        } else if (node.val < val) {
            node.right = insert(node.right, val);
        }
        return node;
    }

    public static Node findMin(Node node) {
        if (node == null) return null;
        if (node.left == null) return node;
        return findMin(node.left);
    }

    public static Node delete(Node node, int val) {
        if (node == null) return null;

        if (node.val == val) {
            if (node.left == null && node.right == null) {
                node = null;
            } else if (node.right == null) {
                node = node.left;
            } else if (node.left == null) {
                node = node.right;
            } else {
                node.val = findMin(node.right).val;
                node.right = delete(node.right, node.val);
            }
        } else if (node.val > val) {
            node.left = delete(node.left, val);
        } else {
            node.right = delete(node.right, val);
        }

        return node;
    }

    public static void print(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if (node == null) {
                System.out.print("null ");
            } else {
                System.out.print(node.val + " ");
                if (node.left != null || node.right != null) {
                    queue.offer(node.left);
                    queue.offer(node.right);
                }
            }
        }
        System.out.println();
    }

}
