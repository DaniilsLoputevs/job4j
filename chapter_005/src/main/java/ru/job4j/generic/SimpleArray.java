package ru.job4j.generic;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

public class SimpleArray<T> implements Iterable {

    /**
     * add(T model) - добавляет указанный элемент (model) в первую свободную ячейку;
     * set(int position, T model) - заменяет указанным элементом (model) элемент, находящийся по индексу position;
     * remove(int position) - удаляет элемент по указанному индексу, все находящиеся справа элементы при этом необходимо сдвинуть на единицу влево (в середине массива не должно быть пустых ячеек);
     * get(int position) - возвращает элемент, расположенный по указанному индексу;
     *
     * Также, реализуйте интерфейс Iterable<T> - метод iterator() возвращает итератор, предназначенный для обхода данной структуры.
     * Объект должен принимать количество ячеек. Структура не должна быть динамической. Если идет переполнение надо выкинуть ошибку.
     */

    private Object[] objects;
    private int position = 0;

    public SimpleArray(int size) {
        this.objects = new Object[size];
    }

    public void add(T value) {
        this.objects[position++] = value;
    }
    public void addAll(Collection<T> collection) {
        for (T item : collection) {
            this.add(item);
        }
    }
    public void set(int index, T value) {
        // это должно работать в пределах добавленных элементов - Andrei
        if (index < position) {
            this.objects[index] = value;
        } else {
            System.out.println("В массиве нет такой ячейки");
        }
    }
    public void remove(int index) {
        this.objects[index] = null;
        this.position--;
        System.arraycopy(objects, index + 1, objects, index, position - index);
    }
    public T get(int position) {
        return (T) this.objects[position];
    }


    public int getSize() {
        return this.position;
    }
    @Override
    public Iterator iterator() {
      return new SimpleArrayIterator(Arrays.copyOf(objects, position));
    }
}