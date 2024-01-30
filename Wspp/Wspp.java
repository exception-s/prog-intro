import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

/**
 Treemap for modifications
 */

public class Wspp {
    public static void main(String[] args){
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
                    LinkedHashMap<String, IntList> map = new LinkedHashMap<>();
                    int wordCounter = 1;
                    String word;
                    while (scan.hasNextLine()) {
                        WordsScanner secondScan = new WordsScanner(scan.nextLine());
                        while (secondScan.hasNext()) {
                            word = secondScan.next().toLowerCase();
                            if (map.containsKey(word)) {
                                IntList list = map.get(word);
                                list.set(0, list.get(0) + 1);
                                list.append(wordCounter);
                                map.replace(word, list);
                            }
                            else {
                                IntList list = new IntList();
                                list.append(1);
                                list.append(wordCounter);
                                map.put(word, list);
                            }
                            wordCounter++;
                        }
                    }
                    for (String key : map.keySet()){
                        writer.write(key);
                        writer.write(" ");
                        writer.write(map.get(key).toString());
                        writer.newLine();
                    }
                }
            }
        } catch (IOException e){
            System.out.println("Input/output file error: " + e.getMessage());
        }
    }
}
