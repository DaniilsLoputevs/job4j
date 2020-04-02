package ru.job4j.copy02;

import org.junit.Test;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class SearchFilesTest {
    private String sourceFolderPath = "./src/main/resources/directoryForSearchFiles";
    private String targetPath = "stubTarget";
    private Args argsMap = new Args();

    private String findValue;
    private String findKey;

    // Manual Before
    public void setUp() {
        this.argsMap.load(new String[]{
                "-d",
                sourceFolderPath,
                "-n",
                this.findValue,
                this.findKey,
                "-o",
                targetPath + "/log.txt"
        });
    }

    private void testModel(List<String> excepted) {
        List<Path> temp = new SearchFiles().start(argsMap);
        var result = temp.stream().map(Path::toString).collect(Collectors.toList());

        assertEquals(excepted, result);
    }

    @Test
    public void findByFullNameTest() {
        this.findKey = "-f";
        this.findValue = "test_copy_file.txt";
        setUp();

        List<String> excepted = List.of(
                ".\\src\\main\\resources\\directoryForSearchFiles\\p1\\p2\\test_copy_file.txt"
        );
        testModel(excepted);
    }

    @Test
    public void findByMaskTest() {
        this.findKey = "-m";
        this.findValue = "^test_copy.*$";
        setUp();

        List<String> excepted = List.of(
                ".\\src\\main\\resources\\directoryForSearchFiles\\p1\\p2\\test_copy_file.txt"
        );
        testModel(excepted);
    }

    @Test
    public void findByRegularTest() {
        this.findKey = "-r";
        this.findValue = ".*txt";
        setUp();

        List<String> excepted = List.of(
                ".\\src\\main\\resources\\directoryForSearchFiles\\file_1.txt",
                ".\\src\\main\\resources\\directoryForSearchFiles\\file_2.txt",
                ".\\src\\main\\resources\\directoryForSearchFiles\\p1\\file_7.txt",
                ".\\src\\main\\resources\\directoryForSearchFiles\\p1\\p2\\file_11.txt",
                ".\\src\\main\\resources\\directoryForSearchFiles\\p1\\p2\\test_copy_file.txt"
        );
        testModel(excepted);
    }
}