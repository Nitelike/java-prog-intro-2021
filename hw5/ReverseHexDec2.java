import utility.MyScanner;

import java.util.ArrayList;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;

public class ReverseHexDec2 {
    public static void main(String[] args) {
        ArrayList<String> ans = new ArrayList<>();

        try {
            MyScanner in = new MyScanner(
                new InputStreamReader(
                    System.in
                )
            );

            try {
                while (true) {
                    String line = in.nextLine();
                    if (line == null) {
                        break;
                    }

                    MyScanner lineScanner = new MyScanner(line);
                    ArrayList<String> buf = new ArrayList<>();
                    StringBuilder sb = new StringBuilder();

                    try {
                        while (true) {
                            String number = lineScanner.next();
                            if (number == null) {
                                break;
                            }
                            if (!isHex(number)) {
                                number = "0x" + Integer.toHexString(Integer.parseInt(number));
                            }
                            buf.add(number);
                        }
                    } finally {
                        lineScanner.close();
                    }

                    for (int i = buf.size() - 1; i >= 0; i--) {
                        sb.append(buf.get(i) + " ");
                    }

                    ans.add(sb.toString());
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            System.err.println("Can't read from System in: " + e.getMessage());
            return;
        }

        try {
            BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                    System.out
                )
            );

            try {
                for (int i = ans.size() - 1; i >= 0; i--) {
                    out.write(ans.get(i));
                    out.newLine();
                }
                        
            } finally {
                out.close();
            }
        } catch (IOException e) {
            System.err.println("Can't write to output file: " + e.getMessage());
        }
    }

    private static boolean isHex(String num) {
        return (num.startsWith("0x") || num.startsWith("0X"));
    }
}
