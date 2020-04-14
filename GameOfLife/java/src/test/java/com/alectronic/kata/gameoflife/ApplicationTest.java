package com.alectronic.kata.gameoflife;

import com.alectronic.kata.gameoflife.exception.DeadWorldException;
import com.alectronic.kata.gameoflife.exception.MalformedLine;
import org.junit.Test;

import java.io.IOException;
import java.util.Objects;

public class ApplicationTest {

    @Test(expected = DeadWorldException.class)
    public void breakOutAfterItStagnates1() throws InterruptedException, DeadWorldException, IOException, MalformedLine {
        Application.main(getResourceFilePath("goodTest.txt"));
    }

    @Test(expected = IOException.class)
    public void failToFindFile() throws InterruptedException, DeadWorldException, IOException, MalformedLine {
        Application.main("test2.txt");
    }

//    TODO: make this get check at file time.
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void failsDueToBadFormat() throws InterruptedException, DeadWorldException, IOException, MalformedLine {
        Application.main(getResourceFilePath("badFormatTest.txt"));
    }

    @Test(expected = MalformedLine.class)
    public void failsDueToMalformedFile() throws InterruptedException, DeadWorldException, IOException, MalformedLine {
        Application.main(getResourceFilePath("malformedTest.txt"));
    }

    private String getResourceFilePath(String fileName) {
        return Objects.requireNonNull(this.getClass().getClassLoader().getResource(fileName)).getFile();
    }

}