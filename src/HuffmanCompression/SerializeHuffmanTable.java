package HuffmanCompression;


import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class SerializeHuffmanTable {

    public static String serialize(Map<Character, String> root) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Character, String> entry : root.entrySet()) {
            sb.append(entry.getKey());
            sb.append("\0");
            sb.append(entry.getValue());
            sb.append("\0");
        }

        return sb.toString();
    }

    public static Map<Character, String> deserialize(String s) {
        if (s == null || s.length() == 0) return null;
        StringTokenizer st = new StringTokenizer(s, "\0");
        Map<Character,String> table = new HashMap<>();

        while (st.hasMoreTokens()){
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
            table.put(key,value);
        }

        return table;
    }

}
