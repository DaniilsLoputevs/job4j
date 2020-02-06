package ru.job4j.tree;

import java.util.Optional;

public interface TreeInterface<E extends Comparable<E>> extends Iterable<E> {

    boolean add(E parent, E child);

    Optional<SimpleTree<E>.Node<E>> findBy(E value);
}
