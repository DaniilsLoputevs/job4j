package ru.job4j.map;

import java.util.HashMap;
import java.util.List;

public class UserConvert {
    public static HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<>();
        for (User user : list) {
            result.put(Integer.valueOf(user.getId()), user);
        }
        return result;
    }
}
/*
 который принимает в себя список пользователей и конвертирует его в
  Map с ключом Integer id и соответствующим ему User.
 */