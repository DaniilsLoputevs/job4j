package ru.job4j.concurrent;

public class ConsoleProgress {
    public static void main(String[] args) {
        try {
            Thread progress = new Thread(new ConsoleProgressInside());
            progress.start();
            Thread.sleep(3500); /* симулируем выполнение параллельной задачи в течение 2 секунды. */
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
                    System.out.print("\r load: " + "\\");
                    Thread.sleep(500);
                    System.out.print("\r load: " + "|");
                    Thread.sleep(500);
                    System.out.print("\r load: " + "/");
                    Thread.sleep(500);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

    }

}
