package HuffmanCompression;

import java.nio.ByteBuffer;
import java.util.BitSet;
import java.util.Map;

public class Compressor {

    public static void main(String[] args) {
        String str = "Last index 1";
        int counter = str.length() - 1;
        System.out.println(str.charAt(counter));
    }

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

    public String encode(String encodedText) {
        Node tree = HuffmanTree.growTree(encodedText);

        Map<Character, String> table = HuffmanTree.createTable(tree);

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < encodedText.length(); i++) {
            char encodedChar = encodedText.charAt(i);
            System.out.print("char = " + encodedChar+"; ");
            String code = table.get(encodedChar);
            System.out.println("code = "+code);
            stringBuilder.append(code);
        }

        String encodedData = stringBuilder.toString();
        System.out.println("data =  "+encodedData);

        String serializedTable = SerializeHuffmanTable.serializeToString(table);

        return createOutputString(serializedTable, encodedData);
    }

    public byte[] encodeToByte(String encodedText) {

        Map<Character, String> table = HuffmanTree.createTable(encodedText);

        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < encodedText.length(); i++) {
            char encodedChar = encodedText.charAt(i);
            String code = table.get(encodedChar);
            binaryString.append(code);
        }
        String result = binaryString.toString();
        int length = result.length();
        byte[] bytes = toBytes(result);
        byte[] formatedBytes = formatedBytes(bytes, length);
        return formatedBytes;
    }

    private byte[] toBytes(String binaryString) {

        BitSet bits = new BitSet(binaryString.length());
        int counter = binaryString.length() - 1;
        for (int i = 0; i < binaryString.length(); i++) {
            if (binaryString.charAt(counter) == '1') {
                bits.set(i);
            }
            counter--;
        }

//        int lastBitIndex = binaryString.length() - 1;
//
//        for (int i = lastBitIndex; i >= 0; i--) {
//            if (binaryString.charAt(i) == '1') {
//                bits.set(lastBitIndex - i);
//            }
//        }

        return bits.toByteArray();
    }

    private static byte[] formatedBytes(byte[] data, int length) {
        byte[] bytes = ByteBuffer.allocate(8).putInt(length).array();
        int leng = data.length + bytes.length;
        byte[] destArr = new byte[leng];
        System.arraycopy(bytes, 0, destArr, 0, bytes.length);
        System.arraycopy(data, 0, destArr, bytes.length, data.length);
        return destArr;
    }
}

