package ru.job4j.StreamAPI;

import java.util.List;
import java.util.stream.Collectors;

public class StreamUsage {
    public static class Task {
        private final String name;
        private final long spent;

        public Task(String name, long spent) {
            this.name = name;
            this.spent = spent;
        }

        @Override
        public String toString() {
            return "Task{" +
                    "name='" + name + '\'' +
                    ", spent=" + spent +
                    '}';
        }
    }

    public static void main(String[] args) {
        List<Task> tasks = List.of(
                new Task("Bug #1", 100),
                new Task("Task #2", 100),
                new Task("Bug #3", 100)
        );
        List<Task> bugs = tasks.stream().filter(
                task -> task.name.contains("Bug")
        ).collect(Collectors.toList());
        bugs.forEach(System.out::println);

        //  Рассмотрим деталей.
        /*
         Рассмотрим деталей.
         tasks.stream() - получаем объект типа Stream
         filter( task -> task.name.contains("Bug") )
         Для этого объекта выполняем метод filter, который принимает лямбда выражение Predicate<Task>
         task -> task.name.contains("Bug")  - задаем условие. что пропускать только те задачи, которые содержат слово Bug.
         .collect(Collectors.toList()); - полученные результат сохранить в коллекции типа List.
         Давайте посмотрим аналог этого решения через for-each.
         List<Task> container = new ArrayList<>();
         for (Task task : tasks) {
            if ("BUG".equals(task.name)) {
                container.add(task);
            }
         }
         Если провести аналогию. то.
         for > stream()
         if > Predicate
         */

        // 2. Преобразование.
        /*
        2. Преобразование.
        Допустим, что на нужно получить только имена задач. Для этого нужно применить метод map.
        List<String> names = tasks.stream().map(
        task -> task.name
        ).collect(Collectors.toList());
        Рассмотрим аналог через for.
        List<String> container = new ArrayList<>();
        for (Task task : tasks) {
        container.add(task.name);
        }
         */

        // 3. Упрощение.
        /*
        3. Упрощение.
        Давайте теперь посчитаем общую сумму потраченную на все задачи.
        long total = tasks.stream().map(
        task -> task.spent
        ).reduce(0L, Long::sum);
        .reduce(0L, Long::sum); - каждое значение task.spent - нужно сложить с начальным значение 0L.
        В результате мы получим общее время потраченное на все задачи.
        Рассмотрим аналог.
        long total = 0L;
        for (Task task : tasks) {
          total += task.spent;
        }
         */

    }
}