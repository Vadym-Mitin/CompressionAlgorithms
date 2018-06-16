package HuffmanCompression;

import java.util.*;

public class HuffmanTree {

    public static Node growTree(String s) {

        Map<Character, Integer> nodeSet = ListOfFrequencies(s);

        //Initialize list of first nodes tree
        Queue<Node> queue = new PriorityQueue<>(nodeSet.size(),
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

            parent.setChars(left.getChars()+right.getChars()); // Форматирование.

            queue.add(parent);
        }
        return queue.poll();
    }

    public static Map<Character, String> createTable(Node node) {
        Map<Character, String> table = new LinkedHashMap<>();
        treeTraversalForCreateTable(table, node, "");
        return table;
    }

    private static Map<Character, Integer> ListOfFrequencies(String s) { // Имя метода не может начинаться с большой буквы.
        Map<Character, Integer> listOfRepetition = new HashMap<>();
        char[] characters = s.toCharArray();

        for (char aChar : characters) {
            System.out.print(aChar);
            if (aChar == '\n') System.out.print("#N#");
            if (listOfRepetition.containsKey(aChar)) {
                Integer freq = listOfRepetition.get(aChar) + 1;
                listOfRepetition.put(aChar, freq);
            } else {
                listOfRepetition.put(aChar, 1);
            }
        }

        List<Map.Entry<Character, Integer>> list = new LinkedList<>(listOfRepetition.entrySet());
        Collections.sort(list, (Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) ->
                o1.getValue().compareTo(o2.getValue()));
        listOfRepetition = new LinkedHashMap<>(); // Непонятно, почему ты используешь еще раз эту переменную, а не создаешь новую.

        for (Map.Entry<Character, Integer> entry : list) { // Более правильный способ был бы ходить по keySet()
            listOfRepetition.put(entry.getKey(), entry.getValue());
        }
        return listOfRepetition;
    }

    private static void treeTraversalForCreateTable(Map<Character, String> table, Node node, String s) {
        if (!node.isLeaf()) {
            treeTraversalForCreateTable(table, node.getLeftNode(), s+ node.getLeftNode().getCode());
            treeTraversalForCreateTable(table, node.getRightNode(), s+ node.getRightNode().getCode());
        } else {
            node.setLeafCode(s);
            table.put(node.getSymbol(), node.getCode());
        }
    }


}
