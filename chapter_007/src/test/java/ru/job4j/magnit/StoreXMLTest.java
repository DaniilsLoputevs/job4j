package ru.job4j.magnit;

import daniils.IOHelper;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StoreXMLTest {
    private StoreXML storeXML;
    private File target;

    @Rule
    public TemporaryFolder tempFolder = new TemporaryFolder();

    // 1) init
    @Before
    public void setUp() throws Exception {
        target = tempFolder.newFile("target.xml");
        storeXML = new StoreXML(target);
    }

    @Test
    public void tryRun() {
        // 2) prepare
        List<Entry> result = List.of(
                new Entry(1),
                new Entry(2),
                new Entry(3)
        );

        // 3) action
        storeXML.save(result);

        // 4) expected
        List<String> actualResult = IOHelper.readFileToList(target.getPath(), ArrayList::new);
        List<String> temp = List.of(
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
                "</entries>"
        );
        List<String> expected = new ArrayList<>(temp);

        // 5) compare
        assertEquals(expected, actualResult);
    }
}