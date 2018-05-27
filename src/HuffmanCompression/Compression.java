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

        //LamBadAss
//        Collections.sort(list,
//                (Map.Entry<Character, Integer> o1, Map.Entry<Character, Integer> o2) ->
//                        o1.getValue().compareTo(o2.getValue()));

        //LamBadAss 2 (simplified)
        Collections.sort(list,
                Comparator.comparing(Map.Entry<Character, Integer>::getValue));

        map = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : list) {
            map.put(entry.getKey(), entry.getValue());
        }

        return map;
    }

    private class Node {
        private Node leftNode;
        private Node rightNode;
        private char symbol;
        private int counter;

        public Node getLeftNode() {
            return leftNode;
        }

        public Node getRightNode() {
            return rightNode;
        }

        public int getCounter() {
            return counter;
        }
    }
}
