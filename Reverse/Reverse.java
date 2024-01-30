import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class Reverse {
    public static void main(String[] args){
        try {
            FScanner scan = new FScanner(System.in);
            String s;
            String[] arr = new String[1];
            int capacity = 1;
            while (scan.hasNextLine()){
                s = scan.nextLine();
                s = reverse(s);
                arr = append(arr, s, capacity);
                capacity++;
            }
            //System.err.println(arr.length);
            for(int i = arr.length - 1; i >= 1; i--){
                if (arr[i] != null) System.out.println(arr[i]);
            }
            scan.close();
        }catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    static String reverse(String str){
        if (!str.isBlank()) {
            String[] numbers = new String[1];
            int cap = 1;
            StringBuilder sbuild = new StringBuilder();
            int i = 0;
            while (i < str.length()){
                sbuild.setLength(0);
                while (i < str.length() && Character.isWhitespace(str.charAt(i))){
                    i++;
                }
                while (i < str.length() && !Character.isWhitespace(str.charAt(i))){
                    sbuild.append(str.charAt(i));
                    i++;
                }
                if (!sbuild.isEmpty()) {
                    numbers = append(numbers, sbuild.toString(), cap);
                    cap++;
                }
            }
            sbuild.setLength(0);
            for (int j = numbers.length - 1; j >= 1; j--){
                if (numbers[j] != null) {
                    sbuild.append(numbers[j]);
                    sbuild.append(" ");
                }
            }
            return sbuild.toString().trim();
        }
        else return str;
    }

    static String[] append(String[] a, String elem, int c){
        if (a.length == c) {
            a = Arrays.copyOf(a, a.length * 2);
        }
        a[c] = elem;
        return a;
    }
}
