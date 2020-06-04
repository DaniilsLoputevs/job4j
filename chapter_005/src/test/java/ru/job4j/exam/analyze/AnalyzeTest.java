package ru.job4j.exam.analyze;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AnalyzeTest {
    private List<User> initList = new ArrayList<>();
    private List<User> newList = new ArrayList<>();

    @Before
    public void setUp() {
        initList.addAll(List.of(
                new User(1, "One"),
                new User(2, "Two"),
                new User(3, "Three"),
                new User(4, "Four"),
                new User(5, "Five"),
                new User(6, "Six")
        ));
    }

    /**
     * Разные коллекции:
     * размер: 6 - 6
     * Добавлено: 2
     * Изменено: 2
     * Удалено: 2
     */
    @Test
    public void infoOne() {
        newList.addAll(List.of(
                new User(1, "One"),
                new User(2, "Two"),
                new User(3, "Ten"),     // change
                new User(4, "Twenty"),  // change
                new User(15, "Five"),   // add
                new User(25, "Six")     // add
        ));

        var info = new Analyze.Info();
        info.added = 2;
        info.deleted = 2;
        info.changed = 2;

        var analise = new Analyze().diff(initList, newList);

        System.out.println("info:    " + info.toString());
        System.out.println("analise: " + analise.toString());

        assertEquals(analise, info);
    }


    /**
     * Разные коллекции:
     * размер: 6 - 4
     * Добавлено: 0
     * Изменено: 2
     * Удалено: 2
     */
    @Test
    public void infoTwo() {
        newList.addAll(List.of(
                new User(1, "One"),
                new User(2, "Two"),
                new User(3, "Thousand"),  // change
                new User(4, "Hundred")    // change
        ));

        var info = new Analyze.Info();
        info.added = 0;
        info.deleted = 2;
        info.changed = 2;

        var analise = new Analyze().diff(initList, newList);

        System.out.println("info:    " + info.toString());
        System.out.println("analise: " + analise.toString());

        assertEquals(analise, info);
    }
}