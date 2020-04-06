package ru.job4j.magnit;

import org.junit.*;
import org.junit.rules.TemporaryFolder;
import ru.job4j.helpers.IOHelper;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConvertXSQTTest {
    private StoreSQL storeSQl;
    private StoreXML storeXML;
    private ConvertXSQT convertXSQT;
    private File xmlTarget;
    private File xslScheme = new File("./src/test/java/ru/job4j/magnit/xslScheme.xsl");
    private File xsltTarget;

    @Before
    public void setUp() {
        String tmpPathToBase = null;
        try {
            tmpPathToBase = tempFolder.newFile("magnitShopTask.sqlite3").getPath();
        } catch (IOException e) {
            e.printStackTrace();
        }

        var config = new Config();
        config.init();
        storeSQl = new StoreSQL(config, tmpPathToBase);

        try {
            storeSQl.getConnect().setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            xmlTarget = tempFolder.newFile("xmlTarget.xml");
            xsltTarget = tempFolder.newFile("xsltTarget.xml");
//            xsltTarget = tempFolder.newFile(" = new File(\"./src/test/java/ru/job4j/magnit/target.xslt");
        } catch (IOException e) {
            e.printStackTrace();
        }

        storeXML = new StoreXML(xmlTarget);
        convertXSQT = new ConvertXSQT();
    }

    @After
    public void finish() {
        try {
            storeSQl.getConnect().rollback();
            storeSQl.getConnect().setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    @Ignore
    public void convert() {
        convertXSQT.convert(xmlTarget, xsltTarget, xslScheme);

        var xslResult = IOHelper.readFileToList(xsltTarget.getPath(), ArrayList::new);
        List<String> xslExpected = List.of(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                        + "<entries>"
                        + "<entry href=\"1\"/>"
                        + "<entry href=\"2\"/>"
                        + "<entry href=\"3\"/>"
                        + "<entry href=\"4\"/>"
                        + "<entry href=\"5\"/>"
                        + "</entries>"
        );
        assertEquals(xslExpected, xslResult);
    }

    @Test
    public void complexRun() {
        // StoreSQL
        storeSQl.generate(5);
        List<Entry> entryListFromBase = storeSQl.load();
        assertEquals(5, entryListFromBase.size());

        // StoreXML
        storeXML.save(entryListFromBase);

        List<String> xmlResult = IOHelper.readFileToList(xmlTarget.getPath(), ArrayList::new);
        List<String> xmlExpected = List.of(
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>",
                "<entries>",
                "    <entry>",
                "        <field>1</field>",
                "    </entry>",
                "    <entry>",
                "        <field>2</field>",
                "    </entry>",
                "    <entry>",
                "        <field>3</field>",
                "    </entry>",
                "    <entry>",
                "        <field>4</field>",
                "    </entry>",
                "    <entry>",
                "        <field>5</field>",
                "    </entry>",
                "</entries>"
        );
        assertEquals(xmlExpected, xmlResult);

        // ConvertXSQT
        convertXSQT.convert(xmlTarget, xsltTarget, xslScheme);

        var xslResult = IOHelper.readFileToList(xsltTarget.getPath(), ArrayList::new);
        List<String> xslExpected = List.of(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
                        + "<entries>"
                        + "<entry href=\"1\"/>"
                        + "<entry href=\"2\"/>"
                        + "<entry href=\"3\"/>"
                        + "<entry href=\"4\"/>"
                        + "<entry href=\"5\"/>"
                        + "</entries>"
        );
        assertEquals(xslExpected, xslResult);

    }


}