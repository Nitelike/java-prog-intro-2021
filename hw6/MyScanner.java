import java.io.Reader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.IOException;

public class MyScanner {
    private final int BUFFER_SIZE = 1024;
    private char[] buffer = new char[BUFFER_SIZE];
    private int buffered = 0;
    private int ptr = 0;
    private boolean skipLF = false;
    private boolean wasLF = false;  // stores information about LF only between words
    private NotWhitespace part = new NotWhitespace();
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

    private boolean isLF(char c) {
        if ((c == '\n') || (c == '\r')) {
            if (!(skipLF && (c == '\n'))) {
                skipLF = (c == '\r');
                wasLF = true;
                return true;
            }
            skipLF = false;
        }
        return false;
    }

    public void resetLFFlag() {
        wasLF = false;
    }

    public boolean getLFFlag() {
        return wasLF;
    }

    public String nextLine() throws IOException {
        String res = null;
        StringBuilder sb = new StringBuilder();

        while (true) {
            int x = nextChar();
            char c = (char)x;
            if (x == -1) {
                break;
            } else if (isLF(c)) {
                res = sb.toString();
                break;
            } else {
                sb.append(c);
            }
        }

        return res;
    }

    public String next() throws IOException {
        return next(part);
    }

    public String next(Checker chk) throws IOException {
        StringBuilder sb = new StringBuilder();
        boolean wasNotDelimiter = false;
        
        while (true) {
            int x = nextChar();
            char c = (char)x;
            if (x == -1) {
                break;
            } else if (chk.partOfWord(c)) {
                wasNotDelimiter = true;
                sb.append(c);
            } else if (wasNotDelimiter) {
                ptr--;
                break;
            }
            isLF(c);
        }

        if (sb.length() > 0) {
            return sb.toString();
        }

        return null;
    }

    public void close() throws IOException {
        in.close();
    }
}

class NotWhitespace implements Checker {
    @Override
    public boolean partOfWord(char c) {
        return !Character.isWhitespace(c);
    }
}