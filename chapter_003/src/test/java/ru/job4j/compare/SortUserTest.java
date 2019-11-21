package ru.job4j.compare;

import org.junit.Test;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test // Проверка User.compareTo()
    public void compareTo() {
        User one = new User("a", 20);
        User two = new User("b", 30);
        User three = new User("c", 40);

        // Создаёем TreeSet и добавляем в него пользователей. (хаотично)
        TreeSet sort = new TreeSet<User>();
        sort.addAll(Arrays.asList(three, two, one));

        // Создаёем ArrayList и передаём в него наш TreeSet(sort)
        List<User> sortArray = new ArrayList<>();
        sortArray.addAll(sort);

        // Создаёем ArrayList справельной поочерёдностью(сортировка)
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(one, two, three));

        assertThat(sortArray, is(list));
        /*
         * Я не мог сравнить напрямую sort и list, поэтому перекинул sort в ArrayList
         */
    }
    @Test
    public void sort() {
        List<User> example = new ArrayList<>();
        User one = new User("a", 20);
        User two = new User("b", 30);
        User three = new User("c", 40);
        User four = new User("c", 40);
        User five = new User("d", 50);
        example.addAll(Arrays.asList(three, two, one, five, four)); // 3, 2, 1, 5, 4

        Set<User> example1 = new TreeSet<>();
        example1.addAll(example);

        assertThat(SortUser.sort(example), is(example1)); // 20, 30, 40, 50
    }

}
