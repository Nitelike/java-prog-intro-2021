package homework;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.FileReader;
import java.io.StringReader;
import java.io.File;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import homework.CoolArray;
import java.util.function.Predicate;
import java.nio.charset.StandardCharsets;

public class MyScanner {
    private final int BUFFER_SIZE = 1024;
    private char[] buffer = new char[BUFFER_SIZE];
    private int bufferedChars = 0;
    private int ptr = 0;
    private Reader in;

    public MyScanner(InputStream source) {
        in = new InputStreamReader(source);
    }

    public MyScanner(File source) throws FileNotFoundException, IOException {
        in = new FileReader(source, StandardCharsets.UTF_8);
    }

    public MyScanner(String source) {
        in = new StringReader(source);
    }

    public int nextChar() throws IOException {
        if (ptr >= bufferedChars && bufferedChars >= 0) {
            bufferedChars = in.read(buffer);
            ptr = 0;
        }
        if (ptr < bufferedChars) {
            return (int)buffer[ptr++];
        }
        return -1;
    }

    public String nextLine() throws IOException {
        String res = null;
        CoolArray buf = new CoolArray();
        int x = nextChar();

        while (true) {
            if (x == -1) {
                break;
            }
            char c = (char)x;
            res = "";
            if (c != '\n' && c != '\r') {
                buf.add(c);
            } else if (c == '\n') {
                break;
            }
            x = nextChar();
        }

        if (buf.length() > 0) {
            res = buf.toString();
        }

        return res;
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