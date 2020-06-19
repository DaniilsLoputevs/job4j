package ru.job4j.search;

import daniils.IOHelper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SearchTest {
    private String path;

    @Before
    public void setUp() {
        path = tempFolder.getRoot().getPath() + "/root";
        IOHelper.createFile(path, true); // 0 - root

        // (n) - это номер папки - исп. для понимания где что заполняетсья.
        // заполнение папки: {0}
        IOHelper.createFile(path + "/fileOne", true); // 0 - 1 (1)
        IOHelper.createFile(path + "/fileTwo", true); // 0 - 2 (2)
        IOHelper.createFile(path + "/extra_one.txt", false);

        // заполнение папки: {1}
        IOHelper.createFile(path + "/fileOne/file_three.check", false);
        IOHelper.createFile(path + "/fileOne/file_four", true); // 0 - 1 - 1 (3)
        IOHelper.createFile(path + "/fileOne/file_five.pom", false);

        // заполнение папки: {2}
        IOHelper.createFile(path + "/fileTwo/file_six", true); // 0 - 2 - 1 (4)
        IOHelper.createFile(path + "/fileTwo/file_seven.txt", false);
        IOHelper.createFile(path + "/fileTwo/file_eight", true); // 0 - 2 - 2 (5)

        // заполнение папки: {3}
        IOHelper.createFile(path + "/fileOne/file_four/file_nine.pom", false);
        IOHelper.createFile(path + "/fileOne/file_four/file_ten.check", false);

        // заполнение папки: {4}
        IOHelper.createFile(path + "/fileTwo/file_six/file_eleven.txt", false);
        IOHelper.createFile(path + "/fileTwo/file_six/file_twelve.pom", false);

        // заполнение папки: {5}
        IOHelper.createFile(path + "/fileTwo/file_eight/file_thirteen.xml", false);
    }

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Test
    public void whenWeTryFindTXTAndPom() {
        var firstList = new Search().files(path, Set.of("pom"));
        var secondList = new Search().files(path, Set.of("pom", "txt"));

        assertThat(firstList.size(), is(3));
        assertThat(secondList.size(), is(6));

        //  System.getProperty("java.io.tmpdir") + "root";
        //  C:\Users\Admin\AppData\Local\Temp\root

        //  tempFolder.getRoot().getPath() + "/root";
        //  C:\Users\Admin\AppData\Local\Temp\junit9342...\root

        // Структура папок:
        //  file {} 0
        //  ** fileOne {} 1
        //  **** file_three
        //  **** file_four {} 3
        //  ****** file_nine
        //  ****** file_ten

        //  **** file_five
        //  ** fileTwo {} 2
        //  **** file_six {} 4
        //  **** file_seven
        //  **** file_eight {} 5
        //  ** extra_one
    }
}