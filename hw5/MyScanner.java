package homework;

import java.io.Reader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.IOException;
import java.util.function.Predicate;

public class MyScanner {
    private final int BUFFER_SIZE = 1024;
    private char[] buffer = new char[BUFFER_SIZE];
    private int buffered = 0;
    private int ptr = 0;
    private boolean skipLF = false;
    private Reader in;

    public MyScanner(InputStreamReader source) {
        in = source;
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
            char c = (char)x;
            if (x == -1) {
                break;
            } else if ((c == '\n') || (c == '\r')) {
                if (skipLF && (c == '\n')) {
                    skipLF = false;
                    continue;
                } else {
                    res = sb.toString();
                }
                skipLF = (c == '\r');
                break;
            } else {
                sb.append(c);
            }
        }

        return res;
    }

    public String next() throws IOException {
        return next(notWhitespace());
    }

    public String next(Predicate<Character> notDelimiter) throws IOException {
        StringBuilder sb = new StringBuilder();
        boolean wasNotDelimiter = false;
        
        while (true) {
            int x = nextChar();
            if (x == -1) {
                break;
            } else if (notDelimiter.test((char)x)) {
                wasNotDelimiter = true;
                sb.append((char)x);
            } else if (wasNotDelimiter) {
                break;
            }
        }

        if (sb.length() > 0) {
            return sb.toString();
        }

        return null;
    }

    public void close() throws IOException {
        in.close();
    }

    private Predicate<Character> notWhitespace() {
        return c -> !Character.isWhitespace(c);
    }
}