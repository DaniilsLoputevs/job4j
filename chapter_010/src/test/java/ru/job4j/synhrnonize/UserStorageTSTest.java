package ru.job4j.synhrnonize;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UserStorageTSTest {
    private final User userOne = new User(1, 1000);
    private final User userTwo = new User(2, 50);
    private final UserStorage bank = new UserStorageTS();

    @Before
    public void setUp() throws Exception {
        List.of(userOne, userTwo).forEach(bank::add);
    }

    @Test
    public void transferTest() throws InterruptedException {
        var threadOne = new Thread(() -> bank.transfer(userOne.getId(), userTwo.getId(), 200));
        var threadTwo = new Thread(() -> bank.transfer(userOne.getId(), userTwo.getId(), 200));

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        assertEquals(600, bank.get(userOne.getId()).getAmount());
        assertEquals(450, bank.get(userTwo.getId()).getAmount());
    }

    @Test
    public void deleteUpdateTest() {
        bank.delete(new User(2, 0));

        bank.update(new User(1, 300));

        assertEquals(1, bank.size());
        assertTrue(bank.get(1).getAmount() != 1000);
    }
}