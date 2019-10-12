package ru.job4j.oop;

public class Cat {

    private String food;
    private String name;

    public void eat(String meat) {
        this.food = meat;
    }
    public void giveNick(String nick) {
        this.name = nick;
    }
    public void show() {
        System.out.println(this.name + " - " + this.food);
        System.out.println(this.food);
    }
    public static void main(String[] args) {
        System.out.println("There are gav's food.");
        Cat gav = new Cat();
        gav.giveNick("Гав");
        gav.eat("котлетка с пюрешкой.");
        gav.show();
        System.out.println();

        System.out.println("There are black's food.");
        Cat black = new Cat();
        black.giveNick("Чёрный кот");
        black.eat("сушоная рыбка под пиво и футбол.");
        black.show();
    }
}
// У меня весёлое настроение сегодня. ))))))))