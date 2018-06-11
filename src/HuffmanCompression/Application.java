package HuffmanCompression;

import java.util.Map;

import static HuffmanCompression.Compressor.encode;
import static HuffmanCompression.HuffmanTree.*;
import static HuffmanCompression.SerializeHuffmanTable.*;


public class Application {

    public static void main(String[] args) {

        String s = "abracadabra";
        System.out.println("String :");
        System.out.println(s);
        Node tree = growTree(s);

        Map<Character, String> tableOfCodes = HuffmanTree.createTable(tree);

        System.out.println("create table from string");
        for (Map.Entry entry : tableOfCodes.entrySet()) {
            System.out.println("key = " + entry.getKey() + "  Val = " + entry.getValue());
        }

        System.out.println("Encoded string");
        String encoded = encode(s,tableOfCodes);
        System.out.println(encoded);

        System.out.println("Serialize table ");
        String y = serialize(tableOfCodes);
        System.out.println(y);

        System.out.println("deserealize table");
        Map<Character,String> deserialize = deserialize(y);

        for (Map.Entry entry : deserialize.entrySet()) {
            System.out.println("key = " + entry.getKey() + "  Val = " + entry.getValue());
        }

        System.out.println("decoded string");
        String decode = Decompressor.decode(encoded,deserialize);
        System.out.println(decode);

    }
}
