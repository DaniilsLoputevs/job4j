package ru.job4j.list;

public class SimpleQueue<T> {

    private SimpleStack<T> input = new SimpleStack<>();
    private SimpleStack<T> output = new SimpleStack<>();
    private int size = 0;
    private int expectedSize = -1;

    public void push(T value) {
        input.push(value);
        this.size++;
    }

    /** Забирает последние добоалениое значение и возвращает.
     * @return T
     */
    public T poll() {
        if (size != expectedSize - 1) {
            for (int i = 0; i <= input.getSize() + 1; i++) {
                output.push(input.peek());
            }
            expectedSize = size;
        }
        this.size--;
        return output.peek();
    }

    /** Копирует последние добоалениое значение и возвращает.
     * @return T
     */
    public T peek() {
        return input.peek();
    }

    public int getSize() {
        return this.size;
    }

//    private boolean checkForModification() {
//        return modCount != expectedModCount;
//    }
}