package HuffmanCompression;

import java.util.*;

public class Compressor {

    public static void main(String[] args) {

        String s = "abracadabra";

        HuffmanTree tree = HuffmanTree.createTree(s);

        Map<Character, String> table = new HashMap<>();
        HuffmanTree.createTable(table,tree.getParentNode(),"");

        for (Map.Entry entry : table.entrySet()) {
            System.out.println("key = " + entry.getKey() + "  Val = " + entry.getValue());
        }

        Byte[] bytes = new Byte[s.length()];

    }


}

