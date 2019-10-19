package ru.job4j.tracker;

public class StartUI {

    public void init(Input input, Tracker tracker) {
        boolean run = true;
        // Для тест всего if else
        Item test = new Item("test");
        Item test1 = new Item("test1");
        Item test2 = new Item("test2");
        tracker.add(test);
        tracker.add(test1);
        tracker.add(test2);

        while (run) {
            showMenu();
            System.out.print("Select: ");
            int select = Integer.valueOf(input.askStr(""));

            if (select == 0) {        // Add new Item
                createItem(input, tracker);

            } else if (select == 1) { // Show all items
                showAllItems(tracker);

            } else if (select == 2) { // Edit item
                replaceItem(input, tracker);

            } else if (select == 3) { // Delete item
                deleteItem(input, tracker);

            } else if (select == 4) { // Find item by id
                findById(input, tracker);

            } else if (select == 5) { // Find items by name
                findByName(tracker);

            } else if (select == 6) { // Exit Program
                run = false;
            }
        }
    }

    private static void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    public static void createItem(Input input, Tracker tracker) {
        System.out.println("=== Create a new Item ====");
        System.out.print("Enter name: ");
        String name = input.askStr("");
        Item item = new Item(name);
        tracker.add(item);
    }

    public static void showAllItems(Tracker tracker) {
        System.out.println("Список всех заявок");
        Item[] items =  tracker.findAll();
        for (Item item : items) {
            System.out.println(item.getName());
        }
        System.out.println();
    }

    public static void replaceItem(Input input, Tracker tracker) {
        System.out.println("заменить заявку, по id");
        System.out.print("Enter id: ");
        // int id = input.askStr("");  // на будущие
        System.out.print("Enter name: ");
        String name =  input.askStr("");
        Item item = new Item(name);
        // Для теста
        Item test = new Item("test");

        tracker.replace(test.getId(), item);
        System.out.println();
    }

    public static void deleteItem(Input input, Tracker tracker) {
        System.out.println("Удалить заявку по id");
        System.out.print("Enter id: ");
        // int id =  input.askStr("");  // на будущие
        // Для теста
        Item test = new Item("test");

        tracker.delete(test.getId());
        System.out.println();
    }

    public static void findById(Input input, Tracker tracker) {
        System.out.println("Поиск заявок по id");
        System.out.print("Enter id: ");
        //      int id =  input.askStr("");  // на будущие
        // Для теста
        Item test = new Item("test");
        tracker.findById(test.getId());
        System.out.println();
    }

    public static void findByName(Tracker tracker) {
        System.out.println("Поиск заявок по названию");
        // Для теста
        Item test = new Item("test");
        tracker.findByName(test.getName());
        System.out.println();
    }





    public static void main(String[] args) {
        Input input = new ConsoleInput();
        Tracker tracker = new Tracker();
        new StartUI().init(input, tracker);
        System.out.println();
    }
}