package ru.job4j.design.lsp.food;

import org.junit.Before;
import org.junit.Test;
import ru.job4j.design.lsp.food.models.Bakery;
import ru.job4j.design.lsp.food.models.Food;
import ru.job4j.design.lsp.food.models.Fruit;
import ru.job4j.design.lsp.food.models.Liquid;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ControlQualityTest {
    private List<StoreStrategy> storeStrategies;
    private List<Food> foodList;
    private ControlQuality cq;
    private List<StoreStrategy> stores;

    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");


    @Before
    public void setUp() {
        this.storeStrategies = List.of(
                new Trash(),
                new Shop(),
                new Warehouse()
        );
        initFoodList();

        try {
            this.cq = new ControlQualityDefault(storeStrategies, dateFormat.parse("20.05.2020"));
            this.stores = this.cq.getStores();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private void initFoodList() {
        try {
            var createTime = dateFormat.parse("10.05.2020");
            this.foodList = List.of(
                    new Fruit("apple", dateFormat.parse("30.05.2020"), createTime),
                    new Liquid("water", dateFormat.parse("30.08.2020"), createTime),
                    new Liquid("milk", dateFormat.parse("23.05.2020"), createTime),
                    new Food("mushroom", dateFormat.parse("15.07.2020"), createTime),
                    new Bakery("potato", dateFormat.parse("20.06.2020"), createTime)
            );

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void acceptAllTest() {
        this.cq.acceptAll(foodList);

        assertTrue(stores.get(0).size() > 0);
        assertTrue(stores.get(1).size() > 0);
        assertTrue(stores.get(2).size() > 0);
    }

    @Test
    public void resortTest() {
        var tempStore = (this.stores.get(0));
        foodList.forEach(tempStore::justPush);

        this.cq.resort();

        assertTrue(stores.get(0).size() > 0);
        assertTrue(stores.get(1).size() > 0);
        assertTrue(stores.get(2).size() > 0);
    }
}