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

    public int getSize() {
        return this.size;
    }

}