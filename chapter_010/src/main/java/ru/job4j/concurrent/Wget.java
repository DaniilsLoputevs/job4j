package ru.job4j.concurrent;

public class Wget {
    /**
     * Конспект для себя от 21.06.21.
     * <p>
     * - Thread main - создаём Thread tNew.
     * - в tNew - идёт загрузка через 1 сек.
     * - в main - ждём 4 сек и прерываем tNew.
     * -- т.к. в tNew в состоянии TIMED_WAITING - выкидывается InterruptedException.
     * -- далее мы её ловим и повторно прерываем нить, на этот раз она точно прервётся.
     * - в main ждём пока tNew закончит работу (tNew.join())
     *
     * @param args -
     * @throws InterruptedException -
     */
    public static void main(String[] args) throws InterruptedException {
        Thread tNew = new Thread(
                () -> {
                    try {
                        System.out.println("Start loading ... ");
                        for (int index = 0; index <= 100; index++) {
                            System.out.print("\rLoading : " + index + "%");
                            Thread.sleep(1000); // 1 sec
                        }
                        System.out.println(System.lineSeparator() + "Loaded.");
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();

                        System.out.println();
                        System.out.println("InterruptedException report: ");
                        System.out.println("Is thread interrupted: " + Thread.currentThread().isInterrupted());
                    }
                }
        );
        tNew.start();
        Thread.sleep(4000); // 4 sec
        tNew.interrupt();
        tNew.join();
    }
}
