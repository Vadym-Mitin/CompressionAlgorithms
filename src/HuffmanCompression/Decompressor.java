package HuffmanCompression;

import java.util.Map;

public class Decompressor {

    private Map<Character, String> decompressedTable;
    private String decompressedData; // Неиспользуемая переменная.

    private Decompressor() {
    }

    private enum Singleton {
        SINGLETON;

        private static final Decompressor DECOMPRESSOR = new Decompressor();

        public Decompressor getSingleton() {
            return DECOMPRESSOR;
        }

    }


    public static Decompressor instance() {
        return Singleton.SINGLETON.getSingleton();
    }

    private String getLongestString(String[] array) {
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

    public Map<Character, String> deco0mpressTable(String compressedTable) { // Непонятно, что делает 0 в названии метода.
        this.decompressedTable = SerializeHuffmanTable.deserializeFromString(compressedTable);
        return decompressedTable;
    }

    public String decode(String s, Map<Character, String> table) {

        String[] arr = new String[table.size()];
        int iter = 0; // Что такое iter?
        for (String s1 : table.values()) {
            arr[iter] = s1;
            iter++;
        }
        String max = getLongestString(arr);
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
                        // Почему бы не записать таблицу как <String, Character> и упростить этот метод?
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
