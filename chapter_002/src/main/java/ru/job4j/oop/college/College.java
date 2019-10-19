package ru.job4j.oop.college;

public class College {
    public static void main(String[] args) {

        Freshman freshMan = new Freshman();

        Student student = freshMan;

        Object object = freshMan;

        Object creative = new Freshman();

        Freshman newFresh = (Freshman) creative;
    }
}
