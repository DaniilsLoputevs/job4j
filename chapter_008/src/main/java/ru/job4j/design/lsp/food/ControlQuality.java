package ru.job4j.design.lsp.food;

import java.util.GregorianCalendar;
import java.util.List;

/**
 * Control quality class, - put all foods in stores.
 * In Constructor put List with all stores that can hold food.
 */
public class ControlQuality {
    private List<StoreStrategy> stores;

    public ControlQuality(List<StoreStrategy> stores) {
        this.stores = stores;
    }

    /**
     * Accept food and put in right store.
     * In while it find a right store and put if store say True by method accept
     *
     * @param food - food that need to put.
     */
    public void accept(Food food) {
        var storeIterator = stores.iterator();
        var run = false;
        var qualityPercents = findQualityPercents(food);
        while (!run) {
            if (storeIterator.hasNext()) {
                run = storeIterator.next().accept(food, qualityPercents);
            }
        }
    }

    public void acceptAll(List<Food> list) {
        list.forEach(this::accept);
    }

    public List<StoreStrategy> getStores() {
        return stores;
    }

    /**
     * Find how badly the food is spoiled in percents value.
     * Example: 13.435 = 13.435% - in real.
     *
     * @param food - food that need to find percents.
     * @return percents
     */
    private double findQualityPercents(Food food) {
        var create = food.getCreateDate().getTimeInMillis();
        var now = new GregorianCalendar().getTimeInMillis();
        var expire = food.getExpireDate().getTimeInMillis();

        // DeBug
//        System.out.println(food.getName());
        long valueOfMaxPercents = expire - create;
//        System.out.println("valueOfMaxPercents: " + valueOfMaxPercents);

        long currentPercents = now - create;
//        System.out.println("now: " + now);
//        System.out.println("create: " + create);
//        System.out.println("expire: " + expire);
//        System.out.println("currentPercents: " + currentPercents);

        double qualityPercents = calculatePercentage(currentPercents, valueOfMaxPercents);
//        System.out.println("qualityPercents: " + qualityPercents);
//        System.out.println();

        return qualityPercents;
    }

    private double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }
}
