import java.io.Reader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

import java.util.Stack;

public class ProblemK {
    private static int n, m;
    private static char[][] c;
    private static int aX, aY;

    public static void main(String[] args) {
        try {
            MyScanner sc = new MyScanner(new InputStreamReader(System.in));

            try {
                n = sc.nextInt();
                m = sc.nextInt();
                c = new char[n][m];

                for (int i = 0; i < n; i++) {
                    String s = sc.next();
                    for (int j = 0; j < m; j++) {
                        c[i][j] = s.charAt(j);
                        if (c[i][j] == 'A') {
                            aX = i;
                            aY = j;
                        }
                    }
                }
            } finally {
                sc.close();
            }
        } catch(IOException e) {
            System.err.println("Can't read System.in: " + e.getMessage());
        }

        int[] topDots = new int[m];
        int[][] h = new int[n][m];
        int[][] w = new int[n][m];

        for (int i = 0; i < n; i++) {
            Stack<Integer> q = new Stack<>();
            int[] closestLessLeft = new int[m];
            int[] closestLessRight = new int[m];

            for (int j = 0; j < m; j++) {
                topDots[j] = (c[i][j] == '.' || c[i][j] == 'A' ? topDots[j] + 1 : 0);
            }

            for (int j = 0; j < m; j++) {
                while (!q.empty() && topDots[q.peek()] >= topDots[j]) {
                    q.pop();
                }
                closestLessLeft[j] = (q.empty() ? -1 : q.peek());
                q.push(j);
            }

            q.clear();

            for (int j = m-1; j >= 0; j--) {
                while (!q.empty() && topDots[q.peek()] >= topDots[j]) {
                    q.pop();
                }
                closestLessRight[j] = (q.empty() ? m : q.peek());
                q.push(j);
            }

            for (int j = 0; j < m; j++) {
                int l = closestLessLeft[j] + 1;
                int r = closestLessRight[j] - 1;

                if (contains(aX, aY, i - topDots[j] + 1, l, i, r)) {
                    if (w[i][r] * h[i][r] < (r-l+1) * topDots[j]) {
                        w[i][r] = (r-l+1);
                        h[i][r] = topDots[j];
                    }
                }
            }
        }

        int l = aX, r = aY;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (w[l][r] * h[l][r] < w[i][j] * h[i][j]) {
                    l = i;
                    r = j;
                }
            }
        }

        int x1 = l - h[l][r] + 1, y1 = r - w[l][r] + 1, x2 = l, y2 = r;

        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (c[i][j] == '.') {
                    c[i][j] = 'a';
                }
            }
        }

        fill(0, 0, x1-1, m-1);
        fill(x2+1, 0, n-1, m-1);
        fill(x1, 0, x2, y1-1);
        fill(x1, y2+1, x2, m-1);

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

            try {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        out.write(c[i][j]);
                    }
                    out.write("\n");
                }
            } finally {
                out.close();
            }
        } catch (IOException e) {
            System.err.println("Can't write to System.out: " + e.getMessage());
        }
    }

    public static boolean contains(int x, int y, int x1, int y1, int x2, int y2) {
        return (Math.min(x1, x2) <= x && x <= Math.max(x1, x2) && Math.min(y1, y2) <= y && y <= Math.max(y1, y2));
    }

    public static void fill(int x1, int y1, int x2, int y2) {
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (Character.isLetter(c[i][j])) {
                    char fillWith = Character.toLowerCase(c[i][j]);
                    int k = i+1;
                    while (k <= x2 && c[k][j] == '.') {
                        c[k++][j] = fillWith;
                    }
                    k = i-1;
                    while (k >= x1 && c[k][j] == '.') {
                        c[k--][j] = fillWith;
                    }
                }
            }
        }
        for (int i = x1; i <= x2; i++) {
            for (int j = y1; j <= y2; j++) {
                if (Character.isLetter(c[i][j])) {
                    char fillWith = Character.toLowerCase(c[i][j]);
                    int k = j+1;
                    while (k <= y2 && c[i][k] == '.') {
                        c[i][k++] = fillWith;
                    }
                    k = j-1;
                    while (k >= y1 && c[i][k] == '.') {
                        c[i][k--] = fillWith;
                    }
                }
            }
        }
    }
}

class MyScanner {
    private final int BUFFER_SIZE = 1024;
    private char[] buffer = new char[BUFFER_SIZE];
    private int buffered = 0;
    private int ptr = 0;
    private boolean skipLF = false;
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
        return next(part);
    }
 
    public String next(Checker chk) throws IOException {
        StringBuilder sb = new StringBuilder();
        boolean wasNotDelimiter = false;
        
        while (true) {
            int x = nextChar();
            if (x == -1) {
                break;
            } else if (chk.partOfWord((char)x)) {
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
 
    public int nextInt() throws IOException {
        return Integer.parseInt(next(part));
    }
 
    public int nextInt(Checker chk) throws IOException {
        return Integer.parseInt(next(chk));
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
 
interface Checker {
    boolean partOfWord(char c);
}