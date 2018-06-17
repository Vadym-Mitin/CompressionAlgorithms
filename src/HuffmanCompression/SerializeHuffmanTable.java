package HuffmanCompression;


import java.io.*;
import java.nio.ByteBuffer;
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
        hmap.put('j', "0011");
        hmap.put('k', "0011");
        hmap.put('l', "0011");
        hmap.put('m', "0011");
        hmap.put('n', "0011");
        hmap.put('o', "0011");
        hmap.put('p', "0011");
        hmap.put('q', "0011");
        hmap.put('r', "0011");
        hmap.put('s', "0011");
        hmap.put('t', "0011");
        hmap.put('u', "0011");
        hmap.put('v', "0011");
        hmap.put('w', "0011");
        hmap.put('x', "0011");
        hmap.put('y', "0011");
        hmap.put('z', "0011");
        hmap.put('1', "0011");
        hmap.put('2', "0011");
        hmap.put('3', "0011");
        hmap.put('4', "0011");
        hmap.put('5', "0011");
        hmap.put('6', "0011");
        hmap.put('7', "0011");
        hmap.put('8', "0011");
        hmap.put('9', "0011");
        hmap.put('0', "0011");
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
            System.out.println(entry.getKey() + " ; " + entry.getValue());
        }

        byte[] bytesA = ByteBuffer.allocate(4).putInt(1695609641).array();

        for (byte b : bytesA) {
            System.out.format("0x%x ", b);
        }

    }

    public static byte[] serializeToByteArray(Map<Character, String> table) throws IOException {

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream out = new ObjectOutputStream(bos)) {
            out.writeObject(table);
            return formatedTableBytes(bos.toByteArray());
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

    private static byte[] formatedTableBytes(byte[] table){
        byte[] table2 = ByteBuffer.allocate(4).putInt(table.length).array();
        int leng = table.length + table2.length;
        byte[] destArr = new byte[leng];
        System.arraycopy(table2,0,destArr, 0,table2.length);
        System.arraycopy(table,0,destArr, table2.length,table.length);
        return destArr;
    }

}
