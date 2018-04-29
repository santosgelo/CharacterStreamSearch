package com.gelo.charstream;

public class CharacterStreamSearcher implements Searcher {
    private CharSequence charSequence;
    private char[] pattern;
    private StringBuilder stringBuilder;

    @Override
    public boolean find() {
        stringBuilder = new StringBuilder();
        //start from comparing end of the character, if it does not match, jump again
        int shift[] = new int[256];

        for (int k = 0; k < 256; k++) {
            shift[k] = pattern.length;
        }

        for (int k = 0; k < pattern.length - 1; k++) {
            shift[pattern[k]] = pattern.length - 1 - k;
        }

        int i = 0, j = 0;
        while (notYetEndOfCharSequence(charSequence)) {
            j = pattern.length - 1;
            while (getCharAt(i + j) == pattern[j]) {
                j -= 1;
                if (j < 0) {
                    shiftSequence(pattern.length);
                    return true;
                }
            }
            i = shiftSequence(shift[getCharAt(i + pattern.length - 1)]);
        }
        return false;
    }

    //whenever we shift the sequence we flush the other strings into the builder
    private int shiftSequence(int shift) {
        stringBuilder.append(charSequence.subSequence(0, shift));
        charSequence = charSequence.subSequence(shift, charSequence.length());
        return 0;
    }

    private char getCharAt(int i) {
        return charSequence.charAt(i);
    }

    private boolean notYetEndOfCharSequence(CharSequence charSequence) {
        return true;
    }

    public void setCharSequence(CharSequence charSequence) {
        this.charSequence = charSequence;
    }

    public void setCharsToFind(String searchString) {
        this.pattern = searchString.toCharArray();
    }

    public String getString() {
        System.out.println("length is " + stringBuilder.toString().length());
        return stringBuilder.toString();
    }
}
