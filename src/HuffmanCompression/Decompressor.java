package HuffmanCompression;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class Decompressor {

    public static void main(String[] args) {
        String s = "abracadabra";

        Node tree = HuffmanTree.growTree(s);

        Map<Character, String> table = HuffmanTree.createTable(tree);
        String compressed = "01011001110011110101100";

        for (Map.Entry entry : table.entrySet()) {
            System.out.println("key = " + entry.getKey() + "  Val = " + entry.getValue());
        }

        String swe = decode(compressed, table);

        System.out.println(swe);

    }

    public static String decode(String s, Map<Character, String> table) {

        Queue<Map.Entry<Character, String>> queue = new PriorityQueue<>((Map.Entry<Character, String> o2,
                                                                         Map.Entry<Character, String> o1) ->
                o1.getValue().compareToIgnoreCase(o2.getValue()));

        queue.addAll(table.entrySet());

        String maxValue = queue.poll().getValue();
//        System.out.println(maxValue);
        int maxCodeLength = maxValue.length();

        StringBuilder sb = new StringBuilder();

        int counter = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = maxCodeLength; j > 0; j--) {

                int next = counter + j;
                if (next >= s.length()) next = s.length();
                if (counter >= s.length()) break;

                String substring = s.substring(counter, next);
                if (table.containsValue(substring)) {
                    for (Map.Entry<Character, String> entry : table.entrySet()) {
                        if (entry.getValue().equals(substring)) {
                            Character code = entry.getKey();
                            sb.append(code);
                            counter += j;
                            i = j;
                            break;
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

}
