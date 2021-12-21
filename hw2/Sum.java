package hw2;

public class Sum {
    public static void main(String[] args) {
        int result = 0;

        for (int j = 0; j < args.length; j++) {
            String input = args[j] + " ";

            int leftPtr = 0;
            for (int i = 0; i < input.length(); i++) {
                if (Character.isWhitespace(input.charAt(i))) {
                    if (i - leftPtr >= 1) {
                        String cur = input.substring(leftPtr, i);
                        result += Integer.parseInt(cur, 10);
                    }

                    leftPtr = i + 1;
                }
            }

        }

        System.out.println(result);
    }
}