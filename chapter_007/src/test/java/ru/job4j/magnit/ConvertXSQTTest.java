package ru.job4j.magnit;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.db.ConnectionRollback;
import ru.job4j.helpers.IOHelper;

import java.io.File;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ConvertXSQTTest {
    private StoreSQL storeSQl;
    private StoreXML storeXML;
    private ConvertXSQT convertXSQT;
    private File xmlTarget;
    private File xslScheme = new File("./src/test/java/ru/job4j/magnit/xslScheme.xml");
    private File xsltTarget;

    private static final Logger LOG = LoggerFactory.getLogger(ConvertXSQTTest.class);

    @Before
    public void setUp() {
        initStoreSQL();
        try {
            xmlTarget = tempFolder.newFile("xmlTarget.xml");
            xsltTarget = tempFolder.newFile("xsltTarget.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }

        storeXML = new StoreXML(xmlTarget);
        convertXSQT = new ConvertXSQT();
    }

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

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
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>",
                "        \\<entries>\\",
                "            <entry href=\"1\"/>\\",
                "            <entry href=\"2\"/>\\",
                "            <entry href=\"3\"/>\\",
                "            <entry href=\"4\"/>\\",
                "            <entry href=\"5\"/>",
                "        \\</entries>"
        );
        assertEquals(xslExpected, xslResult);

    }


    private void initStoreSQL() {
        try {
            var config = new Config();
            config.init();

            var dbPath = tempFolder.newFile("magnitShopTask.sqlite3").getPath();

            var connect = DriverManager.getConnection(
                    config.get("testUrl") + '/' + dbPath);

            // just make a comment line below if you don't need a rollback connection
            connect = ConnectionRollback.create(connect);

            this.storeSQl = new StoreSQL(connect);

        } catch (SQLException | IOException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}