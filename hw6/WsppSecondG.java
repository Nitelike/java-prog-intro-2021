import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.HashMap;
import java.util.Map;
import homework.IntList;
import homework.MyScanner;
import homework.Checker;

public class WsppSecondG {
    public static void main(String[] args) {
        LinkedHashMap<String, IntList> mp = new LinkedHashMap<>();
        StringBuilder sb = new StringBuilder();
        int wordCnt = 0;

        try {
            MyScanner in = new MyScanner(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
                )
            );

            try {
                while (true) {
                    String line = in.nextLine();
                    if (line == null) {
                        break;
                    }
                    HashMap<String, Integer> lineMp = new HashMap<>();

                    MyScanner lineScanner = new MyScanner(line);

                    try {
                        while (true) {
                            String word = lineScanner.next(new PartOfWord());

                            if (word == null) {
                                break;
                            }

                            word = word.toLowerCase();
                            lineMp.computeIfAbsent(word, k -> 0);
                            int cnt = lineMp.get(word) + 1;
                            lineMp.put(word, cnt);

                            wordCnt++;
                            mp.computeIfAbsent(word, k -> new IntList()).add(wordCnt * (cnt % 2 - 1));
                        }
                    } finally {
                        lineScanner.close();
                    }
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't find input file: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Can't read input file: " + e.getMessage());
            return;
        }

        try {
            BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(args[1]),
                    StandardCharsets.UTF_8
                )
            );

            try {
                for (Map.Entry<String, IntList> entry : mp.entrySet()) {
                    IntList cur = entry.getValue();
                    out.write(entry.getKey() + " " + cur.length());
                    for (int i = 0; i < cur.length(); i++) {
                        if (cur.get(i) < 0) {
                            out.write(" " + -cur.get(i));
                        }
                    }
                    out.newLine();
                }
                
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't find output file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Can't write to output file: " + e.getMessage());
        }
    }

    private static boolean canBeInWord(char c) {
        return (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
    }
}

class PartOfWord implements Checker {
    @Override
    public boolean partOfWord(char c) {
        return (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
    }
}