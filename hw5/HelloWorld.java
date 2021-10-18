import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;

public class HelloWorld {
    public static void main(String[] args) {
        StringBuilder sx = new StringBuilder();
        System.out.println(sx.toString());

        /*try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
                )
            );

            String all = "";

            try {
                while (true) {
                    String s = in.readLine();
                    if (s == null) {
                        break;
                    }
                    all += s;
                }
            } finally {
                in.close();
            }

            System.out.print(all);

            for (int i = 0; i < all.length(); i++) {
                if (all.charAt(i) == '\r') {
                    System.out.println(i);
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Cannot find file");
        } catch (IOException e) {
            System.err.println("Cannot read file");
        }*/
    }
}