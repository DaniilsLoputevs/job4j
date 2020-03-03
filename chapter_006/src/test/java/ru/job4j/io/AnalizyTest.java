package ru.job4j.io;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.helpers.IOHelper;
import ru.job4j.helpers.StringHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    private String sourcePath = "./src/main/java/ru/job4j/io/analise_input.txt";
    private String targetPath = "./src/main/java/ru/job4j/io/analise_answers.txt";

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void unavailable() {
        new Analizy().unavailable(sourcePath, targetPath);
        var expectedList = List.of(
                "10:57:01 - 10:59:01",
                "11:01:02 - 11:02:02"
        );
        assertThat(IOHelper.compareInfoFromFileWithList(targetPath, expectedList), is(true));
    }

    @Test
    public void tryWithTempFolder() throws IOException {
        var testFile = tempFolder.newFile("test.check");
        var answerFile = tempFolder.newFile("answer.check");

        IOHelper.writeListToFile(testFile.getPath(), List.of(
                StringHelper.separateLines(
                "200 10:56:01",
                "500 10:57:01",
                "400 10:58:01",
                "200 10:59:01",
                "500 11:01:02",
                "200 11:02:02"
        )));

        new Analizy().unavailable(testFile.getPath(), answerFile.getPath());
        var fileLines = (LinkedList) IOHelper.readFileToList(answerFile.getPath());

        assertEquals(fileLines.removeFirst(), "10:57:01 - 10:59:01");
        assertEquals(fileLines.removeFirst(), "11:01:02 - 11:02:02");
    }
}