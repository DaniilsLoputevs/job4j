package ru.job4j.exam;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/** Нужно понять:
 * Сколько добавлено новых пользователей.
 * Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя, а id осталось прежним.
 * Сколько удалено пользователей.
 * Обязательно напишите тесты.
 */
public class Analize {

    /** Собирает инф о разности между версиями коллекций
     * @param previous - начальные данные.
     * @param current - измененные данные.
     * @return Info
     */
    public Info diff(List<User> previous, List<User> current) {
        var info = new Info();
        Set<Integer> idSet = current.stream().map(User::getId).collect(Collectors.toSet());

        for (User left : previous) {
            if (!idSet.contains(left.getId())) {
                info.deleted++;
            } else if (current.contains(left)) {
                info.changed++;
            }
        }
        info.added = current.size() - (previous.size() - info.deleted);
        return info;
    }

    public static class Info {
        int added;
        int changed;

        int deleted;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;
            return added == info.added
                    && changed == info.changed
                    && deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            return Objects.hash(added, changed, deleted);
        }

        @Override
        public String toString() {
            return "Info{"
                    + "added=" + added
                    + ", changed=" + changed
                    + ", deleted=" + deleted
                    + '}';
        }
    }


}