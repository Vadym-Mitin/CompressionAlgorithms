package HuffmanCompression;

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

    private String createOutputString(String serializedTable, String encodedMessage) {
        StringBuffer sb = new StringBuffer();

        sb.append(serializedTable);
        sb.append("\n");
        sb.append(encodedMessage);

        return sb.toString();
    }

    public String encode(String data) {
        Node tree = HuffmanTree.growTree(data);

        Map<Character, String> table = HuffmanTree.createTable(tree);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < data.length(); i++) {
            String code = table.get(data.charAt(i));
            stringBuilder.append(code);
        }

        String encodedData = stringBuilder.toString();

        String serializedTable = SerializeHuffmanTable.serialize(table);

        return createOutputString(serializedTable, encodedData);
    }

}

