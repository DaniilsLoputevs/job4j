package ru.job4j.bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class AccountTest {
    private Account acc;

    @Before
    public void init() {
        acc = new Account(1000.00, "Novogorsk");
    }

    @Test
    public void transferTo() {
        Account test = new Account(0.00, "Kirovsk");
        acc.transferTo(test, 500);
        assertThat(acc.getValue(), is(test.getValue()));
    }

    @Test
    public void checkAmount() {
        Assert.assertTrue(acc.checkAmount(400));
    }
    @Test
    public void checkAmountFail() {
        Assert.assertFalse(acc.checkAmount(8888));
    }
}
