package ru.job4j.pojo;

import java.util.Date;

public class College {
    public static void main(String[] args) {
        Student petja = new Student();
        petja.setName("Петя");
        petja.setSurname("Рахманин");
        petja.setPatronymic("Васильев");
        petja.setGroup(1);
        petja.setEnrolledTime(new Date());

        System.out.println("Имя: " + petja.getName());
        System.out.println("Фамилия: " + petja.getSurname());
        System.out.println("Отчество: " + petja.getPatronymic());
        System.out.println("Группа: " + petja.getGroup());
        System.out.println("Зачислен: " + petja.getEnrolledTime());

    }
}
