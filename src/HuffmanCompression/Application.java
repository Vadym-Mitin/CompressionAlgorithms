package HuffmanCompression;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Application {
    private static final String COMPRESS_OPTION = "--compress";
    private static final String DECOMPRESS_OPTION = "--decompress";

    static  String codeData = "10000111110010111001011111001011101001111100";

    public static void main(String[] args) throws UnexpectedFileFormat, UnexpectedOptions, IOException, ClassNotFoundException, NotFindCodeInTableException {
        //////
//        System.out.println("Start App");
        if (args.length > 1) {
            String option = args[0];
            File file = new File(args[1]);
            /////
            System.out.println("arg >2");
            if (option.equals(COMPRESS_OPTION)) {
                //////
//                System.out.println("compress");
                Compressor compressor = Compressor.instance();
                List<String> strings = FileWork.readFileStrings(file);
//                for (String string : strings) {
//                    System.out.println("str = "+string);
//                }

                StringBuilder sb = new StringBuilder();
                int counter = 0;
                for (String string : strings) {
                    if (counter == (strings.size() - 1)) {
                        sb.append(string);
//                        System.out.println("size");
                        break;
                    }
//                    System.out.println(" not size");
                    sb.append(string);
                    sb.append("\n");
                    counter++;
                }

                String resultData = sb.toString();
//                System.out.println("res data  = "+resultData);

                Map<Character, String> table = HuffmanTree.createTable(resultData);
                byte[] encodedBytes = compressor.encodeToByte(resultData);

                byte[] serializeTable = SerializeHuffmanTable.serializeToByteArray(table);
                int summarylength = encodedBytes.length + serializeTable.length;
                byte[] summaryBytes = new byte[summarylength];
                System.arraycopy(serializeTable, 0, summaryBytes, 0, serializeTable.length);
                System.arraycopy(encodedBytes, 0, summaryBytes, serializeTable.length, encodedBytes.length);

                FileWork.Data dataD = FileWork.getDataBytes(summaryBytes);
                String binary  = FileWork.getBinaryDataString(dataD);
                System.out.println("1 = "+codeData);
                System.out.println("2 = "+binary);


                FileWork.writeFile(summaryBytes, file.getAbsolutePath(), COMPRESS_OPTION);

            } else if (option.equals(DECOMPRESS_OPTION)) {
                //////
                System.out.println("decompress");
//                Decompressor decompressor = Decompressor.instance();
//                byte[] bytes = FileWork.readFileBytes(file);
//                byte[] tableBytes = FileWork.getTableBytes(bytes);
//                byte[] dataBytes = FileWork.getDataBytes(bytes);
//
//                Map<Character, String> table = decompressor.decompressTable(tableBytes);
//                String binaryString = new String(dataBytes);
//                String decodedData = decompressor.decode(binaryString, table);
//                FileWork.writeFile(decodedData, file.getAbsolutePath(), DECOMPRESS_OPTION);


            } else throw new UnexpectedOptions("This options wrong");
        }
    }
}