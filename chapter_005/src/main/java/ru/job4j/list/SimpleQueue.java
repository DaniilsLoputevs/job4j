package ru.job4j.list;

public class SimpleQueue<T> {

    private SimpleStack<T> input = new SimpleStack<>();
    private SimpleStack<T> output = new SimpleStack<>();
    private int size = 0;
    private int modCount = 0;
    private int expectedModCount = -1;

    public void push(T value) {
        input.push(value);
        this.size++;
        this.modCount++;
    }

    public T poll() {
        if (modCount != expectedModCount) {
            for (int i = -1; i <= input.getSize(); i++) {
                output.push(input.peek());
            }

//
//            var e = input.poll();

//            while (e != null) {
//                output.push(e);
//                e = input.poll();
//            }

//
//            int i = 0;
//            while (i < input.getSize()) {
//                output.push(e);
//                e = input.poll();
//
//
//                i++;
//            }





            expectedModCount = modCount;
        }
        this.size--;
        return output.peek();
    }

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