package HuffmanCompression;

import java.math.BigInteger;

public class StringWork {
    public static void main(String[] args) {
        byte[] bytes = new byte[]{1, 2, 3};


        byte b = 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <bytes.length; i++) {
            b = bytes[i];

            for (int j = 7; j >= 0; --j) {
                sb.append(b >>> j & 1);
            }
        }
            System.out.println(sb.toString());


//        // Create a BigInteger using the byte array
//        BigInteger bi = new BigInteger(bytes);
//
//        String s = bi.toString(2);
//        System.out.println(s);
        }
    }
