package tree.trie;

import java.util.HashMap;

/**
 * 内部节点类
 * @author "zhshl"
 * @date	2014-10-14
 *
 */
public class Trie {

    private class Node {
        private int dup_num;       // 该字串的重复数目，该属性统计重复次数的时候有用
        private int prefix_num;    // 以该字串为前缀的字串数，包括该字串本身
        private Node[] children;   // 此处用数组实现，当然也可以map或list实现以节省空间
        private boolean isLeaf;    // 是否为单词节点
        public Node() {
            children =new Node[26];
        }
    }

    private Node root; // 树根
    public Trie() {
        // 初始化 trie 树
        root=new Node();
    }

    /**
     * 插入字串，用循环代替迭代实现
     */
    public void insert(String word) {
        insert(this.root, word);
    }

    /**
     * 插入字串，用循环代替迭代实现
     */
    private void insert(Node root, String word) {
        word = word.toLowerCase();  // 转化为小写
        char[] charArray = word.toCharArray();

        for(int i=0; i<charArray.length; i++) {
            // 用相对于a字母的值作为下标索引，也隐式地记录了该字母的值
            int index = charArray[i] - 'a';
            if(root.children[index] == null) {
                root.children[index] = new Node();
            }
            root.children[index].prefix_num++;

            // 如果到了字串结尾，则做标记
            if(i == charArray.length - 1) {
                root.children[index].isLeaf = true;
                root.children[index].dup_num ++;
            }

            // root指向子节点，继续处理
            root = root.children[index];
        }
    }

    /**
     * 遍历Trie树，查找所有的words以及出现次数
     * @return HashMap<String, Integer> map
     */
    public HashMap<String,Integer> getAllWords(){
        return preTraversal(this.root, "");
    }

    /**
     * 前序遍历。。。
     * @param root		子树根节点
     * @param prefix	查询到该节点前所遍历过的前缀
     * @return
     */
    private  HashMap<String,Integer> preTraversal(Node root, String prefix) {
        HashMap<String, Integer> map = new HashMap<>();

        if(root != null) {
            if(root.isLeaf) {
                // 当前即为一个单词
                map.put(prefix, root.dup_num);
            }

            for (int i=0; i<root.children.length; i++) {
                if (root.children[i] != null) {
                    char ch = (char) (i + 'a');
                    // 递归调用前序遍历
                    map.putAll(preTraversal(root.children[i], prefix + ch));
                }
            }
        }

        return map;
    }

    /**
     * 判断某字串是否在字典树中
     * @param word
     * @return true if exists, otherwise false
     */
    public boolean isExist(String word) {
        return search(this.root, word);
    }

    /**
     * 查询某字串是否在字典树中
     * @param word
     * @return true if exists, otherwise false
     */
    private boolean search(Node root, String word){
        char[] charArray = word.toLowerCase().toCharArray();
        for (int i=0; i<charArray.length; i++) {
            int index = charArray[i] - 'a';
            if(root.children[index] == null){
                // 如果不存在，则查找失败
                return false;
            }
            root = root.children[index];
        }

        return true;
    }

    /**
     * 得到以某字串为前缀的字串集，包括字串本身！ 类似单词输入法的联想功能
     * @param prefix 字串前缀
     * @return 字串集以及出现次数，如果不存在则返回null
     */
    public HashMap<String, Integer> getWordsForPrefix(String prefix) {
        return getWordsForPrefix(this.root, prefix);
    }

    /**
     * 得到以某字串为前缀的字串集，包括字串本身！
     * @param root
     * @param prefix
     * @return 字串集以及出现次数
     */
    private HashMap<String, Integer> getWordsForPrefix(Node root, String prefix) {
        char[] charArray = prefix.toLowerCase().toCharArray();

        for (int i=0; i<charArray.length; i++) {
            int index = charArray[i] - 'a';
            if (root.children[index] == null) {
                return null;
            }
            root = root.children[index];
        }

        // 结果包括该前缀本身
        // 此处利用之前的前序搜索方法进行搜索
        return preTraversal(root, prefix);
    }

}