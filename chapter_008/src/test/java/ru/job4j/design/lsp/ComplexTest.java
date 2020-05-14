package ru.job4j.design.lsp;

import daniils.DateHelper;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * All food created 4 month ago.
 * Expire date start increase from CurrentTime().
 */
public class ComplexTest {
    List<StoreStrategy> storeStrategies;
    List<Food> foodList;

    @Before
    public void setUp() throws Exception {
        this.storeStrategies = List.of(
                new Trash(),
                new Shop(),
                new Warehouse()
        );
        initFoodList();
    }

    private void initFoodList() {
        var expireTime = new GregorianCalendar();

        this.foodList = List.of(
                new Food("milk",
                        DateHelper.copyCalendarWithChanges(expireTime, Calendar.MONTH, +1)),
                new Food("water",
                        DateHelper.copyCalendarWithChanges(expireTime, Calendar.MONTH, +22)),
                new Food("potato",
                        DateHelper.copyCalendarWithChanges(expireTime, Calendar.MONTH, +2)),
                new Food("mushroom",
                        DateHelper.copyCalendarWithChanges(expireTime, Calendar.MONTH, +4)),
                new Food("cake",
                        DateHelper.copyCalendarWithChanges(expireTime, Calendar.MONTH, +1))
        );
    }


    @Test
    public void doTest() {
        var controlQuality = new ControlQuality(storeStrategies);
        controlQuality.acceptAll(foodList);

        var temp = controlQuality.getStores();

        System.out.println(temp.get(0));
        System.out.println(temp.get(1));
        System.out.println(temp.get(2));
        assertTrue(temp.get(0).size() > 0);
        assertTrue(temp.get(1).size() > 0);
        assertTrue(temp.get(2).size() > 0);
    }


}