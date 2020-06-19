package ru.job4j.concurrent;

/**
 * заметки:
 * Функциональные интерфейсы можно можно реализовать без класса, через лямбду описать метод.
 */
public class ConcurrentOutput {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        first.setName("zero - thread");
        Thread second = new Thread(
                () -> System.out.println(Thread.currentThread().getName())
        );
        second.setName("second - thread");

//        Thread first = new Thread(
//                new Runnable() {
//                    @Override
//                    public void run() {
//                        System.out.println(Thread.currentThread().getName());
//                    }
//                }
//        );

        first.start(); // запускает нить + код из Stack под эту нить.
//        first.run(); // запускает только код из Stack под эту нить. (запуск будет в старой нити, а не в новой)
        second.start();
        try {
//            Thread.currentThread().sleep(50); - полная версия метода.
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
        /*
        Console output:
        zero - thread
        second - thread
        main
         */
    }
}
