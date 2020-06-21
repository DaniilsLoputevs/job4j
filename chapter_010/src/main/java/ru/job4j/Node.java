package ru.job4j;

import javax.annotation.concurrent.Immutable;

@Immutable
public class Node<T> {
    private final Node next;
    private final T value;

    public Node(Node next, T value) {
        this.next = next;
        this.value = value;
    }

    public Node getNext() {
        return next;
    }

//    public void setNext(Node next) {
//        this.next = next;
//    }

    public T getValue() {
        return value;
    }

//    public void setValue(T value) {
//        this.value = value;
//    }
}
