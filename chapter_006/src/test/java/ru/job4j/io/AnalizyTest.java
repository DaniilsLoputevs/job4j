package ru.job4j.io;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class AnalizyTest {
    private final String sourcePath = "./src/main/java/ru/job4j/io/analise_input.txt";
    private final String targetPath = "./src/main/java/ru/job4j/io/analise_answers.txt";

    @Test
    public void unavailable() {
        new Analizy().unavailable(sourcePath, targetPath);
        var expectedList = List.of(
                "10:57:01 - 10:59:01",
                "11:01:02 - 11:02:02"
        );
        assertThat(Helper.compareInfoFromFileWithList(targetPath, expectedList), is(true));

    }

}