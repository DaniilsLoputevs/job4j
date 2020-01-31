package ru.job4j.map;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Реализовывать итератор.
 * Внутренняя реализация должна использовать массив. Нужно обеспечить фиксированное время вставки и получение.
 * Предусмотрите возможность роста хэш-таблицы при нехватке места для нового элемента.
 *
 * @param <K> Ключ.
 * @param <V> Значение.
 */
public class SimpleMap<K, V> implements Iterable<V> {
    private int size = 0;
    private int capacity = 16;

    private Node[] table = new Node[capacity];

    /* ------------------------------------------------------------ */
    // Основные методы

    public boolean insert(K key, V value) {
        var result = false;
        var keyHash = hash(key);

        if (size == this.capacity) {
            reSizeMap();
        }
        // push
        if (!checkHashTable(key)) {
            result = push(keyHash, value, indexByHash(key));
        } else {
            result = push(keyHash, value, freeIndex());
        }
        return result;
    }

    V get(K key) {
        V result = null;
        int index = indexByHash(key);
        if (checkHashTable(key)) {
            if (compareHash(index, key)) {
                result = (V) table[index].getValue();
            } else {
                result = getFromTable(key);
            }
        }
        return result;
    }
    public boolean delete(K key) {
        var result = false;
        if (checkHashTable(key)) {
            table[indexByHash(key)] = null;
            size--;
            result = true;
        }
        return result;
    }

    public int getSize() {
        return this.size;
    }

    /* ------------------------------------------------------------ */
    // Внутрение методы

    private boolean push(int hash, V value, int index) {
        table[index] = new Node<>(hash, value);
        this.size++;
        return true;
    }
    private int indexByHash(K key) {
        var result = capacity - 1 & key.hashCode();
        return result;
    }
    /** Есть ли: в таблице такой Улюч
     * @param key - Ключ
     * @return true/false - Обнаружен такой Хэш или нет.
     */
    private boolean checkHashTable(K key) {
        var localHash = hash(key);
        var result = false;
        if (size > 0) {
            for (Node node : table) {
                if (node != null && node.getHash() == localHash) {
                    result = true;
                    break;
                }
            }
        }
        return result;
    }
    private boolean compareHash(int indexInTable, K key) {
        return table[indexInTable].getHash() == key.hashCode();
    }
    private int hash(K key) {
        int result;
        if (key.getClass().equals(Number.class)) {
            result = (int) key;
        } else {
            result = key.hashCode();
        }
        return result;
    }

    /** Метод дял избежания Лишней вложености в методе: V get(key)
     *  Поиск по всему массиву, в случаии колизии.
     * @return V value
     */
    private V getFromTable(K key) {
        V result = null;
        for (int i = 0; i < table.length; i++) {
            if (compareHash(i, key)) {
                result = (V) table[i].getValue();
                break;
            }
        }

        return result;
    }

    private int freeIndex() {
        var result = -1;
        for (int i = 0; i < this.capacity; i++) {
            if (table[i] == null) {
                result = i;
                break;
            }
        }
        return result;
    }
    private void reSizeMap() {
        table = Arrays.copyOf(table, capacity * 2);
    }

    // метод для теста (Дебаг)
    public void showTable() {
        var index = 0;
        for (Node node : table) {
            if (node != null) {
                System.out.println(index + " " + node.getHash() + " " + node.getValue());
            }
            index++;
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new LocalIterator();
    }

    /* ------------------------------------------------------------ */
    // Внутрение Классы: Node, Iterator

    private class Node<K, V> {
        private int hash;
        private V value;

        private int getHash() {
            return hash;
        }
        private V getValue() {
            return value;
        }

        Node(int hash, V value) {
            this.hash = hash;
            this.value = value;
        }
    }


    /** Итератор ходит по value
     * Вызов hasNext перед next ОБЯЗАТЕЛЕН! Иначе, будет NPE!
     */
    private class LocalIterator implements Iterator<V> {
        private int position = 0;

        @Override
        public boolean hasNext() {
            var result = false;
            // Поиск по всему массиву, след. элемента.
            for (int i = position; i < table.length; i++) {
                if (table[i] != null) {
                    position = i;
                    result = true;
                    break;
                }
            }

            return result;
        }
        @Override
        public V next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (V) table[position++].getValue();
        }
    }

}