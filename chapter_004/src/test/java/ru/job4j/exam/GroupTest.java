package ru.job4j.exam;

import org.junit.Test;
import java.util.*;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class GroupTest {

    @Test
    public void sections() {
        Student a = new Student("Пётр", Set.of(
                "Коллекционировать учеников",
                "Коллекционировать машины",
                "Коллекционировать оружие",
                "Коллекционировать маски",
                "Коллекционировать сломаные геймпады"
        ));
        Student b = new Student("Андрей", Set.of(
                "Коллекционировать телефоны",
                "Коллекционировать машины",
                "Коллекционировать оружие",
                "Коллекционировать маски",
                "Коллекционировать сломаные геймпады"
        ));
        Student c = new Student("Раил", Set.of(
                "Коллекционировать телефоны",
                "Коллекционировать машины",
                "Коллекционировать оружие",
                "Коллекционировать маски",
                "Коллекционировать золото"
        ));
        ArrayList initCase = new ArrayList(List.of(a, b, c));

        Map expectedCase = new TreeMap(Map.of(
                "Коллекционировать маски", Set.of("Пётр", "Раил", "Андрей"),
                "Коллекционировать машины", Set.of("Пётр", "Раил", "Андрей"),
                "Коллекционировать оружие", Set.of("Пётр", "Раил", "Андрей"),
                "Коллекционировать учеников", Set.of("Пётр"),
                "Коллекционировать сломаные геймпады", Set.of("Пётр", "Андрей"),
                "Коллекционировать телефоны", Set.of("Раил", "Андрей"),
                "Коллекционировать золото", Set.of("Раил")));

        //Создал TreeMap - что бы отсортировать по порядку
        Map test = new TreeMap(Group.sections(initCase));

        assertThat(test, is(expectedCase));
    }
}