package HuffmanCompression; // Неправильно назван пакет. https://docs.oracle.com/javase/tutorial/java/package/namingpkgs.html

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;


public class Application {
    private static File file;
    private static File compressedFile; // Зачем делать статическое поле, если это локальная переменная для метода?

    public static void main(String[] args) {

        String message = null; // Необязательно писать "= null", идея подсказывает это.
        List<String> data = null; // Почему бы не объявить переменную прямо там, где она используется?

        if (args[0].equals("--compress")) { // Правильней будет делать "--compress".equals(args[0]), чтобы избежать возможного NPE
            // --compress стоило бы вынести в константу.
            compressedFile = new File(args[1]);
            Compressor compressor = Compressor.instance();
            try {
                StringBuffer sb = new StringBuffer();
                data = FileWork.readFile(compressedFile);
                int i = data.size(); // Очень плохое имя переменной, непонятно, что она делает.
                for (String string : data) { // string -- тоже плохое название переменной.
                    if (i==1) { // Пожалуйста, отформатируй код.
                        sb.append(string);
                        break;
                    }
                    sb.append(string);
                    i--;
                }
                message = sb.toString();
//                System.out.println(message); // Такие штуки не должны попадать в репозитории проектов.
                String encoded = compressor.encode(message);
                FileWork.writeData(encoded, compressedFile.getPath(), args[0]);
                // args[0] используется два раза, вынеси его в переменную с понятным именем.
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // К нижней половине те же замечания, что и к верхней.
        if (args[0].equals("--decompress")) { // Тоже самое, что выше.
            file = new File(args[1]);
            Decompressor decompressor = Decompressor.instance();
            try {
                data = FileWork.readFile(file);
                int i = data.size(); //
                String encodedTable = null; // Почему бы не сделать = "" и избежать использования null?
                String encodedData = null;
                StringBuffer sb = new StringBuffer();
                for (String s : data) {
                    if (i == 1) { // Плохое имя переменной.
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
