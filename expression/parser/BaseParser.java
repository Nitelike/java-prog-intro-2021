package expression.parser;

public class BaseParser {
    public final static char END = 0;

    private final CharSource source;
    private char ch;

    public BaseParser(CharSource source) {
        this.source = source;
        take();
    }

    public boolean test(char expected) {
        return ch == expected;
    }

    public boolean test(CharChecker cc) {
        return cc.check(ch);
    }

    public char take() {
        char res = ch;
        ch = source.hasNext() ? source.next() : END;
        return res;
    }

    public boolean take(char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }

    public int take(String expected) {
        for (int i = 0; i < expected.length(); i++) {
            if (!take(expected.charAt(i))) {
                return i;
            }
        }
        return expected.length();
    }

    public void expect(char expected) {
        if (!take(expected)) {
            throw source.error(String.format(
                    "Expected '%s' found '%s'",
                    expected, ch != END ? ch : "end-of-file"
            ));
        }
    }

    protected void expect(String expected) {
        for (char c : expected.toCharArray()) {
            expect(c);
        }
    }

    protected IllegalArgumentException error(String message) {
        throw source.error(message);
    }

    public boolean end() {
        return test(END);
    }
}
