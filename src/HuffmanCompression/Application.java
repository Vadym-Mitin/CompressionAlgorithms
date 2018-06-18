package HuffmanCompression;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Map;


public class Application {
    private static final String COMPRESS_OPTION = "--compress";
    private static final String DECOMPRESS_OPTION = "--decompress";

    public static void main(String[] args) throws UnexpectedFileFormat, UnexpectedOptions, IOException, ClassNotFoundException, NotFindCodeInTableException {

        if (args.length > 1) {
            String option = args[0];
            File file = new File(args[1]);
            if (option.equals(COMPRESS_OPTION)) {
                Compressor compressor = Compressor.instance();
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

                byte[] encodedBytes = compressor.encodeToByte2(resultData);

                byte[] serializeTable = SerializeHuffmanTable.serializeToByteArray(table);

                FileWork.writeEncodedData(file, encodedBytes);
                FileWork.writeMeta(file, serializeTable);

            } else if (option.equals(DECOMPRESS_OPTION)) {

                List<byte[]> list = FileWork.readCompressedFiles(file);
                byte[] tableData = list.get(0);
                byte[] dataData = list.get(1);

                Map<Character, String> table = SerializeHuffmanTable.deserializeFromByteArray(tableData);

                String binaryString = new String(dataData);

                Decompressor decompressor = Decompressor.instance();
                String decode = decompressor.decode(binaryString, table);

                FileWork.writeFile(decode.getBytes(), file.getAbsolutePath(), DECOMPRESS_OPTION);

            } else throw new UnexpectedOptions("This options wrong");
        }
    }
}