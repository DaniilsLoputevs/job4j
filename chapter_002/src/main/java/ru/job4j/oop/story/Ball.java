package ru.job4j.oop.story;

public class Ball {
    private String song = "Колобок, Колобок, \n"
            + "Колобок румяный бок, \n"
            + "Я по коробу скребен, \n"
            + "По сусеку я метен, \n"
            + "На сметане мешон, \n"
            + "На окошке стужон. \n"
            + "Я от дедушки ушел, \n"
            + "Я от бабушки ушел, \n";

    void songAdd(String add) {
        this.song += add;
    }

    void say(String say) {
        System.out.println(say);
    }

    void song() {
        System.out.print(song);
    }

    void dontEatMe(String who) {
        System.out.println("— Не ешь меня, " + who + ", я тебе песенку спою!");
    }

}
