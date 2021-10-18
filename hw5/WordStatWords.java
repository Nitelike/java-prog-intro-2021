import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.function.Predicate;
import homework.MyScanner;
import java.util.TreeMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;

public class WordStatWords {
    public static void main(String[] args) {
        TreeMap<String, Integer> mp = new TreeMap<>();

        try {
            MyScanner in = new MyScanner(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
                )
            );
            try {
                while (true) {
                    String word = in.next(partOfWord());
                    if (word == null) {
                        break;
                    }

                    int cnt = 1;
                    word = word.toLowerCase();

                    if (mp.containsKey(word)) {
                        cnt += mp.get(word);
                    }

                    mp.put(word, cnt);
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
                for (Map.Entry<String, Integer> entry : mp.entrySet()) {
                    out.write(entry.getKey() + " " + entry.getValue());
                    out.newLine();
                }
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't find output file: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Can't write to output file: " + e.getMessage());
            return;
        }
    }

    private static Predicate<Character> partOfWord() {
        return c -> (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
    }
}