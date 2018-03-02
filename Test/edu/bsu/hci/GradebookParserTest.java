package edu.bsu.hci;

import org.junit.Assert;
import org.junit.Test;

public class GradebookParserTest {

    @Test
    public void parseNumberOnlyTestNumbers() {
        GradebookParser gparser = new GradebookParser();
        Assert.assertEquals(gparser.parseNumberOnly("123"), 123);
    }

    @Test
    public void parseNumberOnlyTestLetters() {
        GradebookParser gparser = new GradebookParser();
        Assert.assertEquals(gparser.parseNumberOnly("abc123def"), 123);
    }

    @Test
    public void parseNumberOnlyTestOtherCharacters() {
        GradebookParser gparser = new GradebookParser();
        Assert.assertEquals(gparser.parseNumberOnly("ab;'2!3def"), 23);
    }
}
