import homework.MyScanner;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.File;

public class HelloWorld {
    public static void main(String[] args) {
        try {
            MyScanner in = new MyScanner(new File(args[0]));

            try {
                String str = in.nextLine();

                while(str != null) {
                    System.out.println(str);
                    str = in.nextLine();
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