package daniils;

import org.junit.Ignore;
import org.junit.Test;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Predicate;

public class DateHelperTest {

    private final HashMap<String, SoftReference<List<String>>> cacheMap = new HashMap<>();

    @Test
    public void devRun1() {
        String[][] strings = new String[1][1];
        String st = new String("test");

        class DateType {
        }
        class Manager implements Cloneable {
            public DateType one;
            public DateType two;

            public Manager(DateType one, DateType two) {
                this.one = one;
                this.two = two;
            }

            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
        }

        var one = new DateType();
        var two = new DateType();

        var a = new Manager(one, two);

        try {
            var b = a.clone();
            System.out.println("a in hashCode: " + a);
            System.out.println("b in hashCode: " + b);
            System.out.println("a && b - equals: " + a.equals(b));
//            System.out.println("" + );
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
    }

    //    @Ignore
    @Test
    public void devRun() {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 0};
        int[] arr1 = new int[]{1, 2};
        System.out.println(sumAllElementsInArr(arr, 0));
    }

    int sumAllElementsInArr(int[] arr, int startIndex) {
        var temp = arr[startIndex++];
        if (startIndex < arr.length) {
            temp += sumAllElementsInArr(arr, startIndex);
        }
        return temp;
    }

    @Test
    public void devRun2() {
        boolean one = true;
        boolean two = true;
        while (one && two) {
            System.out.println("Всё хорошо");
            one = false;
        }
        System.out.println("Что-то, не так...");
    }

    class UUU {
        //        private List test;
        private int test;

        public UUU(int test) {
            this.test = test;
        }
        //        public UUU(List test) {
//            this.test = test;
//        }
//        void set(int index, int value) {
//            this.test.set(index, value);
//        }

//        public List getTest() {
//            return test;
//        }

        public int getTest() {
            return test;
        }
    }

    class BBB {
        int num = 10;
    }

    @Ignore
    public void exp() {
        var test = new UserArray().filter(user -> user.name.equals("laptop"));
//        var test = new UserArray().filter(user -> user.age == 10);

    }

    class UserArray {
        List<User> userList = new ArrayList<>();

        public List<User> filter(Predicate<User> filter) {
            var result = new ArrayList<User>();

//            for (User user : userList) {
//                if (filter.test(user)) {
//
//                };
//                result.add(user);
//            }
            return result;
        }
    }

    class User {
        String name;
        int age;
    }


}