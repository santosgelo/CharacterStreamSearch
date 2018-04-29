package com.gelo.charstream;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SearcherTest {

    @Test
    public void testStringSearch() {
        StringBuilder charSequenceBuilder = new StringBuilder();
        for (int i = 0; i < Integer.MAX_VALUE/100000; i++) {
            charSequenceBuilder.append("randomString");
        }
        charSequenceBuilder.append("findme");
        for (int i = 0; i < Integer.MAX_VALUE/100000; i++) {
            charSequenceBuilder.append("dummy");
        }

        CharacterStreamSearcher searcher = new CharacterStreamSearcher();
        searcher.setCharSequence(charSequenceBuilder.toString());
        searcher.setCharsToFind("findme");
        searcher.find();
        String string = searcher.getString();
        assertNotNull(string);
        System.out.println(string.length());
    }

}
