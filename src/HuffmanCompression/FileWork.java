package HuffmanCompression;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileWork {
    private static final String COMPRESS = ".compressed";
    private static final String COMPRESSARG = "--compress"; // Раздели COMPRESS и ARG знаком подчеркивания.
    private static final String DECOMPRESSARG = "--decompress";
    private static final String DECOMPRESS = ".decompressed";

    public static void writeData(String data, String path, String arg) throws IOException {
        File file = new File(path);
        byte[] bytes = data.getBytes();

        if (arg.equals(COMPRESSARG)) { // Правильней будет делать COMPRESSARG.equals(arg), чтобы избежать возможного NPE
            File compressed = new File(file.getAbsolutePath() + COMPRESS);
            compressed.createNewFile();
            Files.write(Paths.get(compressed.getAbsolutePath()), bytes);

        }if (arg.equals(DECOMPRESSARG)) { // Форматирование кода.
            File decompressed = new File(file.getAbsolutePath() + DECOMPRESS);
            decompressed.createNewFile();
            Files.write(Paths.get(decompressed.getAbsolutePath()), bytes);
        }
    }

    public static List<String> readFile(File file) throws IOException {
        List<String> string = Files.readAllLines(Paths.get(file.getAbsolutePath()));
        return string;
    }
}
