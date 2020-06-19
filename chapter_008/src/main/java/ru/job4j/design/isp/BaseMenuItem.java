package ru.job4j.design.isp;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseMenuItem {
    String name;
    List<MenuItem> leaves = new ArrayList<>();

    public BaseMenuItem(String name, List<MenuItem> leaves) {
        this.name = name;
        this.leaves = leaves;
    }

    public BaseMenuItem(String name) {
        this.name = name;
    }

    /**
     * Do Menu item logic that should be realized in extended classes.
     * @return true - if this logic completed successfully.
     */
    public boolean doLogic() {
        throw new UnsupportedOperationException("Realise this method in extended class!");
    }
}
