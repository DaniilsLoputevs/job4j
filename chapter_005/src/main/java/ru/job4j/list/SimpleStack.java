package ru.job4j.list;

public class SimpleStack<T> {
    private int size = 0;

    private SimpleLinkedList<T> collection = new SimpleLinkedList<>();

    public void push(T value) {
        collection.add(value);
        this.size++;
    }

    /** Забирает последние добоалениое значение и возвращает.
     * @return T
     */
    public T poll() {
        this.size--;
        return collection.delete();
    }

    /** Копирует последние добоалениое значение и возвращает.
     * @return T
     */
    public T peek() {
        this.size--;
        return collection.get(size);
    }
    public int getSize() {
        return this.size;
    }

}