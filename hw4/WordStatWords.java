import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.nio.charset.StandardCharsets;

public class WordStatWords {
    public static void main(String[] args) {
        String inputName = "input.txt", outputName = "output.txt";
        ArrayList<String> input = new ArrayList<String>();

        try {
            inputName = args[0];
            outputName = args[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Not all arguments were provided. Can't set right filenames.");
        }

        try {
            BufferedReader in = new BufferedReader(new FileReader(inputName, StandardCharsets.UTF_8));

            try {
                String line = in.readLine();

                while (line != null) {
                    line = line.toLowerCase() + " ";
                    int leftPtr = 0;

                    for (int i = 0; i < line.length(); i++) {
                        char c = line.charAt(i);
                        if (!canBeInWord(c)) {
                            if (i - leftPtr >= 1) {
                                String word = line.substring(leftPtr, i);
                                input.add(word);
                            }
                            leftPtr = i+1;
                        }
                    }

                    line = in.readLine();
                }
            } finally {
                in.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't find " + inputName);
        } catch (IOException e) {
            System.err.println("Can't read from " + inputName);
        }

        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(outputName, StandardCharsets.UTF_8));

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
            System.err.println("Can't find " + outputName);
        } catch (IOException e) {
            System.err.println("Can't write to " + outputName);
        }
    }

    private static boolean canBeInWord(char c) {
        return (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
    }
}