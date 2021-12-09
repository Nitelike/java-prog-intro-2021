import java.io.Reader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.IOException;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;

public class ProblemH {
    public static void main(String[] args) {
        int n, q;
        int max = 0;
        int[] a;
        int[] t;
        int[] prefSum;
        int[] mem;

        try {
            MyScanner sc = new MyScanner(new InputStreamReader(System.in));

            try {
                n = sc.nextInt();
                a = new int[n];
                prefSum = new int[n];

                for (int i = 0; i < n; i++) {
                    a[i] = sc.nextInt();
                    prefSum[i] = (i > 0 ? prefSum[i-1] : 0) + a[i];
                    max = Math.max(max, a[i]);
                }

                q = sc.nextInt();
                t = new int[q];

                for (int i = 0; i < q; i++) {
                    t[i] = sc.nextInt();
                }
            } finally {
                sc.close();
            }
        } catch (IOException e) {
            System.err.println("Can't read from System.in: " + e.getMessage());
            return;
        }

        mem = new int[prefSum[n-1] + 1];

        try {
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));

            try {
                for (int i = 0; i < q; i++) {
                    if (t[i] < max) {
                        out.write("Impossible");
                    } else if (mem[t[i]] > 0) {
                        out.write(Integer.toString(mem[t[i]]));
                    } else {
                        int ans = 0, l = -1, bufSum = 0;

                        while (l < n - 1) {
                            ans++;
                            int ind = upperBound(prefSum, l+1, n-1, bufSum + t[i]) - 1;
                            bufSum = prefSum[ind];
                            l = ind;
                        }

                        mem[t[i]] = ans;
                        out.write(Integer.toString(ans));
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

    public static int upperBound(int[] a, int l, int r, int x) {
        int ans = r + 1;

        while (l <= r) {
            int m = (l + r) / 2;
            if (a[m] < x) {
                l = m + 1;
            } else if (a[m] > x) {
                ans = m;
                r = m - 1;
            } else {
                l = m + 1;
                ans = m;
            }
        }

        if (ans <= r && a[ans] == x) {
            ans++;
        }

        return ans;
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