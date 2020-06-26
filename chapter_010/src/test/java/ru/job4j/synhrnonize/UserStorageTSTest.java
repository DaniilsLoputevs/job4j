package ru.job4j.synhrnonize;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class UserStorageTSTest {

    @Test
    public void test() throws InterruptedException {
        var userOne = new User(1, 1000);
        var userTwo = new User(2, 50);
        UserStorage bank = new UserStorageTS();
        List.of(userOne, userTwo).forEach(bank::add);

        var threadOne = new Thread(() -> bank.transfer(userOne.getId(), userTwo.getId(), 200));
        var threadTwo = new Thread(() -> bank.transfer(userOne.getId(), userTwo.getId(), 200));

        threadOne.start();
        threadTwo.start();

        threadOne.join();
        threadTwo.join();

        bank.delete(new User(2, 0));

        assertEquals(1, bank.size());

        assertEquals(450, userTwo.getAmount());
        assertEquals(600, userOne.getAmount());
    }
}