package HuffmanCompression;

import java.util.*;

public class Compressor {

        public static String encode(String encodedString, Map<Character, String> table) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i <encodedString.length(); i++) {
            String code = table.get(encodedString.charAt(i));
            stringBuilder.append(code);
        }

        return stringBuilder.toString();
    }


}

