package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;


public class Md2Html {
    public static void main(String[] args){
        StringBuilder result = new StringBuilder();
        try {
            try (Reader reader = new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
            )) {
                try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        StandardCharsets.UTF_8
                ))) {
                    WordsScanner scan = new WordsScanner(reader);
                    String line = scan.nextLine();
                    StringBuilder lines = new StringBuilder();
                    while (line != null && !line.isBlank()) {
                        while (!line.isEmpty()) {
                            lines.append(line);
                            lines.append("\n");
                            line = scan.nextLine();
                        }
                        if (!lines.isEmpty()) {
                            new Partition(lines).toHtml(result);
                            result.append("\n");
                            lines.setLength(0);
                        }
                    }
                    writer.write(result.toString());
                }
            }
        } catch (IOException e){
            System.out.println("Input/output file error: " + e.getMessage());
        }
    }
}
