package ru.job4j.synhrnonize;

public interface UserStorage {
    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    /**
     * transfer money from user by{@param fromId} to user{@param toId}
     *
     * @param fromId-
     * @param toId-
     * @param amount  how much money to transfer.
     * @return is transaction successful or ont.
     */
    boolean transfer(int fromId, int toId, int amount);

    int size();
}
