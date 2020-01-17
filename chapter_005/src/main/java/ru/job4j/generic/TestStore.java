package ru.job4j.generic;

public class TestStore extends AbstractStore {

    public TestStore(int size) {
        super(new SimpleArray<User>(size));
    }

//    public static void main(String[] args) {
//        TestStore test = new TestStore(10);
//        test.add(new User("1"));
//    }
}
