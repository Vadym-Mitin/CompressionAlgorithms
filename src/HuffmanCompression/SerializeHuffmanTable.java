package HuffmanCompression;


import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SerializeHuffmanTable {

    public static void main(String[] args) {
        Map<Character, String> hmap = new HashMap<>();
        //Adding elements to HashMap
        hmap.put('a', "00");
        hmap.put('b', "01");
        hmap.put('c', "1001");
        hmap.put('d', "1100");
        hmap.put('e', "1011");
        hmap.put('f', "0011");
        hmap.put('g', "0011");
        hmap.put('h', "0011");
        hmap.put('i', "0011");
        hmap.put('\n', "0011");

        byte[] bytes = null;

        try {
            bytes = serializeToByteArray(hmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuffer sb = new StringBuffer();
        for (byte aByte : bytes) {
            sb.append(Integer.toBinaryString(aByte));
        }

        String serTable = sb.toString();
        System.out.println(serTable);
        System.out.println(serTable.length());

        Map<Character, String> table = new HashMap<>();
        try {
            table = deserializeFromByteArray(bytes);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Map.Entry<Character, String> entry : table.entrySet()) {
            System.out.println(entry.getKey() + " ; "+entry.getValue());
        }

    }

    public static byte[] serializeToByteArray(Map<Character, String> table) throws IOException {

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(table);
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

    public static Map<Character, String> deserializeFromString(String s) {
        if (s == null || s.length() == 0) return null;
        StringTokenizer st = new StringTokenizer(s, "\0");
        Map<Character, String> table = new HashMap<>();

        while (st.hasMoreTokens()) {
            Character key = null;
            String value = "";
            for (int i = 0; i < 2; i++) {
                String val = st.nextToken();
//                System.out.println(val);
                if (val.equals("\0")) {
                    break;
                }
                if (i == 0) {
                    key = val.charAt(i);
                }
                if (i == 1) {

                    value = val;
                }
            }
            table.put(key, value);
        }

        return table;
    }

}
