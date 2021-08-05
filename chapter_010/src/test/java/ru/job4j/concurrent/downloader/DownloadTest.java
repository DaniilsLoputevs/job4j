package ru.job4j.concurrent.downloader;

import daniils.IOHelper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * expected args:
 * -url {url value} -t {target path} -n {name} -s {num} {kof}
 * num = "100"
 * kof = "b"
 */
public class DownloadTest {
    private String url = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
    private File targetFile;
    private String path;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Before
    public void setUp() throws Exception {
        targetFile = tempFolder.newFile("download_test.xml");
        path = tempFolder.getRoot().getAbsolutePath();
    }

    @Test
    public void downloadTest() {
        assertTrue(IOHelper.readFileToList(targetFile).isEmpty());
        String[] args = {"-url", url, "-t", path, "-n", "download_test.xml", "-s", "2", "kb"};

        new Download().downloadByArgs(args);
        assertFalse(IOHelper.readFileToList(targetFile).isEmpty());
    }

    //    @Test
//    public void mainTest() {
//        assertTrue(IOHelper.readFileToList(targetFile).isEmpty());
//        String[] args = {"-url", url, "-t", path, "-n", "download_test.xml", "-s", "2", "kb"};
//        Download.logic(args);
//        assertFalse(IOHelper.readFileToList(targetFile).isEmpty());
//    }
}