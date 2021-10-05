import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.function.Predicate;
import homework.MyScanner;

public class WordStatWords {
    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<String>();

        try {
            MyScanner in = new MyScanner(new FileInputStream(args[0]), "utf8");
            try {
                while (true) {
                    String word = in.next(partOfWord());
                    if (word == null) {
                        break;
                    }
                    input.add(word.toLowerCase());
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't find " + args[0]);
        } catch (IOException e) {
            System.err.println("Can't read from " + args[0]);
        }

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "utf8"));
            try {
                Collections.sort(input);
                for (int i = 0; i < input.size(); i++) {
                    int j = i;
                    while (j < input.size() && input.get(i).equals(input.get(j))) {
                        j++;
                    }
                    out.write(input.get(i) + " " + (j-i) + "\n");
                    i = j-1;
                }
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't find " + args[1]);
        } catch (IOException e) {
            System.err.println("Can't write to " + args[1]);
        }
    }

    private static Predicate<Character> partOfWord() {
        return c -> (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
    }
}