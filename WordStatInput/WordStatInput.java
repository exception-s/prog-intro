import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;

public class WordStatInput {
    public static void main(String[] args){
        try {
            Reader reader = new BufferedReader(new InputStreamReader(
                    new FileInputStream(args[0]),
                    StandardCharsets.UTF_8
            ));
            try {
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(args[1]),
                        StandardCharsets.UTF_8
                ));
                try {
                    LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
                    String s;
                    int read = reader.read();
                    StringBuilder sbuild = new StringBuilder();
                    while (read >= 0){
                        sbuild.setLength(0);
                        while (!Character.isLetter(read) && !(Character.getType(read) == Character.DASH_PUNCTUATION)
                        && !(read == 39)) {
                            if (read < 0) break;
                            read = reader.read();
                        }
                        while (Character.isLetter(read) || (Character.getType(read) == Character.DASH_PUNCTUATION)
                                || (read == 39)){
                            if (read < 0) {
                                break;
                            }
                            sbuild.append((char)read);
                            read = reader.read();
                        }
                        if (!sbuild.isEmpty()) {
                            s = sbuild.toString().toLowerCase();
                            if (map.containsKey(s)) {
                                map.replace(s, map.get(s), map.get(s) + 1);
                            }
                            else {
                                map.put(s, 1);
                            }
                        }
                    }
                    for(String key : map.keySet()){
                        writer.write(key);
                        writer.write(" ");
                        writer.write(map.get(key).toString());
                        writer.newLine();
                    }
                } finally {
                    writer.close();
                }
            } finally {
                reader.close();
            }
        } catch (IOException e){
            System.out.println("Input/output file error: " + e.getMessage());
        }
    }
}
