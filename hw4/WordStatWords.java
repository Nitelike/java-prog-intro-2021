import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.nio.charset.StandardCharsets;
import homework.CoolArray;

public class WordStatWords {
    public static void main(String[] args) {
        ArrayList<String> input = new ArrayList<String>();
        CoolArray str = new CoolArray();

        try {
            BufferedReader in = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8));
            
            try {
                int x = in.read();
                while (true) { 
                    char c = (char)x;
                    if (x != -1 && canBeInWord(c)) {
                        str.add(Character.toLowerCase(c));
                    } else if (str.length() > 0) {
                        input.add(str.toString());
                        str = new CoolArray();
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
            System.err.println("Can't find " + args[0]);
        } catch (IOException e) {
            System.err.println("Can't read from " + args[0]);
        }

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8));

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
            System.err.println("Can't find " + args[1]);
        } catch (IOException e) {
            System.err.println("Can't write to " + args[1]);
        }
    }

    private static boolean canBeInWord(char c) {
        return (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
    }
}