import homework.MyScanner;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

public class ReverseHexDec2 {
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
            System.err.println("Can't read from System in: " + e.getMessage());
            return;
        }

        for (int i = strings.size() - 1; i >= 0; i--) {
            ArrayList<String> numbers = new ArrayList<String>();

            try {
                MyScanner line = new MyScanner(strings.get(i));

                try {
                    while (true) {
                        String nxt = line.next();
                        if (nxt == null) {
                            break;
                        }
                        if (!isHex(nxt)) {
                            nxt = "0x" + Integer.toHexString(Integer.parseInt(nxt));
                        }
                        numbers.add(nxt);
                    }

                    for (int j = numbers.size() - 1; j >= 0; j--) {
                        System.out.print(numbers.get(j) + " ");
                    }

                    System.out.println();
                } finally {
                    line.close();
                }
            } catch (IOException e) {
                System.err.println("Can't read line: " + e.getMessage());
                return;
            }
        }   
    }

    private static boolean isHex(String num) {
        return (num.startsWith("0x") || num.startsWith("0X"));
    }
}