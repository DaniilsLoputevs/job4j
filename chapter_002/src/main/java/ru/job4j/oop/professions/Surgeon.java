package ru.job4j.oop.professions;

public class Surgeon extends Doctor {

    public Surgeon (String name, String surname, String education, String birthday, Diagnose diagnose, String numberOfOperations) {
        super(name, surname, education, birthday, diagnose);
    }
}
