package ru.job4j.list;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleLinkedList<E> implements Iterable<E>  {
    private int size;
    private Node<E> first;
    private int modCount = 0;

    public void add(E data) {
        Node<E> newLink = new Node<>(data);
        newLink.next = this.first;
        this.first = newLink;
        this.size++;
    }
    public E get(int index) {
        Node<E> result = this.first;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result.data;
    }
    public E delete() {
        // Копируем first
        Node<E> firstOne = first;
        if (firstOne == null) {
            throw new NoSuchElementException();
        }
        // Сохраняем возвращаемый результат
        E result = firstOne.data;
        // Сохраняем ссылку на след. Node
        Node<E> nextLink = firstOne.next;

        // Переназначаем first, След. Node
        first = nextLink;

        this.size--;
        return result;
    }
    public int getSize() {
        return this.size;
    }

    @Override
    public Iterator<E> iterator() {
        return new LocalIterator();
    }



    private static class Node<E> {
        private E data;
        private Node<E> next;

        Node(E data) {
            this.data = data;
        }
    }



    private class LocalIterator implements Iterator<E> {
        private int position = 0;
        private int expectedModCount = modCount;
        private Node<E> main; // Нулевой node и далее
        private Node<E> next = first; // Первый node и далее

        @Override
        public boolean hasNext() {
            checkForModification();
            return size > position;
        }
        @Override
        public E next() {
            checkForModification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            // Логически сдвигаем указатель на +1
            main = next;
            next = next.next;
            // Фактически сдвигаем указатель на +1
            position++;
            return main.data;
        }

        void checkForModification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }


}


