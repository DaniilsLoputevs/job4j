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
        if (user != null) {
            this.users.get(user).add(account);
        }
    }

    /** Удалить один счёт пользователя.
     * @param passport - Пасспорт.
     * @param account - Счёт.
     */
    public void deleteAccountFromUser(String passport, Account account) {
        User user = getUserByPassport(passport);
        if (user != null) {
            this.users.get(user).remove(account);
        }
    }

    /** Получить список счетов для пользователя.
     * @param passport - Пасспорт.
     */
    public List<Account> getUserAccounts(String passport) {
        User user = getUserByPassport(passport);
        List<Account> result = new ArrayList<>();
        if (user != null) {
            result = this.users.get(user);
        }
        return result;
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
        Account srcAcc = getUsersAcc(srcPassport, srcRequisite);
        Account dstAcc = getUsersAcc(dstPassport, dstRequisite);
        if (srcAcc != null && dstAcc != null) {
            result = srcAcc.transferTo(dstAcc, amount);
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

    /** Метод поиска аккаунта по паспорту и реквизитам
     * @param passport - пасспорт для поиска user
     * @param requisite - реквезиты для поиска acc в списке от user
     * @return Account/null - Есть такой acc у user/ Нету acc у user
     */
    public Account getUsersAcc(String passport, String requisite) {
        Account result = null;
        Account acc = getAccountByRequisite(requisite);
        if (getUserAccounts(passport).contains(acc)) {
        result = acc;
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