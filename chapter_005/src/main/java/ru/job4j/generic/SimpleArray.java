package ru.job4j.generic;

import java.util.Collection;
import java.util.Iterator;

public class SimpleArray<T> implements Iterable {

    /**
     * add(T model) - добавляет указанный элемент (model) в первую свободную ячейку;
     * set(int index, T model) - заменяет указанным элементом (model) элемент, находящийся по индексу index;
     * remove(int index) - удаляет элемент по указанному индексу, все находящиеся справа элементы при этом необходимо сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек);
     * get(int index) - возвращает элемент, расположенный по указанному индексу;
     *
     * Также, реализуйте интерфейс Iterable<T> - метод iterator() возвращает итератор, предназначенный для обхода данной структуры.
     * Объект должен принимать количество ячеек. Структура не должна быть динамической. Если идет переполнение надо выкинуть ошибку.
     */

    private Object[] objects;
    private int index = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(T value) {
        this.objects[index++] = value;
    }
    public void addAll(Collection<T> collection) {
        for (T item : collection) {
            this.add(item);
        }
    }
    public void set(int index, T value) {
        this.objects[index] = value;
    }
    public void remove(int index) {
        this.objects[index] = null;
        for (int i = index; i < this.objects.length; i++) {
            this.objects[index] = this.get(index + 1);
        }
    }
    public T get(int position) {
        return (T) this.objects[position];
    }

    @Override
    public Iterator iterator() {
      return new SimpleArrayIterator(objects);
    }
}