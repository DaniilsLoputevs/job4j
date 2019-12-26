package ru.job4j.bank;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class BankTest {
    private Bank bank;
    private User first;
    private User second;

    private User newUser;
    private Account testAcc;

    @Before
    public void init() {
        bank = new Bank();
        first = new User("Dima", "Ru_000");
        second = new User("Oljga", "Ru_170");
        bank.addUser(first);
        bank.addUser(second);
        bank.addAccountToUser(first.getPassport(), new Account(1000.00, "NovoKraj"));
        bank.addAccountToUser(first.getPassport(), new Account(00.00, "NovoKraj_2"));
        bank.addAccountToUser(second.getPassport(), new Account(00.00, "Sibirsk"));
        bank.addAccountToUser(second.getPassport(), new Account(1000.00, "Sibirsk_2"));

        newUser = new User("Ivan", "Lv_140");
        testAcc = new Account(1000.00, "Kiriljsk");
        bank.addUser(newUser);
    }

    // transferMoney - 3 tests
    @Test
    public void transferMoney() {
        bank.transferMoney("Ru_000", "NovoKraj",
                "Ru_170", "Sibirsk", 500.00);
        assertThat(bank.getAccountByRequisite("NovoKraj").getValue(),
                is(bank.getAccountByRequisite("Sibirsk").getValue()));
    }
    @Test // Если денег недостаточно, то баланс останется прежним.
    public void transferMoneyFail() {

        bank.transferMoney("Ru_000", "NovoKraj",
                "Ru_170", "Sibirsk", 5000.00);
        assertThat(bank.getAccountByRequisite("NovoKraj").getValue(),
                is(bank.getAccountByRequisite("NovoKraj").getValue()));
    }
    @Test // Оба счёта станут равными
    public void transferMoneySelfTransfer() {

        bank.transferMoney("Ru_000", "NovoKraj",
                "Ru_000", "NovoKraj_2", 500.00);
        assertThat(bank.getAccountByRequisite("NovoKraj").getValue(),
                is(bank.getAccountByRequisite("NovoKraj").getValue()));
    }

    @Test
    public void addUser() {
        bank.addUser(newUser);
        Assert.assertNotNull(bank.getUserByPassport(newUser.getPassport()));
    }

    @Test
    public void deleteUser() {
        bank.deleteUser(first);
        Assert.assertNull(bank.getUserByPassport(first.getPassport()));
    }

    @Test
    public void addAccountToUser() {
        bank.addUser(new User("Nikita", "Ru_890"));
        bank.addAccountToUser("Ru_890", testAcc);
        Assert.assertNotNull((bank.getUserAccounts("Ru_890")));
    }

    @Test
    public void deleteAccountFromUser() {
        bank.deleteAccountFromUser(first.getPassport(), bank.getAccountByRequisite("NovoKraj_2"));
        Assert.assertNull(bank.getAccountByRequisite("NovoKraj_2"));
    }

    // Technical tests
    @Test
    public void getUserByPassport() {
        Assert.assertEquals(bank.getUserByPassport("Ru_000"), first);
    }
    @Test
    public void getAccountByRequisite() {
        bank.addAccountToUser("Lv_140", testAcc);
        Assert.assertEquals(bank.getAccountByRequisite("Kiriljsk"), testAcc);
    }
    @Test
    public void getUsersAcc() {
        bank.addAccountToUser("Lv_140", testAcc);

        Assert.assertEquals(bank.getUsersAcc("Lv_140", "Kiriljsk"), testAcc);
    }
    @Test
    public void showUserAccounts() {
        bank.showUserAccounts("Ru_170");

    }
}
