package ru.job4j.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void test() {
        Map<User, Object> map = new HashMap<>();

        User first = new User("Анатолий", 1);
        User second = new User("Анатолий", 1);

        map.put(first, "Luminor Bank");
        map.put(second, "Nordea Bank");

//        System.out.println(first.equals(zero));

        System.out.println(map);
    }

    @Test
    public void tryTest() {
        int zero = 10;
        int a = 14;
        int b = 5;

        int index = a & b;
        int index2 = zero & b;


        System.out.println(index2);
        System.out.println(index);



    }


}