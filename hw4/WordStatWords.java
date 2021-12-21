package hw4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.TreeMap;
import java.util.Map;
import java.nio.charset.StandardCharsets;

public class WordStatWords {
    public static void main(String[] args) {
        Map<String, Integer> mp = new TreeMap<>();
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
                )
            );
            
            try {
                while (true) { 
                    int x = in.read();
                    char c = (char)x;
                    if (x != -1 && canBeInWord(c)) {
                        sb.append(Character.toLowerCase(c));
                    } else if (sb.length() > 0) {
                        int cnt = 1;
                        String word = sb.toString();

                        if (mp.get(word) != null) {
                            cnt += mp.get(word);
                        }

                        mp.put(word, cnt);

                        sb = new StringBuilder();
                    }
                    if (x == -1) {
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
                for (Map.Entry<String, Integer> entry : mp.entrySet()) {
                    out.write(entry.getKey() + " " + entry.getValue());
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
