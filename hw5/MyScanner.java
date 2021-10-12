package homework;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.InputStream;
import java.io.IOException;
import java.util.function.Predicate;

public class MyScanner {
    private BufferedReader in;

    public MyScanner(InputStream source) throws IOException {
        in = new BufferedReader(
            new InputStreamReader(source, "utf8")
        );
    }

    public MyScanner(String source) {
        in = new BufferedReader(
            new StringReader(source)
        );
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
        StringBuilder sb = new StringBuilder();
        boolean wasNotDelimiter = false;
        int c = nextChar();

        while (c != -1) {
            if (notDelimiter.test((char)c)) {
                wasNotDelimiter = true;
                sb.append((char)c);
            } else if (wasNotDelimiter) {
                break;
            }
            c = nextChar();
        }

        if (sb.length() > 0) {
            res = sb.toString();
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