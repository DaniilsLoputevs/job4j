package ru.job4j.design.lsp.food;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.food.models.Fruit;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class StoreStrategyTest {
    private StoreStrategy store = new Shop();

    @Before
    public void setUp() {
        store.justPush(new Fruit("Lemon", new Date(), new Date()));
        store.justPush(new Fruit("Apple", new Date(), new Date()));
        store.justPush(new Fruit("kiwi", new Date(), new Date()));
    }

    @Test
    public void justPushTest() {
        assertEquals(3, store.size());
    }

    @Test
    public void takeOutAllTest() {
        var shop = store;
        var out = shop.takeOutAll();

        assertEquals(0, shop.size());
        assertEquals(3, out.size());
    }
}