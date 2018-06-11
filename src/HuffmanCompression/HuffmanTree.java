package HuffmanCompression;

import java.util.*;

public class HuffmanTree {

    private final Node parent;

    public static void main(String[] args) {
        String s = "abracadabra";
        System.out.println(s);

        Map<Character, Integer> map = ListOfFrequencies(s);

        for (Map.Entry entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + "  Val = " + entry.getValue());
        }

        System.out.println();
        System.out.println();

        Node tree = HuffmanTree.growTree(s);
        Map<Character, String> table = createTable(tree);

        for (Map.Entry entry : table.entrySet()) {
            System.out.println("key = " + entry.getKey() + "  Val = " + entry.getValue());
        }
    }

    private HuffmanTree(Node parent) {
        this.parent = parent;
    }

    public Node getParentNode() {
        return parent;
    }

    private static Map<Character, Integer> ListOfFrequencies(String s) {
        Map<Character, Integer> listOfRepetition = new HashMap<>();
        char[] characters = s.toCharArray();
        for (char aChar : characters) {
            if (listOfRepetition.containsKey(aChar)) {
                Integer count = listOfRepetition.get(aChar) + 1;
                listOfRepetition.put(aChar, count);
            } else {
                listOfRepetition.put(aChar, 1);
            }
        }

        List<Map.Entry<Character, Integer>> list = new LinkedList<>(listOfRepetition.entrySet());
        Collections.sort(list, (Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) ->
                o1.getValue().compareTo(o2.getValue()));
        listOfRepetition = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : list) {
            listOfRepetition.put(entry.getKey(), entry.getValue());
        }
        return listOfRepetition;
    }

    public static Node growTree(String s) {

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

            parent.setChars(left.getChars()+right.getChars());

            queue.add(parent);
        }

        Node root = queue.poll();
        return root;
    }

    public static Map<Character, String> createTable(Node node) {
        Map<Character, String> table = new LinkedHashMap<>();
        treeTraversalForCreateTable(table, node, "");
        return table;
    }

    private static void treeTraversalForCreateTable(Map<Character, String> table, Node node, String s) {

        if (!node.isLeaf()) {
//            System.out.println(node.getChars());
            treeTraversalForCreateTable(table, node.getLeftNode(), s+ node.getLeftNode().getCode());
            treeTraversalForCreateTable(table, node.getRightNode(), s+ node.getRightNode().getCode());
        } else {
            node.setLeafCode(s);
            table.put(node.getSymbol(), node.getCode());
        }
    }


}
