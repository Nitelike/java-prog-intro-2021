import homework.CoolIntArray;
import homework.MyScanner;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class Reverse {
    public static void main(String[] args) {
        ArrayList<String> strings = new ArrayList<String>();

        try {
            MyScanner in = new MyScanner(System.in);

            try {
                while (true) {
                    String line = in.nextLine();
                    if (line == null) {
                        break;
                    }
                    strings.add(line);
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            System.err.println("Can't read from System in");
        }

        for (int i = strings.size() - 1; i >= 0; i--) {
            CoolIntArray ints = new CoolIntArray();

            try {
                MyScanner line = new MyScanner(strings.get(i));

                try {
                    while (true) {
                        String nxt = line.next();
                        if (nxt == null) {
                            break;
                        }
                        ints.add(Integer.parseInt(nxt));
                    }

                    for (int j = ints.length() - 1; j >= 0; j--) {
                        System.out.print(ints.get(j) + " ");
                    }

                    System.out.println();
                } finally {
                    line.close();
                }
            } catch (IOException e) {
                System.err.println("Can't read line");
            }
        }   
    }
}