package HuffmanCompression;

import java.util.*;

public class HuffmanTree {

    private final Node parent;

    private HuffmanTree(Node parent) {
        this.parent = parent;
    }

    public Node getParentNode() {
        return parent;
    }

    public static void main(String[] args) {
        String s = "abracadabra";
        Map<Character, Integer> map = ListOfFrequencies(s);

        for (Map.Entry entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + "  Val = " + entry.getValue());
        }

        System.out.println();
        System.out.println();


        HuffmanTree tree = HuffmanTree.growTree(s);
        Map<Character, String> table = tree.createTable(tree.parent);


        for (Map.Entry entry : table.entrySet()) {
            System.out.println("key = " + entry.getKey() + "  Val = " + entry.getValue());
        }
    }


    private static Map<Character, Integer> ListOfFrequencies(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] characters = s.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            char symbol = characters[i];
            if (map.containsKey(symbol)) {
                Integer count = map.get(symbol) + 1;
                map.put(symbol, count);
            } else {
                map.put(symbol, 1);
            }
        }

        List<Map.Entry<Character, Integer>> list = new LinkedList<>(map.entrySet());

        //Sorting witch Comparator
        //LamBadAss
        Collections.sort(list, (Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) ->
                o1.getValue().compareTo(o2.getValue()));

        map = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : list) {
            map.put(entry.getKey(), entry.getValue());
        }

        return map;
    }

    public static HuffmanTree growTree(String s) {

        Map<Character, Integer> nodeSet = HuffmanTree.ListOfFrequencies(s);

        //Initialize list of first nodes tree
        Queue<Node> queue = new PriorityQueue<Node>(nodeSet.size(),
                Comparator.comparing(Node::getFreq));

        for (Map.Entry<Character, Integer> entry : nodeSet.entrySet()) {
            queue.add(Node.createLeaf(entry));
        }

        //create tree
        while (queue.size() > 1) {
            Node right = queue.poll();
            Node left = queue.poll();

            left.setCode("1");
            right.setCode("0");

            int freq = left.getFreq() + right.getFreq();
            Node parent = Node.createNode(left, freq, right);

            parent.setaChar(left.getaChar()+right.getaChar());

            queue.add(parent);
        }

        Node root = queue.poll();
        return new HuffmanTree(root);
    }

    public Map<Character, String> createTable(Node node) {
        Map<Character, String> table = new LinkedHashMap<>();
        treeTraversalforCreateTable(table, node, "");
        return table;
    }

    private void treeTraversalforCreateTable(Map<Character, String> table, Node node, String s) {

        if (!node.isLeaf()) {

            System.out.println(node.getaChar());

            treeTraversalforCreateTable(table, node.leftNode, node.leftNode.getCode() + s);
            treeTraversalforCreateTable(table, node.rightNode, node.rightNode.getCode() + s);
        } else {
            node.setLeafCode(s);
            table.put(node.getTheChar(), node.getCode());
        }
    }


    private static class Node {

        private static int depth = 0;

        private boolean isRoot;
        private boolean isFirst;
        private String code;
        private Map.Entry<Character, Integer> pair;
        private int freq;
        private Node leftNode;
        private Node rightNode;
        private String aChar;
        private Character theChar;

        public Character getTheChar() {
            return theChar;
        }

        public void setaChar(String s) {
            this.aChar = s;
        }
        public String getaChar() {
            return aChar;
        }

        private Node(Node left, int freq, Node right) {
            this.leftNode = left;
            this.rightNode = right;
            this.freq = freq;
        }

        private Node(Node left, int freq, Node right, Character aChar) {
            this.leftNode = left;
            this.rightNode = right;
            this.freq = freq;
            this.aChar = aChar.toString();
            this.theChar = aChar;

        }

        public static Node createLeaf(Map.Entry<Character, Integer> symbol) {
            return new Node(null, symbol.getValue(), null, symbol.getKey());
        }

        public static Node createNode(Node left, int freq, Node right) {
            depth++;
            return new Node(left, freq, right);
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            if (code.equals("1") || code.equals("0")) {
                this.code = code;
//                return this;
            } else throw new IllegalStateException("this wrong code type 0 or 1");
        }

        public void setLeafCode(String code) {
            if (isLeaf()) {
                this.code = code;
            }
        }

        public boolean isLeaf() {
            return leftNode == null && rightNode == null && !(aChar.isEmpty());
        }


        public Node setPair(Map.Entry<Character, Integer> pair) {
            this.pair = pair;
            isRoot = true;
            this.freq = pair.getValue();
            return this;
        }

        public Node setLeftNode(Node leftNode) {
            this.leftNode = leftNode;
            return this;
        }

        public Node setRightNode(Node rightNode) {
            this.rightNode = rightNode;
            return this;
        }

        public Node getLeftNode() {
            return leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public Integer getFreq() {
            return freq;
        }

        public Map.Entry<Character, Integer> getPair() {
            return pair;
        }

    }
}
