package daniils;

public class ThreadsBehaviour {
    static Object lock = new Object();

    public static void main(String[] args) throws InterruptedException {

        int[] a = {1, 3, 5, 7, 9};
        int[] b = {2, 4, 6, 8, 10};

        Thread odd = new Thread(new Looper(a, lock));
        Thread even = new Thread(new Looper(b, lock));

        odd.start();
        even.start();

        odd.join();
        even.join();
    }

}

class Looper implements Runnable {

    int[] a;
    final Object lock;

    public Looper(int[] a, Object lock) {
        this.a = a;
        this.lock = lock;
    }

    @Override
    public void run() {

        for (int i = 0; i < a.length; i++) {
            synchronized (lock) {

                System.out.println(a[i]);
                try {
                    lock.notify();
                    if (i == (a.length - 1)) {
                        break;
                    }
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}