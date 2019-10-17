package ru.job4j.tracker;

import java.util.Scanner;

public class StartUI {

    public void init(Scanner scanner, Tracker tracker) {
        boolean run = true;
        // Для тест всего if else
        Item test = new Item("test");
        Item test1 = new Item("test1");
        Item test2 = new Item("test2");
        tracker.add(test);
        tracker.add(test1);
        tracker.add(test2);
        while (run) {
            this.showMenu();
            System.out.print("Select: ");
            int select = Integer.valueOf(scanner.nextLine());

            if (select == 0) { // Add new Item
                System.out.println("=== Create a new Item ====");
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                Item item = new Item(name);
                tracker.add(item);
                System.out.println();

            } else if (select == 1) { // Show all items
                System.out.println("Список всех заявок");
                Item[] items =  tracker.findAll();
                for (Item item : items) {
                    System.out.println(item.getName());
                }
                System.out.println();

            } else if (select == 2) { // Edit item
                System.out.println("заменить заявку, по id");
                System.out.print("Enter id: ");
//                int id = scanner.nextInt();  // на будущие
                System.out.print("Enter name: ");
                String name = scanner.nextLine();
                Item item = new Item(name);
                tracker.replace(test.getId(), item);
                System.out.println();

            } else if (select == 3) { // Delete item
                System.out.println("Удалить заявку по id");
                System.out.print("Enter id: ");
//                int id = scanner.nextInt();  // на будущие
                tracker.delete(test.getId());
                System.out.println();

            } else if (select == 4) { // Find item by id
                System.out.println("Поиск заявок по id");
                System.out.print("Enter id: ");
//                int id = scanner.nextInt();  // на будущие
                tracker.findById(test.getId());
                System.out.println();

            } else if (select == 5) { // Find items by name
                System.out.println("Поиск заявок по названию");
                tracker.findByName(test.getName());
                System.out.println();

            } else if (select == 6) { // Exit Program
                run = false;
            }
        }
    }

    private void showMenu() {
        System.out.println("Menu.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Tracker tracker = new Tracker();
        new StartUI().init(scanner, tracker);
    }
}