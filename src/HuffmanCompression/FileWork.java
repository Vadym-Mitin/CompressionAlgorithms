package HuffmanCompression;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static HuffmanCompression.SerializeHuffmanTable.deserializeFromByteArray;
import static HuffmanCompression.SerializeHuffmanTable.serializeToByteArray;

public class FileWork {
    private static final String COMPRESS_EXTENSION = ".compressed";
    private static final String DECOMPRESS_EXTENSION = ".decompressed";
    private static final String COMPRESS_OPTION = "--compress";
    private static final String DECOMPRESS_OPTION = "--decompress";

    String codeData = "10000111110010111001011111001011101001111100";


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
        hmap.put('\n', "0011");

        byte[] bytes = null;

        try {
            bytes = serializeToByteArray(hmap);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<Character, String> table = new HashMap<>();

        byte[] tableByte = getTableBytes(bytes);

        try {
            table = deserializeFromByteArray(tableByte);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (Map.Entry<Character, String> entry : table.entrySet()) {
            System.out.println(entry.getKey() + " ; " + entry.getValue());
        }


//        int[] sourceArr = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
//        int[] sourceArr2 = {0, 1, 2, 3};
//
//        int[] destArr = new int[sourceArr.length+sourceArr2.length];
//
//        System.arraycopy(sourceArr2, 0, destArr, 0, sourceArr2.length);
//        System.arraycopy(sourceArr, 0, destArr, sourceArr2.length, sourceArr.length);
//
//        for (int i=0; i < destArr.length; i++) {
//            System.out.print(destArr[i] + " ");
//        }
    }

    private static String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e) {
            return "";
        }
    }

    public static void writeFile(byte[] data, String path, String arg) throws IOException, UnexpectedFileFormat {
        File file = new File(path);

        if (arg.equals(COMPRESS_OPTION)) {
            toFileWrite(file, data, COMPRESS_EXTENSION);
        }

        if (arg.equals(DECOMPRESS_OPTION)) {
            if (!getFileExtension(file).equals(COMPRESS_EXTENSION)) {
                throw new UnexpectedFileFormat("File format is unexpected");
            }
            toFileWrite(file, data, DECOMPRESS_EXTENSION);
        }
    }


    private static void toFileWrite(File file, byte[] dataBytes, String extension) throws IOException {
        File compressed = new File(file.getAbsolutePath() + extension);
        compressed.createNewFile();
        Path compressedPath = Paths.get(compressed.getAbsolutePath());
        Files.write(compressedPath, dataBytes);
    }

    public static List<String> readFileStrings(File file) throws IOException {
        List<String> string = Files.readAllLines(Paths.get(file.getAbsolutePath()));
        return string;
    }

    public static byte[] readFileBytes(File file) throws IOException {
        return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
    }

    public static byte[] getTableBytes(byte[] data) {
        byte[] tableLength = new byte[4];
        System.arraycopy(data, 0, tableLength, 0, tableLength.length);
        int readedlength = ByteBuffer.wrap(tableLength).getInt();
        byte[] tableBytes = new byte[readedlength];
        System.arraycopy(data, tableLength.length, tableBytes, 0, readedlength);
        return tableBytes;

    }

    public static Data getDataBytes(byte[] data) {
        byte[] tableLengthBytes = new byte[4];
        System.arraycopy(data, 0, tableLengthBytes, 0, tableLengthBytes.length);
        int readedTablelength = ByteBuffer.wrap(tableLengthBytes).getInt();
        int tableLength = tableLengthBytes.length + readedTablelength;

        byte[] dataLengthBytes = new byte[8];
        System.arraycopy(data, tableLength, dataLengthBytes, 0, dataLengthBytes.length);
        int datalength = ByteBuffer.wrap(dataLengthBytes).getInt();
        int readedLength = data.length - tableLength - 8;
        byte[] dataBytes = new byte[readedLength];
        System.arraycopy(data, tableLength + 8, dataBytes, 0, dataBytes.length);
        return new Data(dataBytes, datalength);
    }

    public static String getBinaryDataString(Data d) {
        int length = d.getLength();
        byte[] data = d.getData();
        BitSet bitSet = BitSet.valueOf(ByteBuffer.wrap(data));
//        bitSet.
        int start = data.length * 8 - length;
        StringBuilder sb = new StringBuilder();
        for (int i = bitSet.length() - 1; i > 0; i--) {
            if (bitSet.get(i)) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        }
        if (bitSet.get(bitSet.length())) {
            sb.append("1");
        } else sb.append("0");

        System.out.println("3 = " + sb.toString());

//        String reverse = new StringBuffer(getStringByByte(data)).reverse().toString();
//        String sub = reverse.substring(3);
//        System.out.println("4 = "+sub);

        return sb.toString();
    }

    public static byte[] getByteByString(String byteString) {
        return new BigInteger(byteString, 2).toByteArray();
    }

    public static String getStringByByte(byte[] bytes) {
        StringBuilder ret = new StringBuilder();
        if (bytes != null) {
            for (byte b : bytes) {
                ret.append(Integer.toBinaryString(b & 255 | 256).substring(1));
            }
        }
        return ret.toString();
    }

    public static class Data {
        private byte[] data;
        private int length;

        public Data(byte[] data, int length) {
            this.data = data;
            this.length = length;
        }

        public byte[] getData() {
            return data;
        }

        public int getLength() {
            return length;
        }
    }


}
