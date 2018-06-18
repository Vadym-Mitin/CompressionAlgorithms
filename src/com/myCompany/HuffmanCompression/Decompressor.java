package com.myCompany.HuffmanCompression;

import java.util.Map;

public class Decompressor {

    public static Decompressor instance() {
        return Singleton.SINGLETON.getSingleton();
    }

    private String getLongestCode(Map<Character, String> table) {
        String[] array = new String[table.size()];
        int iteration = 0;
        for (String value : table.values()) {
            array[iteration] = value;
            iteration++;
        }
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

    public String decode(String s, Map<Character, String> table) throws NotFindCodeInTableException {

        String longestCode = getLongestCode(table);
        int maxCodeLength = longestCode.length();

        StringBuilder sb = new StringBuilder();

        int leftBorder = 0;
        for (int i = 0; i < s.length(); i++) {
            for (int j = maxCodeLength; j > 0; j--) {

                int rightBorder = leftBorder + j;
                if (rightBorder >= s.length()) rightBorder = s.length();
                if (leftBorder >= s.length()) break;

                String substring = s.substring(leftBorder, rightBorder);
                if (table.containsValue(substring)) {
                    for (Map.Entry<Character, String> entry : table.entrySet()) {
                        if (entry.getValue().equals(substring)) {
                            Character code = entry.getKey();
                            sb.append(code);
                            leftBorder += j;
                            i = j;
                            if (leftBorder == s.length()) {
                                throw new NotFindCodeInTableException("something wrong in readed data");
                            }
                            break;
                        }
                    }
                }
            }
        }
        return sb.toString();
    }

    private Decompressor() {
    }

    private enum Singleton {
        SINGLETON;

        private static final Decompressor DECOMPRESSOR = new Decompressor();

        public Decompressor getSingleton() {
            return DECOMPRESSOR;
        }

    }

}