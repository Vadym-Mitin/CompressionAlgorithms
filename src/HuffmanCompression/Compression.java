package HuffmanCompression;

import java.util.*;

public class Compression {

    public static void main(String[] args) {
        String s = "AAA BBBBB CDDD E123";
        Map<Character, Integer> map = ListOfRepetition(s);

        for (Map.Entry entry : map.entrySet()) {
            System.out.println("key = " + entry.getKey() + "  Val = " + entry.getValue());
        }
    }

    private static Map<Character, Integer> ListOfRepetition(String s) {
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

//        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
//            @Override
//            public int compare(Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) {
//                return o1.getValue().compareTo(o2.getValue());
//            }
//        });

        //        //LamBadAss 2 (simplified)
//        Collections.sort(list,
//                Comparator.comparing(Map.Entry<Character, Integer>::getValue));


        //LamBadAss
        Collections.sort(list,
                (Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) ->
                        o1.getValue().compareTo(o2.getValue()));

        map = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : list) {
            map.put(entry.getKey(), entry.getValue());
        }

        return map;
    }

    private void doTree(List<Map.Entry<Character, Integer>> nodeSet) {

        //Initialize list of first nodes tree
        List<Node> nodeList = new ArrayList<>();
//        Set<Node> nodeSet = new HashSet<>();
        for (Map.Entry<Character, Integer> entry : nodeSet) {
            nodeList.add(Node.createNode().setSymbol(entry));
//            nodeSet.add(Node.createNode().setSymbol(entry));
        }

        Queue<Node> queue =new PriorityQueue<Node>(nodeSet.size(),
                (Node o1,Node o2) ->o1.getFreq().compareTo(o2.getFreq()) );

        for (Node list: nodeList             ) {
            queue.add(list);
        }
        //create tree
        Node firstNode;
        Node nextNode;
        for (int i = 0; i < nodeList.size(); i++) {
            firstNode = Node.createNode().setLeftNode(nodeList.get(i));
            firstNode.setRightNode(nodeList.get(i + 1));
            nextNode = firstNode;

        }

    }

}

class Node {

    private static int depth;

    private boolean isRoot;
    private boolean isFirst;

    private Node parent;
    private Node leftNode;
    private Node rightNode;

    private Map.Entry<Character, Integer> symbol;

    private int freq;

    private Node() {
    }


    public static Node createNode() {
        return new Node();
    }

    public Node setParent(Node parent) {
        this.parent = parent;
        return this;
    }

    public Node setSymbol(Map.Entry<Character, Integer> symbol) {
        this.symbol = symbol;
        isRoot = true;
        this.freq = symbol.getValue();
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

    public Map.Entry<Character, Integer> getSymbol() {
        return symbol;
    }
}

