package hw6;

import utility.IntList;
import utility.MyScanner;

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

public class WsppSecondG {
    public static void main(String[] args) {
        Map<String, IntList> mp = new LinkedHashMap<>();
        int wordCnt = 0;
        PartOfWord checker = new PartOfWord();

        try {
            MyScanner in = new MyScanner(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
                )
            );

            try {
                String savedWord = null;

                while (true) {
                    Map<String, Integer> lineMp = new HashMap<>();

                    in.resetLFFlag();
                    boolean wasWord = false;

                    while (true) {
                        String word = null;
                        if (savedWord != null) {
                            word = savedWord;
                            savedWord = null;
                        } else {
                            word = in.next(checker);
                        }

                        if (word != null) {
                            wasWord = true;
                            if (!in.getLFFlag()) {
                                word = word.toLowerCase();
                                int cnt = lineMp.computeIfAbsent(word, k -> 0) + 1;
                                lineMp.put(word, cnt);

                                wordCnt++;
                                mp.computeIfAbsent(word, k -> new IntList()).add(wordCnt * (cnt % 2 - 1));
                            } else {
                                savedWord = word;
                                break;
                            }
                        } else {
                            break;
                        }
                    }

                    if (!wasWord) {
                        break;
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
