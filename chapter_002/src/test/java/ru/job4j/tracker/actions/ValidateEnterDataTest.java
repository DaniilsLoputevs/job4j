package ru.job4j.tracker.actions;

import org.junit.Assert;
import org.junit.Test;
import ru.job4j.tracker.Item;
import ru.job4j.tracker.TrackerLocal;

public class ValidateEnterDataTest {
    @Test
    public void checkId() {
        TrackerLocal trackerLocal = new TrackerLocal();
        Item first = new Item("example");
        trackerLocal.add(first);
        Assert.assertTrue(ValidateEnterData.checkId(first.getId(), trackerLocal));
    }

}
