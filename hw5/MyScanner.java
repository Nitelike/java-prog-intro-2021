package homework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import homework.CoolArray;
import java.util.function.Predicate;

public class MyScanner {
    private BufferedReader in;

    public MyScanner(InputStream source) {
        in = new BufferedReader( new InputStreamReader(source));
    }

    public MyScanner(File source) throws FileNotFoundException, IOException {
        in = new BufferedReader(new FileReader(source, StandardCharsets.UTF_8));
    }

    public MyScanner(String source) {
        in = new BufferedReader(new StringReader(source));
    }

    public int nextChar() throws IOException {
        return in.read();
    }

    public String nextLine() throws IOException {
        return in.readLine();
    }

    public String next() throws IOException {
        return next(notWhitespace());
    }

    public String next(Predicate<Character> notDelimiter) throws IOException {
        String res = null;
        CoolArray buf = new CoolArray();
        boolean wasNotDelimiter = false;
        int c = nextChar();

        while (c != -1) {
            if (notDelimiter.test((char)c)) {
                wasNotDelimiter = true;
                buf.add((char)c);
            } else if (wasNotDelimiter) {
                break;
            }
            c = nextChar();
        }

        if (buf.length() > 0) {
            res = buf.toString();
        }

        return res;
    }

    public void close() throws IOException {
        in.close();
    }

    private Predicate<Character> notWhitespace() {
        return c -> !Character.isWhitespace(c);
    }
}