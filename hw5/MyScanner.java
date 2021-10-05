package homework;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.StringReader;
import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.Predicate;
import java.nio.charset.StandardCharsets;

public class MyScanner {
    private final int BUFFER_SIZE = 1024;
    private char[] buffer = new char[BUFFER_SIZE];
    private int buffered = 0;
    private int ptr = 0;
    private Reader in;

    public MyScanner(InputStream source) {
        in = new InputStreamReader(source);
    }

    public MyScanner(FileInputStream source, String encoding) throws IOException {
        in = new InputStreamReader(source, encoding);
    }

    public MyScanner(String source) {
        in = new StringReader(source);
    }

    public int nextChar() throws IOException {
        if (ptr >= buffered && buffered >= 0) {
            buffered = in.read(buffer);
            ptr = 0;
        }
        if (ptr < buffered) {
            return (int)buffer[ptr++];
        }
        return -1;
    }

    public String nextLine() throws IOException {
        String res = null;
        StringBuilder sb = new StringBuilder();

        while (true) {
            int x = nextChar();
            if (x == -1) {
                break;
            }
            char c = (char)x;
            res = "";
            if (c != '\n' && c != '\r') {
                sb.append(c);
            } else if (c == '\n') {
                break;
            }
        }

        if (sb.length() > 0) {
            res = sb.toString();
        }

        return res;
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