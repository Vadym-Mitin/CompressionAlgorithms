package com.myCompany.HuffmanCompression;

import java.io.File;
import java.io.IOException;


public class Application {
    private static final String COMPRESS_OPTION = "--Compress";
    private static final String DECOMPRESS_OPTION = "--Decompress";

    public static void main(String[] args) throws UnexpectedFileFormat, UnexpectedOptions, IOException, ClassNotFoundException, NotFindCodeInTableException {

        if (args.length > 1) {
            String option = args[0];
            File file = new File(args[1]);
            if (COMPRESS_OPTION.equals(option)) {
                Compressor compressor = Compressor.instance();
                compressor.Compress(file);
            } else if (DECOMPRESS_OPTION.equals(option)) {
                Decompressor decompressor = Decompressor.instance();
                decompressor.Decompress(file);
            }
        } else {
            System.out.println("Usage:     huffman --<switch> <files...>");
            System.out.println();
            System.out.println("<Switches>");
            System.out.println("Compress        compressed files");
            System.out.println("Decompress      decompressed files");
        }
    }
}