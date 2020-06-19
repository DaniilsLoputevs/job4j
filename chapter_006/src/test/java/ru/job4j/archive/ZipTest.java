package ru.job4j.archive;

import daniils.IOHelper;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * ** В main коде, Path начиается с job4j(.)/chapter_N/src/main/...
 * ** В test коде, Path начиается с chapter_N(.)/src/test/...
 * ** Вместо TemporaryFolder можно использовать Путь: System.getProperty("java.io.tmpdir")
 */
public class ZipTest {
    private String resourcesPath = "./src/main/resources/directoryForSearchFiles";
    private File root = new File(resourcesPath);
    private String systemTmpFolderPath = System.getProperty("java.io.tmpdir");

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();


    @Test
    public void zipToTemp() throws Exception {
//       testInfo();
        var targetPath = tempFolder.getRoot().getPath() + "/arch.zip";

        new Zip().zipTo(root, Set.of("txt"), targetPath);

        var checkFile = new File(targetPath);
        assertTrue(checkFile.exists());
        assertThat(IOHelper.getExt(checkFile), is("zip"));
    }

    private void testInfo() {
        System.out.println("resourcesPath:                             " + root.getPath());
        System.out.println("root.exists:                               " + root.exists());

        System.out.println("systemTmpFolderPath:                       " + tempFolder.getRoot().getPath());
        System.out.println("System.getProperty(\"java.io.tmpdir\"):    " + systemTmpFolderPath);
    }
}


/* Конспет мне в будущем. 03.03.2020.
 * доступ к файлам в resources можно получить чз метод
 * Zip.class.getClassLoader().getResource(path).getFile(),
 * где path — имя файла с путём относительно resources — это будет работать везде,
 * тк файл ищется относительно classpath текущего модуля
 */