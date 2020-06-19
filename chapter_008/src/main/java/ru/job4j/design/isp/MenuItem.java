package ru.job4j.design.isp;

import java.util.List;

public class MenuItem extends BaseMenuItem {
    public MenuItem(String name, List<MenuItem> leaves) {
        super(name, leaves);
    }

    public MenuItem(String name) {
        super(name);
    }

    @Override
    public boolean doLogic() {
        System.out.println("doLogic");
        return true;
    }

    public String getName() {
        return name;
    }

    public List<MenuItem> getLeaves() {
        return leaves;
    }

}
