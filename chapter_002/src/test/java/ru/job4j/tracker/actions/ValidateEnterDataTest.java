package ru.job4j.tracker.actions;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.Tracker;

public class ValidateEnterDataTest {
    @Test
    public void checkId() {
        Tracker tracker = new Tracker();
        Item first = new Item("example");
        tracker.add(first);
        Assert.assertTrue(ValidateEnterData.checkId(first.getId(), tracker));
    }

}
