package ru.job4j.exam;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

/** Перевернуть Связанный список.
 * Задача: Написать метод revert() - который переворачивает Односвязный список.
 * Пример >>
 * было:  1 - 2 - 3 - 4 - 5
 * стало: 5 - 4 - 3 - 2 - 1
 *** Делаеться переназначение ссылки на след элемент, ссылкой на Предыдущий.
 */
public class RevertLinkedList<T> implements Iterable<T> {
    private Node<T> head;

    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addAll(Collection<? extends T> c) {
        c.forEach(this::add);
    }

    /** Перевернуть Список.
     * Кратко: идёя по всему списку, меняем направленность соседних ссылок, на противоположную.
     *
     * Детально:
     * Есть буфферная констррукция которая двигаеться по Реальному List и меняет ссылку на лед. элемент.
     * Конструкция состоит из:
     * previous - прошедший элемент.
     * current - точька отсчёта.
     * forward - след элемент.
     *
     * Подробнее смотреть в комментария в коде самого метода.
     *
     * Результат работы:
     * Итерация 1
     *      * было:
     *      * 1 -> 2 -> 3 -> 4 -> 5 -> null
     *      * Стало:
     *      * 1 -> [null] 2 -> 3 -> 4 -> 5 -> null
     *      *** 1.next = null
     *
     * Итерация 2
     *      * было:
     *      * 1 -> [null] 2 -> 3 -> 4 -> 5 -> null
     *      * Стало:
     *      * 1 -> [null] 2 -> 1 -> [null] 3 -> 4 -> 5 -> null
     *      * 2 -> 1 -> [null] 3 -> 4 -> 5 -> null
     *
     * Итерация 2
     *      * было:
     *      * 2 -> 1 -> [null] 3 -> 4 -> 5 -> null
     *      * Стало:
     *      * 1 -> [null] 2 -> [null] 3 -> [null] 4 -> 5 -> null
     *      * 3 -> 2 -> 1 -> [null] 4 -> 5 -> null
     */
    public void revert() {
         Node<T>  previous = null;      //
         Node<T>  current = this.head;  //
         Node<T>  forward;              //

        // Меняем весь массив.
        while (current != null) {
            // сохраняем след. Элемент на будущие.
            forward = current.next;
            // Перезаписываем ссылку нынешнего элемента, на предыдущий. (при старре, это null, далее предыдушие "нынешнего")
            current.next = previous;
            // сохраняем предыдуший элемент. (при старре, это null, далее предыдушие "нынешнего")
            previous = current;
            // сменяем
            current = forward;
        }
        // после "Перевертывания списка" назначаем корень(head) на послед Перевернутый элемент.
        this.head = previous;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    public static class Node<T> {
        T value;
        Node<T> next;

        private Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

}
