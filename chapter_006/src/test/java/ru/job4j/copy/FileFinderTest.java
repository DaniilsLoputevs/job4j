package ru.job4j.copy;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.helpers.IOHelper;
import ru.job4j.helpers.StringHelper;
import ru.job4j.search.Search;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FileFinderTest {
    private String sourceFolderPath = "./src/test/java/ru/job4j/archive";
    private String targetPath;
    private Args args;

    private String findValue;
    private String findKey;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();


    // Manual Before
    public void setUp(String findValue, String findKey) {
        targetPath = tempFolder.getRoot().getPath();
        try {
            tempFolder.newFile("log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // -d c:/ -n *.txt -m -o log.txt
        this.args = new Args(new String[] {
                "-d",
                sourceFolderPath,
                "-n",
                findKey,
                findValue,
                "-o",
                targetPath + "/log.txt"
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

        new FileFinder().findAndWrite(args.rootPath(), args.findParamValue(), args.target(), args.findParamKey());

        var expected = new ArrayList<>(List.of("another test file." + System.lineSeparator()));
        var realLog = IOHelper.readFileToList(targetPath + "/log.txt");
        realLog = StringHelper.separateLines(realLog);
        assertThat(realLog, is(expected));
    }
    private void modelForFullNameAndMaskTest() {
        new FileFinder().findAndWrite(args.rootPath(), args.findParamValue(), args.target(), args.findParamKey());
        var expected = new ArrayList<>(StringHelper.linesToList(StringHelper.separateLines(
                "111 --- aaa",
                "222 --- bbb",
                "333 --- ccc"
        )));
        var realLog = IOHelper.readFileToList(targetPath + "/log.txt");
        realLog = StringHelper.separateLines(realLog);
        assertThat(realLog, is(expected));
    }



    @Test
//    @Ignore
    public void tryOne() {
        // Не хотел строить несколько классов, для обработки ключей и придумал исп. Люмду
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
        assertThat(lambdaFile.getName(), is("test_copy_file.txt"));




    }

}