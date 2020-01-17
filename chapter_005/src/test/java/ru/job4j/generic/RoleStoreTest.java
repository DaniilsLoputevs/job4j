package ru.job4j.generic;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    private RoleStore collection;
    private Role role0;
    private Role role1;

    @Before
    public void init() {
        collection = new RoleStore();
        role0 = new Role("0");
        role1 = new Role("1");
        collection.add(role0);
    }


    @Test
    public void add() {
        collection.add(role1);
        assertThat(collection.findById("1"), is(role1));
    }
    @Test
    public void replace() {
        assertTrue(collection.replace("0", role1));
    }
    @Test
    public void delete() {
        assertTrue(collection.delete("0"));
    }
    @Test
    public void findById() {
        assertThat(collection.findById("0"), is(role0));
    }

}