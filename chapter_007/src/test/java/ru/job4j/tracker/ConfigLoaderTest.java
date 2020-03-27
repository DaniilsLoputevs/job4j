package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigLoaderTest {

    @Test
    public void whenPairWithoutComment() {
        String path = "./src/main/resources/connection_config.properties";
        var config = new ConfigLoader(path);
        config.load();
        assertThat(config.value("username"), is("postgres"));
    }

}
