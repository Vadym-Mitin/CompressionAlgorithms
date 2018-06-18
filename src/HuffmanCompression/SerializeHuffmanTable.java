package HuffmanCompression;


import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SerializeHuffmanTable {

    public static byte[] serializeToByteArray(Map<Character, String> table) throws IOException {

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(table);
//            return formatedTableBytes(bos.toByteArray());
            return bos.toByteArray();
        }
    }

    public static Map<Character, String> deserializeFromByteArray(byte[] table) throws IOException, ClassNotFoundException {

        try (ByteArrayInputStream bis = new ByteArrayInputStream(table);
             ObjectInputStream in = new ObjectInputStream(bis)) {
            return (Map<Character, String>) in.readObject();
        }
    }

    public static String serializeToString(Map<Character, String> root) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Character, String> entry : root.entrySet()) {
            sb.append(entry.getKey());
            sb.append("\0");
            sb.append(entry.getValue());
            sb.append("\0");
        }

        return sb.toString();
    }

}
