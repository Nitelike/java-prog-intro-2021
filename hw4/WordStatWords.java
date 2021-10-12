import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;

public class WordStatWords {
    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<String>();
        StringBuilder sb = new StringBuilder();

        try {
            BufferedReader in = new BufferedReader(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    "utf-8"
                )
            );
            
            try {
                int x = in.read();
                while (true) { 
                    char c = (char)x;
                    if (x != -1 && canBeInWord(c)) {
                        sb.append(Character.toLowerCase(c));
                    } else if (sb.length() > 0) {
                        input.add(sb.toString());
                        sb = new StringBuilder();
                    }
                    if (x == -1) {
                        break;
                    }
                    x = in.read();
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
                    "utf-8"
                )
            );

            try {
                Collections.sort(input);
                for (int i = 0; i < input.size(); i++) {
                    int j = i;
                    while (j < input.size() && input.get(i).equals(input.get(j))) {
                        j++;
                    }
                    out.write(input.get(i) + " " + (j-i));
                    out.newLine();
                    i = j-1;
                }
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't find output file: " + e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Can't write to output file: " + e.getMessage());
            return;
        }
    }

    private static boolean canBeInWord(char c) {
        return (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
    }
}