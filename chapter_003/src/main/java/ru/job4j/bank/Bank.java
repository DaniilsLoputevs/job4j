package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    /** Поиск User по паспорту.
     * @param passport - Пасспорт.
     * @return User - Пользователь.
     */
    public User getUserByPassport(String passport) {
        return users.keySet().stream()
                .filter(user -> user.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**Поиск Account по Реквизитам.
     * @param requisite - Реквизиты.
     * @return Account - Счёт.
     */
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

    /** Поиск Account по паспорту && реквизитам
     * @param passport - пасспорт для поиска user
     * @param requisite - реквезиты для поиска Account у User
     * @return Account/null - Есть такой Account у user/ Нету acc у user
     */
    public Account getUsersAcc(String passport, String requisite) {

        return users.get(this.getUserByPassport(passport)).stream()
                .filter(account -> account.equals(this.getAccountByRequisite(requisite)))
                .findFirst()
                .orElse(null);
    }

    public void showUserAccounts(String passport) {
        for (Account acc : getUserAccounts(passport)) {
            System.out.println(acc.toString());
        }
    }
}

// Блок с доп инфой по stream().methods()
/*
//         List<User> result = users.keySet().stream()
//                .filter(user -> user.getPassport().equals(passport))
//                .collect(Collectors.toList());
//        return result.get(0);

        // Пример для большего понимания
        // return list.stream().distinct().collect(
        //                Collectors.toMap(
        //                        student -> student,
        //                        Student::getName
        //                ));
        //    }

        // Пример из инета.
////Map -> Stream -> Filter -> String
//        String result = map.entrySet().stream()
//                .filter(x -> "something".equals(x.getValue()))
//                .map(x->x.getValue())
//                .collect(Collectors.joining());
 */