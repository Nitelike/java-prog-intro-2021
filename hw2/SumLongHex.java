public class SumLongHex {
    public static void main(String[] args) {
        long result = 0;

        for (int j = 0; j < args.length; j++) {
            String input = args[j] + " ";

            int leftPtr = 0;
            for (int i = 0; i < input.length(); i++) {
                if (Character.isWhitespace(input.charAt(i))) {
                    if (i - leftPtr >= 1) {
                        String cur = input.substring(leftPtr, i);
                        if (cur.startsWith("0x") || cur.startsWith("0X")) {
                            result += Long.parseUnsignedLong(cur.substring(2), 16);
                        } else {
                            result += Long.parseLong(cur, 10);
                        }
                    }

                    leftPtr = i + 1;
                }
            }

        }

        System.out.println(result);
    }
}