package ru.job4j.design.isp;

/**
 * Default Menu Manager.
 * <p>
 * show menu in tree-format. Like tree with Directories.
 */
public class MenuManagerDefault implements MenuManager {
    private MenuItem root;

    public MenuManagerDefault(MenuItem root) {
        this.root = root;
    }


    /**
     * Show menu that is in Manager.
     * * Show in tree-format.
     */
    @Override
    public void showMenu() {
        System.out.println("Menu");
        var tab = "--";
        for (var e : root.getLeaves()) {
            System.out.println(tab + " " + e.getName());
            innerPrint(e, tab);
        }
    }

    /**
     * Print leafs of {@code Menu item}
     *
     * @param menuItem - Menu item which leafs it print.
     * @param tab      - tab for new level of leaves.
     */
    private void innerPrint(MenuItem menuItem, String tab) {
        tab += tab;
        for (var e : menuItem.getLeaves()) {
            System.out.println(tab + " " + e.getName());
            innerPrint(e, tab);
        }
    }

}