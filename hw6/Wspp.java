import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;
import homework.IntList;

public class Wspp {
    public static void main(String[] args) {
        LinkedHashMap<String, IntList> mp = new LinkedHashMap<>();
        StringBuilder sb = new StringBuilder();
        int wordCnt = 0;

        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
                )
            );
            
            try {
                while (true) { 
                    int x = in.read();
                    char c = (char)x;
                    if (x != -1 && canBeInWord(c)) {
                        sb.append(Character.toLowerCase(c));
                    } else if (sb.length() > 0) {
                        String word = sb.toString();
                        mp.computeIfAbsent(word, k -> new IntList()).add(++wordCnt);
                        sb = new StringBuilder();
                    }
                    if (x == -1) {
                        break;
                    }
                }

            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't find input file: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Can't read input file: " + e.getMessage());
            return;
        }

        try {
            BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    StandardCharsets.UTF_8
                )
            );

            try {
                for (Map.Entry<String, IntList> entry : mp.entrySet()) {
                    IntList cur = entry.getValue();
                    out.write(entry.getKey() + " " + cur.length());
                    for (int i = 0; i < cur.length(); i++) {
                        out.write(" " + cur.get(i));
                    }
                    out.newLine();
                }
                
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't find output file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Can't write to output file: " + e.getMessage());
        }
    }

    private static boolean canBeInWord(char c) {
        return (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
    }
}