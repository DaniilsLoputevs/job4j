package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Bank {
    private final Map<User, List<Account>> users = new HashMap();

    /** Добавление пользователя.
     * @param user - Пользоваиеть.
     */
    public void addUser(User user) {
        this.users.putIfAbsent(user, new ArrayList<>());
    }

    /** Удаление пользователя.
     * @param user - Пользоваиеть.
     */
    public void deleteUser(User user) {
        this.users.remove(user);
    }

    /** Добавить счёт пользователю.
     * @param passport - Пасспорт.
     * @param account - Счёт.
     */
    public void addAccountToUser(String passport, Account account) {
        User user = getUserByPassport(passport);
        this.users.get(user).add(account);
    }

    /** Удалить один счёт пользователя.
     * @param passport - Пасспорт.
     * @param account - Счёт.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User user = getUserByPassport(passport);
        this.users.get(user).remove(account);
    }

    /** Получить список счетов для пользователя.
     * @param passport - Пасспорт.
     */
    public List<Account> getUserAccounts(String passport) {
        return this.users.get(getUserByPassport(passport));
    }

    /** Метод для перечисления денег с одного счёта на другой счёт:
     *  если счёт не найден или не хватает денег на счёте srcAccount (с которого переводят) должен вернуть false.
     * @param srcPassport - Пасспорт Отправителя.
     * @param srcRequisite - Номер счёт Отправителя.
     * @param dstPassport - Пасспорт Получателя.
     * @param dstRequisite - Номер счёт Получателя.
     * @param amount - Сумма перевода.
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                  String dstPassport, String dstRequisite, double amount) {
        boolean result = false;
        User leftUser = getUserByPassport(srcPassport);
        Account leftAcc = getAccountByRequisite(srcRequisite);
        User rightUser = getUserByPassport(dstPassport);
        Account rightAcc = getAccountByRequisite(dstRequisite);

        if (leftUser != null && leftAcc != null && rightUser != null && rightAcc != null
                && belongAccToUser(leftUser, leftAcc) && belongAccToUser(rightUser, rightAcc)) {
            result = leftAcc.transferTo(rightAcc, amount);
        }
        return result;
    }


    public User getUserByPassport(String passport) {
        User result = null;
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                result = user;
                break;
            }
        }
        return result;
    }

    public Account getAccountByRequisite(String requisite) {
        Account result = null;
        for (List<Account> accounts : new ArrayList<>(users.values())) {
            for (Account acc : accounts) {
                if (acc.getRequisites().equals(requisite)) {
                    result = acc;
                    break;
                }
            }
        }
        return result;
    }

    public boolean belongAccToUser(User user, Account account) {
        boolean result = false;
        for (Account acc : users.get(user)) {
            if (acc.equals(account)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public void showUserAccounts(String passport) {
        for (Account acc : getUserAccounts(passport)) {
            System.out.println(acc.toString());
        }
    }
}