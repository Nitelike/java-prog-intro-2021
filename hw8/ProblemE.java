import java.io.Reader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.IOException;

import java.util.Arrays;

public class ProblemE {
    private static IntList[] g;
    private static int[] deep;

    public static void main(String[] args) {
        int n, m;
        int[] teams;

        try {
            MyScanner sc = new MyScanner(new InputStreamReader(System.in));

            try {
                n = sc.nextInt();
                m = sc.nextInt();
                g = new IntList[n+1];
                deep = new int[n+1];
                teams = new int[m];

                for (int i = 0; i < n+1; i++) {
                    g[i] = new IntList();
                }

                for (int i = 1; i < n; i++) {
                    int a = sc.nextInt(), b = sc.nextInt();
                    g[a].add(b);
                    g[b].add(a);
                }

                for (int i = 0; i < m; i++) {
                    teams[i] = sc.nextInt();
                }
            } finally {
                sc.close();
            }
        } catch (IOException e) {
            System.err.println("Can't read System in: " + e.getMessage());
            return;
        }        

        int root = teams[0];
        dfs(root, 0);

        int deepestTeam = 0;
        for (int i = 0; i < m; i++) {
            if (deep[teams[i]] > deep[deepestTeam]) {
                deepestTeam = teams[i];
            }
        }

        IntList possibleAns = new IntList();
        int needDeep = deep[deepestTeam] / 2;

        for (int i = 1; i <= n; i++) {
            if (deep[i] == needDeep) {
                possibleAns.add(i);
            }
        }

        deep[deepestTeam] = 0;
        dfs(deepestTeam, 0);
        int ansNode = 0;

        for (int i = 0; i < possibleAns.length(); i++) {
            int cand = possibleAns.get(i);
            if (deep[cand] == needDeep) {
                ansNode = cand;
                break;
            }
        }

        if (ansNode > 0) {
            deep[ansNode] = 0;
            dfs(ansNode, 0);

            for (int i = 0; i < m; i++) {
                if (deep[teams[i]] != needDeep) {
                    ansNode = 0;
                    break;
                }
            }
        }

        if (ansNode > 0) {
            System.out.println("YES\n" + ansNode);
        } else {
            System.out.println("NO");
        }
    }

    private static void dfs(int v, int p) {
        for (int i = 0; i < g[v].length(); i++) {
            int to = g[v].get(i);
            if (to != p) {
                deep[to] = deep[v] + 1;
                dfs(to, v);
            }
        }
    }
}

class IntList {
    private int[] arr = new int[0];
    private int length;

    public void add(int val) {
        if (length == arr.length) {
            arr = Arrays.copyOf(arr, (arr.length * 3 / 2) + 1);    
        }
        arr[length++] = val;
    }

    public int get(int ind) {
        return arr[ind];
    }

    public void set(int ind, int val) {
        arr[ind] = val;
    }

    public int length() {
        return length;
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