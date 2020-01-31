package ru.job4j.map;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.*;

public class SimpleMapTest {
    private SimpleMap map = new SimpleMap<String, User>();
    private User one = new User("Pavel", 1);
    private User two = new User("Artjom", 2);
    private User three = new User("Nikolaj", 3);

    @Before
    public void setUp() {
        map.insert("first", one);
        map.insert("second", two);
        map.insert("third", three);
    }

    @Test
    public void insert() {
        assertThat(map.getSize(), is(3));
    }

    @Test
    public void get() {
        assertThat(map.get("first"), is(one));
    }

    @Test
    public void delete() {
        assertThat(map.get("first"), is(one));
        map.delete("first");
        assertThat(map.get("first"), is(nullValue()));
        assertThat(map.getSize(), is(2));
    }

    @Test
    public void numberInKey() {
        SimpleMap newMap = new SimpleMap<Integer, String>();
        newMap.insert(1, "one");
        newMap.insert(2, "two");
        newMap.insert(3, "three");

        assertThat(newMap.get(1), is("one"));
        assertThat(newMap.get(2), is("two"));
    }


    /**
     * map.showTable - вспомогательный метод для Дебага.
     * Были проблемы с итератором.
     */
    @Test
    public void iterator() {
        var iterator = map.iterator();
//        map.showTable();


        assertThat(iterator.hasNext(), is(true));
//        System.out.println((iterator.next().toString()));
        assertNotNull(iterator.next());

        assertThat(iterator.hasNext(), is(true));
//        System.out.println((iterator.next().toString()));
        assertNotNull(iterator.next());

        assertThat(iterator.hasNext(), is(true));
//        System.out.println((iterator.next().toString()));
        assertNotNull(iterator.next());

        assertThat(iterator.hasNext(), is(false));
//        System.out.println((iterator.next().toString()));
    }

}