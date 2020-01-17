package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserStoreTest {

    private UserStore collection;
    private User user0;
    private User user1;

    @Before
    public void init() {
        collection = new UserStore();
        user0 = new User("0");
        user1 = new User("1");
        collection.add(user0);
    }


    @Test
    public void add() {
        collection.add(user1);
        assertThat(collection.findById("1"), is(user1));
    }
    @Test
    public void replace() {
        assertTrue(collection.replace("0", user1));
    }
    @Test
    public void delete() {
        assertTrue(collection.delete("0"));
    }
    @Test
    public void findById() {
        assertThat(collection.findById("0"), is(user0));
    }


}