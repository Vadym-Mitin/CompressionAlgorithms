package HuffmanCompression;

import java.util.Iterator;
import java.util.Map;

public class Decompressor {

    public static void main(String[] args) {
        String s = "abracadabra";

        HuffmanTree tree = HuffmanTree.growTree(s);
//        System.out.println(HuffmanTree.createTable(tree.getParentNode()).toString());
        String compressed = "01011001110011110101100";

//        System.out.println(tree.getParentNode().getCode());

        String swe = decode(compressed, tree);

        System.out.println(swe);
//            for (int j = maxCode.length()-1; j >=0 ; j--) {
//                System.out.println(j);
//            }

    }

    public static String decode(String s, HuffmanTree tree) {
        HuffmanTree.Node parentNode = tree.getParentNode();
        Map<Character, String> table = HuffmanTree.createTable(parentNode);
//        System.out.println(table.toString());
        Iterator<Map.Entry<Character, String>> iterator = table.entrySet().iterator();
        int maxCodeLength = iterator.next().getValue().length();

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
