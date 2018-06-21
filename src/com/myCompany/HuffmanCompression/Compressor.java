package com.myCompany.HuffmanCompression;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Compressor {

    private Compressor() {
    }

    private enum Singleton {
        SINGLETON;

        private static final Compressor COMPRESSOR = new Compressor();

        public Compressor getSingleton() {
            return COMPRESSOR;
        }

    }

    public static Compressor instance() {
        return Singleton.SINGLETON.getSingleton();
    }

    private byte[] EncodeToByte(String encodedText) {

        Map<Character, String> table = HuffmanTree.createTable(encodedText);

        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < encodedText.length(); i++) {
            char encodedChar = encodedText.charAt(i);
            String code = table.get(encodedChar);
            binaryString.append(code);
        }
        String result = binaryString.toString();
        return result.getBytes();
    }

    public void Compress(File file) throws IOException {
        List<String> strings = FileWork.readFileStrings(file);

        StringBuilder sb = new StringBuilder();
        int counter = 0;
        for (String string : strings) {
            if (counter == (strings.size() - 1)) {
                sb.append(string);
                break;
            }
            sb.append(string);
            sb.append("\n");
            counter++;
        }

        String resultData = sb.toString();

        Map<Character, String> table = HuffmanTree.createTable(resultData);

        byte[] encodedBytes = EncodeToByte(resultData);

        byte[] serializeTable = SerializeHuffmanTable.serializeToByteArray(table);

        FileWork.writeEncodedData(file, encodedBytes);
        FileWork.writeMeta(file, serializeTable);

    }
}

