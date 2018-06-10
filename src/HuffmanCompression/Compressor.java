package HuffmanCompression;

import java.sql.SQLOutput;
import java.util.*;

public class Compressor {

    public static void main(String[] args) {

        String s = "abracadabra";

        HuffmanTree tree = HuffmanTree.growTree(s);

        Map<Character, String> tableOfCodes = tree.createTable(tree.getParentNode());


        for (Map.Entry entry : tableOfCodes.entrySet()) {
            System.out.println("key = " + entry.getKey() + "  Val = " + entry.getValue());
        }

//        Byte[] bytes = new Byte[s.length()];
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i <s.length() ; i++) {
            sb.append(tableOfCodes.get(s.charAt(i)));
        }
        System.out.println(sb.toString());

        String x = HuffmanTree.nodeSerialize(tree.getParentNode());
        System.out.println(x);

        HuffmanTree.Node newParent = HuffmanTree.nodeDeserialize(x);

    }


}

