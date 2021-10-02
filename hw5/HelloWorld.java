import homework.MyScanner;

import java.io.FileNotFoundException;
import java.io.IOException;

public class HelloWorld {
    public static void main(String[] args) {

        try {
            MyScanner in = new MyScanner();

            try {
                while (in.hasNext()) {
                    String a = in.next();

                    System.out.println(a);
                }
            } finally {
                in.close();
            }

        } catch (FileNotFoundException e) {
            System.out.println("Cannot find file");
        } catch (IOException e) {
            System.out.println("Cannot read file");
        }
        
    }
}