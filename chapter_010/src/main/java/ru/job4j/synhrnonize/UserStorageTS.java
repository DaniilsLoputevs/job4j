package ru.job4j.synhrnonize;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Map;

/**
 * User Storage Thread Safe.
 */
@ThreadSafe
public class UserStorageTS implements UserStorage {
    /**
     * key - user's id.
     * value - user.
     */
    @GuardedBy("this")
    private final Map<Integer, User> store = new HashMap<>();

    @Override
    public synchronized boolean add(User user) {
        return this.store.put(user.getId(), user) != null;
    }

    @Override
    public synchronized boolean update(User user) {
        if (this.store.containsKey(user.getId())) {
            this.store.replace(user.getId(), user);
        } else {
            this.store.put(user.getId(), user);
        }
        return true;
    }

    @Override
    public synchronized boolean delete(User user) {
        return this.store.remove(user.getId()) != null;
    }

    @Override
    public synchronized boolean transfer(int fromId, int toId, int amount) {
        var rsl = false;
        var userFrom = this.store.get(fromId);
        var userTo = this.store.get(toId);
        if (userFrom != null && userTo != null) {
            if (userFrom.getAmount() > amount && amount > 0) {
                synchronized (this) {
                    userFrom.changeAmount(-amount);
                    userTo.changeAmount(amount);
                    rsl = true;
                }
            } else {
                System.out.println("Not Enough money!");
            }
        }
        return rsl;
    }

    @Override
    public synchronized int size() {
        return this.store.size();
    }
}
