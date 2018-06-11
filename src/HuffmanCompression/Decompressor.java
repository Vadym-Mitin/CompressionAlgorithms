package HuffmanCompression;

import java.util.*;

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

    private static String getLongestString(String[] array) {
        int maxLength = 0;
        String longestString = null;
        for (String s : array) {
            if (s.length() > maxLength) {
                maxLength = s.length();
                longestString = s;
            }
        }
        return longestString;
    }

    public static String decode(String s, Map<Character, String> table) {

     String[] arr = new String[table.size()];
     int iter = 0;
        for (String s1 : table.values()) {
            arr[iter] = s1;
//            System.out.print(s1+"; ");
            iter++;
        }
//        System.out.println();
//        System.out.println(arr);
        String max = getLongestString(arr);
        System.out.println("longest string");
        System.out.println(max);
        int maxCodeLength = max.length();

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
