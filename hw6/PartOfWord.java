package hw6;

import utility.Checker;

public class PartOfWord implements Checker {
    @Override
    public boolean partOfWord(char c) {
        return (Character.isLetter(c) || Character.getType(c) == Character.DASH_PUNCTUATION || c == '\'');
    }
}