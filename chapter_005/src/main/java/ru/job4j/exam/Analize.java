package ru.job4j.exam;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/** Нужно понять:
 * Сколько добавлено новых пользователей.
 * Сколько изменено пользователей. Изменённым считается объект в котором изменилось имя. а id осталось прежним.
 * Сколько удалено пользователей.
 * Обязательно напишите тесты.
 */
public class Analize {

    /**
     * @param previous - начальные данные.
     * @param current - измененные данные.
     * @return Info
     */
    public Info diff(List<User> previous, List<User> current) {
        var info = new Info();

        for (User left : previous) {
            if (checkId(left, current)) {
                if (current.contains(left)) {
                    info.changed++;
                }
            } else {
                info.deleted++;
            }
        }
        info.added = current.size() - (previous.size() - info.deleted);
        return info;
    }

    private boolean checkId(User user, List<User> current) {
        var result = false;
        for (var right : current) {
            if (user.getId() == right.getId()) {
                result = true;
            }
        }
        return result;
    }

//    public Info diff(List<User> previous, List<User> current) {
//        Info temp = new Info();
//        Map<Integer, User> mPre = previous.stream().collect(Collectors.toMap(User::getId, e -> e));
//        Map<Integer, User> mCur = current.stream().collect(Collectors.toMap(User::getId, e -> e));
//        for (var e : mPre.keySet()) {
//            if (mCur.containsKey(e)) {
//                if (!mPre.get(e).equals(mCur.get(e))) {
//                    temp.changed++;
//                }
//            } else {
//                temp.deleted++;
//            }
//        }
//        temp.added = mCur.size() - (mPre.size() - temp.deleted);
//        return temp;
//
//    }


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