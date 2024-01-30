public class Sum {
    public static void main(String[] args) {
        int answer = 0;
        StringBuilder help = new StringBuilder();
        for(int i = 0; i < args.length; i++) {
            args[i] = args[i].trim();
            for (int j = 0; j < args[i].length(); j++) {
                if (!Character.isWhitespace(args[i].charAt(j))) {
                    help.append(args[i].charAt(j));
                }
                else if (!help.isEmpty()) {
                    answer += Integer.parseInt(help.toString());
                    help = new StringBuilder();
                }
            }
            if (!help.isEmpty()) {
                answer += Integer.parseInt(help.toString());
                help = new StringBuilder();
            }
        }
        System.out.println(answer);
    }
}
