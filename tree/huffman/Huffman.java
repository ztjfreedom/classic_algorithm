package tree.huffman;

import java.nio.charset.Charset;
import java.util.*;

public class Huffman {

    public static void main(String[] args) {
        String oriStr = "Huffman codes compress data very effectively: savings of 20% to 90% are typical, "
                + "depending on the characteristics of the data being compressed. 中华崛起";
        Map<Character, Integer> statistics = statistics(oriStr.toCharArray());
        String encodedBinaryStr = encode(oriStr, statistics);
        String decodedStr = decode(encodedBinaryStr, statistics);

        System.out.println("Original string: " + oriStr);
        System.out.println("Huffman encode binary string: " + encodedBinaryStr);
        System.out.println("decoded string from binary string: " + decodedStr);

        System.out.println("binary string of UTF-8: "
                + getStringOfByte(oriStr, Charset.forName("UTF-8")));
        System.out.println("binary string of UTF-16: "
                + getStringOfByte(oriStr, Charset.forName("UTF-16")));
        System.out.println("binary string of US-ASCII: "
                + getStringOfByte(oriStr, Charset.forName("US-ASCII")));
        System.out.println("binary string of GB2312: "
                + getStringOfByte(oriStr, Charset.forName("GB2312")));
    }

    public static Map<Character, Integer> statistics(char[] charArray) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : charArray) {
            Character character = c;
            if (map.containsKey(character)) {
                map.put(character, map.get(character) + 1);
            } else {
                map.put(character, 1);
            }
        }

        return map;
    }

    public static String encode(String originalStr, Map<Character, Integer> statistics) {
        if (originalStr == null || originalStr.equals("")) {
            return "";
        }

        char[] charArray = originalStr.toCharArray();
        List<Node> leafNodes = new ArrayList<>();
        buildTree(statistics, leafNodes);
        Map<Character, String> encodeInfo = buildEncodingInfo(leafNodes);

        StringBuilder buffer = new StringBuilder();
        for (char c : charArray) {
            Character character = c;
            buffer.append(encodeInfo.get(character));
        }

        return buffer.toString();
    }

    public static Tree buildTree(Map<Character, Integer> statistics, List<Node> leafs) {
        Character[] keys = statistics.keySet().toArray(new Character[0]);

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
        for (Character character : keys) {
            Node node = new Node();
            node.chars = character.toString();
            node.frequency = statistics.get(character);
            priorityQueue.add(node);
            leafs.add(node);
        }

        int size = priorityQueue.size();
        for (int i = 1; i <= size - 1; i++) {
            Node node1 = priorityQueue.poll();
            Node node2 = priorityQueue.poll();

            Node sumNode = new Node();
            sumNode.chars = node1.chars + node2.chars;
            sumNode.frequency = node1.frequency + node2.frequency;

            sumNode.leftNode = node1;
            sumNode.rightNode = node2;

            node1.parent = sumNode;
            node2.parent = sumNode;

            priorityQueue.add(sumNode);
        }

        Tree tree = new Tree();
        tree.root = priorityQueue.poll();
        return tree;
    }

    public static Map<Character, String> buildEncodingInfo(List<Node> leafNodes) {
        Map<Character, String> codewords = new HashMap<>();
        for (Node leafNode : leafNodes) {
            Character character = leafNode.getChars().charAt(0);
            String codeword = "";
            Node currentNode = leafNode;

            do {
                if (currentNode.isLeftChild()) {
                    codeword = "0" + codeword;
                } else {
                    codeword = "1" + codeword;
                }

                currentNode = currentNode.parent;
            } while (currentNode.parent != null);

            codewords.put(character, codeword);
        }

        return codewords;
    }

    public static String decode(String binaryStr, Map<Character, Integer> statistics) {
        if (binaryStr == null || binaryStr.equals("")) {
            return "";
        }

        char[] binaryCharArray = binaryStr.toCharArray();
        LinkedList<Character> binaryList = new LinkedList<>();
        int size = binaryCharArray.length;
        for (int i = 0; i < size; i++) {
            binaryList.addLast(binaryCharArray[i]);
        }

        List<Node> leafNodes = new ArrayList<>();
        Tree tree = buildTree(statistics, leafNodes);

        StringBuilder buffer = new StringBuilder();

        while (binaryList.size() > 0) {
            Node node = tree.root;

            do {
                Character c = binaryList.removeFirst();
                if (c == '0') {
                    node = node.leftNode;
                } else {
                    node = node.rightNode;
                }
            } while (!node.isLeaf());

            buffer.append(node.chars);
        }

        return buffer.toString();
    }

    public static String getStringOfByte(String str, Charset charset) {
        if (str == null || str.equals("")) {
            return "";
        }

        byte[] byteArray = str.getBytes(charset);
        int size = byteArray.length;
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < size; i++) {
            byte temp = byteArray[i];
            buffer.append(getStringOfByte(temp));
        }

        return buffer.toString();
    }

    public static String getStringOfByte(byte b) {
        StringBuilder buffer = new StringBuilder();
        for (int i = 7; i >= 0; i--) {
            byte temp = (byte) ((b >> i) & 0x1);
            buffer.append(temp);
        }

        return buffer.toString();
    }

}
