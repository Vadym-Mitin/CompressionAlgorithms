package HuffmanCompression;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static HuffmanCompression.SerializeHuffmanTable.deserializeFromByteArray;
import static HuffmanCompression.SerializeHuffmanTable.serializeToByteArray;

public class FileWork {
    private static final String COMPRESS_EXTENSION = ".compressed";
    private static final String DECOMPRESS_EXTENSION = ".decompressed";
    private static final String COMPRESS_OPTION = "--compress";
    private static final String DECOMPRESS_OPTION = "--decompress";

    private static String getFileExtension(File file) {
        String name = file.getName();
        try {
            return name.substring(name.lastIndexOf("."));
        } catch (Exception e) {
            return "";
        }
    }

    private static void writeData(File file, byte[] dataBytes, String extension) throws IOException {
        File compressed = new File(file.getAbsolutePath() + extension);
        compressed.createNewFile();
        Path compressedPath = Paths.get(compressed.getAbsolutePath());
        Files.write(compressedPath, dataBytes);
    }

    public static void writeEncodedData(File file, byte[] dataBytes) throws IOException {
        File compressed = new File(file.getAbsolutePath() + COMPRESS_EXTENSION);
        compressed.createNewFile();
        Path compressedPath = Paths.get(compressed.getAbsolutePath());
        Files.write(compressedPath, dataBytes);
    }

    public static void writeMeta(File file, byte[] dataBytes) throws IOException {
        File compressed = new File(file.getAbsolutePath() + COMPRESS_EXTENSION + ".meta");
        compressed.createNewFile();
        Path compressedPath = Paths.get(compressed.getAbsolutePath());
        Files.write(compressedPath, dataBytes);
    }

    public static void writeFile(byte[] data, String path, String arg) throws IOException, UnexpectedFileFormat {
        File file = new File(path);

        if (arg.equals(COMPRESS_OPTION)) {
            writeData(file, data, COMPRESS_EXTENSION);
        }

        if (arg.equals(DECOMPRESS_OPTION)) {
            if (!getFileExtension(file).equals(COMPRESS_EXTENSION)) {
                throw new UnexpectedFileFormat("File format is unexpected");
            }
            writeData(file, data, DECOMPRESS_EXTENSION);
        }
    }

    public static List<String> readFileStrings(File file) throws IOException {
        List<String> string = Files.readAllLines(Paths.get(file.getAbsolutePath()));
        return string;
    }

    public static byte[] readFileBytes(File file) throws IOException {
        return Files.readAllBytes(Paths.get(file.getAbsolutePath()));
    }

    public static List<byte[]> readCompressedFiles(File file) throws UnexpectedFileFormat, IOException {
        List<byte[]> list = new ArrayList<>();
        File tableFile = new File(file.getAbsolutePath() + ".meta");
        File dataFile = new File(file.getAbsolutePath());
        if (!dataFile.exists() || !tableFile.exists()) {
            throw new UnexpectedFileFormat("cant find table or meta file");
        }
        byte[] tableDytes = Files.readAllBytes(Paths.get(tableFile.getAbsolutePath()));
        byte[] dataBytes = Files.readAllBytes(Paths.get(dataFile.getAbsolutePath()));
        list.add(tableDytes);
        list.add(dataBytes);

        return list;
    }

}
