package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    private final String sourcePath = "./src/main/java/ru/job4j/io/analise_input.txt";
    private final String targetPath = "./src/main/java/ru/job4j/io/analise_answers.txt";

    @Rule
    public TemporaryFolder tempFolder  = new TemporaryFolder();

    @Test
    public void unavailable() {
        new Analizy().unavailable(sourcePath, targetPath);
        var expectedList = List.of(
                "10:57:01 - 10:59:01",
                "11:01:02 - 11:02:02"
        );
        assertThat(Helper.compareInfoFromFileWithList(targetPath, expectedList), is(true));
    }

    @Test
    public void wheWeTryWithTemp() throws IOException {
        var testFile = tempFolder.newFile("test.check");
        var answerFile = tempFolder.newFile("answer.check");

        Helper.writeListToFile(testFile.getPath(), List.of(
                "200 10:56:01", "\n",
                "500 10:57:01", "\n",
                "400 10:58:01", "\n",
                "200 10:59:01", "\n",
                "500 11:01:02", "\n",
                "200 11:02:02", "\n"
        ));

        new Analizy().unavailable(testFile.getPath(), answerFile.getPath());
        var fileLines = (LinkedList) Helper.readFileToList(answerFile.getPath());

        assertEquals(fileLines.removeFirst(), "10:57:01 - 10:59:01");
        assertEquals(fileLines.removeFirst(), "11:01:02 - 11:02:02");
    }

}