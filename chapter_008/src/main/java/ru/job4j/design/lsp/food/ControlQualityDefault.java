package ru.job4j.design.lsp.food;

import ru.job4j.design.lsp.food.models.Food;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ControlQualityDefault implements ControlQuality {
    private List<StoreStrategy> stores;
    private Date checkTime;

    public ControlQualityDefault(List<StoreStrategy> stores) {
        this.stores = stores;
        this.checkTime = new Date();
    }

    public ControlQualityDefault(List<StoreStrategy> stores, Date checkTime) {
        this.stores = stores;
        this.checkTime = checkTime;
    }

    /**
     * Accept food and put in right store.
     * In while it find a right store and put if store say True by method accept
     *
     * @param food - food that need to put.
     */
    @Override
    public void accept(Food food) {
        var storeIterator = stores.iterator();
        var run = false;
        var qualityPercents = findSpoiledPercents(food);
        while (!run) {
            if (storeIterator.hasNext()) {
                run = storeIterator.next().accept(food, qualityPercents);
            }
        }
    }

    @Override
    public void acceptAll(List<Food> list) {
        list.forEach(this::accept);
    }

    @Override
    public void resort() {
        var tempStore = new ArrayList<Food>();
        var stores = getStores();
        stores.forEach(store -> tempStore.addAll(store.takeOutAll()));
        acceptAll(tempStore);
    }

    @Override
    public List<StoreStrategy> getStores() {
        return stores;
    }

    /**
     * Find how badly the food is spoiled in percents value.
     * Example: 13.435 = 13.435% - in real.
     * <p>
     * * Commented prints it's for Debug mode.
     * * If it hard to read, just see only special words "long" && "double".
     *
     * @param food - food that need to find percents.
     * @return - percents.
     */
    private double findSpoiledPercents(Food food) {
        var create = food.getCreateDate().getTime();
        var now = this.checkTime.getTime();
        var expire = food.getExpireDate().getTime();

        // DeBug
//        System.out.println(food.getName());
        long valueOfMaxPercents = expire - create;
//        System.out.println("valueOfMaxPercents: " + valueOfMaxPercents);

        long valueOfCurrentPercents = now - create;
//        System.out.println("now time : " + now);
//        System.out.println("create: " + create);
//        System.out.println("expire: " + expire);
//        System.out.println("valueOfCurrentPercents: " + valueOfCurrentPercents);

        double spoiledPercents = calculatePercentage(valueOfCurrentPercents, valueOfMaxPercents);
//        System.out.println("spoiledPercents: " + spoiledPercents);
//        System.out.println();

        return spoiledPercents;
    }

    private double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }
}
