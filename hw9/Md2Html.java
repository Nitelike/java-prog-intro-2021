package md2html;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import java.io.InputStreamReader;
import java.io.FileInputStream;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;

public class Md2Html {
    public static void main(String[] args) {
        StringBuilder res = new StringBuilder();
        Md2HtmlParser parser = new Md2HtmlParser();

        try {
            MyScanner sc = new MyScanner(
                new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
                )
            );

            try {
                String line = sc.nextLine();
                StringBuilder sb = new StringBuilder();

                while (true) {
                    if (line != null && line.length() > 0) {
                        sb.append(line).append('\n');
                    } else if (sb.length() > 0) {
                        sb.setLength(sb.length() - 1);
                        res.append(parser.parseBlock(sb)).append('\n');
                        sb = new StringBuilder();
                    }
                    if (line == null) {
                        break;
                    }
                    line = sc.nextLine();
                }
            } finally {
                sc.close();
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
                out.write(res.toString());  
            } finally {
                out.close();
            }
        } catch (FileNotFoundException e) {
            System.err.println("Can't find output file: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Can't write to output file: " + e.getMessage());
        }
    }
}