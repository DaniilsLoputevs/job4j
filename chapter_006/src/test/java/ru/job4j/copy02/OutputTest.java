package ru.job4j.copy02;

import daniils.IOHelper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class OutputTest {
    private String sourceFolderPath = "./src/main/resources/directoryForSearchFiles";
    private String targetPath; // log in temp folder
    private Args argsMap = new Args();

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    // Manual Before
    public void setUp(String findValue, String findKey) {
        try {
            targetPath = tempFolder.newFile("log.txt").getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.argsMap.load(new String[]{
                "-d",
                sourceFolderPath,
                "-n",
                findValue,
                findKey,
                "-o",
                targetPath + "/log.txt"
        });
    }

    @Test
    public void saveInLog() {
        var findKey = "-r";
        var findValue = ".*txt";
        setUp(findValue, findKey);

        List<Path> temp = new SearchFiles().start(argsMap);
        new Output().logSave(temp, targetPath);

        List<String> excepted = List.of(
                "file_1.txt --- .\\src\\main\\resources\\directoryForSearchFiles\\file_1.txt",
                "file_2.txt --- .\\src\\main\\resources\\directoryForSearchFiles\\file_2.txt",
                "file_7.txt --- .\\src\\main\\resources\\directoryForSearchFiles\\p1\\file_7.txt",
                "file_11.txt --- .\\src\\main\\resources\\directoryForSearchFiles\\p1\\p2\\file_11.txt",
                "test_copy_file.txt --- .\\src\\main\\resources\\directoryForSearchFiles\\p1\\p2\\test_copy_file.txt"
        );
        List<String> result = IOHelper.readFileToList(targetPath, ArrayList::new);

        assertEquals(excepted.size(), result.size());
    }
}