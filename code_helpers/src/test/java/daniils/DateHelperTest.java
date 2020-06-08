package daniils;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class DateHelperTest {

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

    @Ignore
    public void devRun2() {
//        var player = new BasePlayer("test", 'O');
//        field.writeOnField(4,2, player.getSign());
//        field.showField();


//        var one = new UUU();
//        var two = new UUU();
//        System.out.println(one.getBbb().equals(two.getBbb()));

//        var temp = new ArrayList<>(List.of("111", "222"));
        var temp = 10;
        UUU one = new UUU(temp);
        UUU two = new UUU(temp);

//        System.out.println(ttt.equals(aaa));

//        System.out.println(temp.toString());
        System.out.println(temp);
//        System.out.println(one.getTest().toString());
//        System.out.println(two.getTest().toString());
        System.out.println(one.getTest());
        System.out.println(two.getTest());

        System.out.println("START");

//        temp.add(2, "333");
        temp = 15;
        System.out.println(temp);
//        System.out.println(one.getTest().toString());
//        System.out.println(two.getTest().toString());
        System.out.println(one.getTest());
        System.out.println(two.getTest());


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