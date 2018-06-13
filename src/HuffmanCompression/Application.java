package HuffmanCompression;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Application {
    private static File file;
    private static File compressedFile;

    public static void main(String[] args) {

        String message = null;
        List<String> data = null;
        if (args[0].equals("--compress")) {
            compressedFile = new File(args[1]);
            Compressor compressor = Compressor.instance();
            try {
                StringBuffer sb = new StringBuffer();
                data = FileWork.readFile(compressedFile);
                int i = data.size();
                for (String string : data) {
                    if (i==1) {
                        sb.append(string);
                        break;
                    }
                    sb.append(string);
                    i--;
                }
                message = sb.toString();
//                System.out.println(message);
                String encoded = compressor.encode(message);
                FileWork.writeData(encoded, compressedFile.getPath(), args[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (args[0].equals("--decompress")) {
            file = new File(args[1]);
            Decompressor decompressor = Decompressor.instance();
            try {
                data = FileWork.readFile(file);
                int i = data.size();
                String encodedTable = null;
                String encodedData = null;
                StringBuffer sb = new StringBuffer();
                for (String s : data) {
                    if (i == 1) {
                        encodedData = s;
                        encodedTable = sb.toString();
                        break;
                    }
                    sb.append(s);
                    sb.append("\n");
                    i--;
                }
                System.out.println(encodedData);
                System.out.println(encodedTable);
                Map<Character, String> decodedTable = decompressor.deco0mpressTable(encodedTable);
                String decodedData = decompressor.decode(encodedData, decodedTable);
//                System.out.println(decodedData);
                FileWork.writeData(decodedData, file.getPath(), args[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
