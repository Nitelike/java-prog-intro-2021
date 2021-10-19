import homework.MyScanner;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;
import homework.Checker;

public class WordStatWords {
    public static void main(String[] args) {
        TreeMap<String, Integer> mp = new TreeMap<>();
        StringBuilder sb = new StringBuilder();
        PartOfWord part = new PartOfWord();

        try {
            MyScanner in = new MyScanner(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
                )
            );
            
            try {
                while (true) { 
                    String word = in.next(part);
                    if (word == null) {
                        break;
                    }
                    word = word.toLowerCase();
                    int cnt = 1;
                    if (mp.get(word) != null) {
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
        } catch (IOException e) {
            System.err.println("Can't write to output file: " + e.getMessage());
        }
    }
}

class PartOfWord implements Checker {
    @Override
    public boolean partOfWord(char c) {
        return (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
    }
}