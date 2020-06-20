package ru.job4j.concurrent;

public class ConsoleProgress {
    public static void main(String[] args) {
        try {
            Thread progress = new Thread(new ConsoleProgressInside());
            progress.start();
            Thread.sleep(1000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
            progress.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static class ConsoleProgressInside implements Runnable {
        @Override
        public void run() {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    System.out.print("\r load: " + ".");
                    Thread.sleep(500);
                    System.out.print("\r load: " + "..");
                    Thread.sleep(500);
                    System.out.print("\r load: " + "...");
//                    System.out.print("\r load: " + - \ | / -);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
