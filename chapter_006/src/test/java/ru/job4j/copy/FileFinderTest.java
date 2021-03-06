package ru.job4j.copy;

import daniils.IOHelper;
import daniils.StringHelper;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.search.Search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FileFinderTest {
    private String sourceFolderPath = "./src/main/resources/directoryForSearchFiles";
    private String targetPath;
    private Args args;

    private String findValue;
    private String findKey;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();


    // Manual Before
    public void setUp(String findValue, String findKey) {
        try {
            this.targetPath = tempFolder.newFile("log.txt").getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // -d c:/ -n *.txt -m -o log.txt
        this.args = new Args(new String[]{
                "-d",
                sourceFolderPath,
                "-n",
                findKey,
                findValue,
                "-o",
                targetPath
        });
    }

    @Test
    public void testFullName() {
        this.findValue = "test_copy_file.txt";
        this.findKey = "-f";
        setUp(findValue, findKey);

        modelForFullNameAndMaskTest();
    }

    @Test
    public void testMask() {
        this.findValue = "^test_copy.*$";
        this.findKey = "-m";
        setUp(findValue, findKey);

        modelForFullNameAndMaskTest();
    }

    @Test
    public void testRegular() {
        this.findValue = ".*txt";
        this.findKey = "-r";
        setUp(findValue, findKey);

        // Одинаковая часть, но expected разный, перенос будет занимать больше строк, чем так.
        new FileFinder().copyToPath(args.rootPath(), args.findParamValue(), args.target(), args.findParamKey());
        var expected = new ArrayList<>(List.of("another test file." + System.lineSeparator()));
        var realLog = IOHelper.readFileToList(targetPath, ArrayList::new);
        realLog = StringHelper.separateLines(realLog);
        assertEquals(expected, realLog);
    }

    private void modelForFullNameAndMaskTest() {
        new FileFinder().copyToPath(args.rootPath(), args.findParamValue(), args.target(), args.findParamKey());
        var expected = new ArrayList<>(StringHelper.linesToList(StringHelper.separateLines(
                "111 --- aaa",
                "222 --- bbb",
                "333 --- ccc"
        )));
        var realLog = IOHelper.readFileToList(targetPath, ArrayList::new);
        realLog = StringHelper.separateLines(realLog);
        assertEquals(expected, realLog);
    }


    //    @Test
    @Ignore
    public void tryOne() {
        // Не хотел строить несколько классов, для обработки ключей и придумал исп. Лямбду
        // Тут я тестил, что куда и как.

        //  full    =  "test_copy_file.txt";  - contains
        //  name    =  "copy";      - contains
        //  regular =  ".*txt";            - matches
        //  mask    =  "^file_f.*$"        - matches


        String arg = "^test_copy.*$";


        File lambdaFile = new Search().findByLambda(sourceFolderPath, (fileName -> {
            System.out.println("fileName: " + fileName + " --- arg: " + arg);
            return fileName.matches(arg);
        }));
        System.out.println();
        System.out.println(lambdaFile.getName());
        System.out.println(lambdaFile.getPath());

        assertEquals("test_copy_file.txt", lambdaFile.getName());
    }

}