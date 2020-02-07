package ru.job4j.tree;

import java.util.*;

public class SimpleTree<E extends Comparable<E>> implements TreeInterface<E> {
    private Node<E> root;
    private int modCount = 0;

    public SimpleTree(E rootValue) {
        this.root = new Node<>(rootValue);
    }

    /**
     * Добавить элемент child в parent.
     * *Если child уже есть в дереве, то return = false
     * *Если Parent == null >> child пойдёт к root в children.
     * @param parent parentValue - ключ для поиска в дереве.
     * @param child childValue - Вставляемое значение.
     * @return true/false.
     */
    @Override
    public boolean add(E parent, E child) {
        var result = false;
        if (!findBy(child).isPresent()) {
            var parentOptional = findBy(parent);

            if (parentOptional.isPresent()) {
                parentOptional.get().add(new Node<>(child));
            } else {
                this.root.add(new Node<>(child));
            }
            modCount++;
            result = true;
        }
        return result;
    }

    /** поиск node<E> в дереве, по value.
     * Если value нет в дереве, то return = Optional.empty();
     * @param value - значение для поиска.
     * @return  Optional<Node<E>>
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.eqValue(value)) {
                rsl = Optional.of(el);
                break;
            }
            for (Node<E> child : el.leaves()) {
                data.offer(child);
            }
        }
        return rsl;
    }

    public boolean isBinary() {
       var queue = new LinkedList<>(List.of(root));
       Node<E> current = queue.poll();
       for (Node<E> child : current.leaves()) {
           queue.offer(child);
           if (current.leaves().size() > 2) {
               return false;
           }
           current = queue.poll();
       }
       return true;
    }

    @Override
    public Iterator<E> iterator() {
        return new LocalIterator(root);
    }

    /* ------------------------------------------------------------ */
    // Внутрение Классы: Node, Iterator

    /**
     * @author Petr Arsentev (parsentev@yandex.ru)
     * @version $Id$
     * @since 0.1
     */
    class Node<E extends Comparable<E>> {
        private final List<Node<E>> children = new ArrayList<>();
        private final E value;

        public E getValue() {
            return value;
        }

        public Node(final E value) {
            this.value = value;
        }

        public void add(Node<E> child) {
            this.children.add(child);
        }

        public List<Node<E>> leaves() {
            return this.children;
        }

        /** Сравнивает value с that
         * @param that E object
         * @return boolean
         */
        public boolean eqValue(E that) {
            return this.value.compareTo(that) == 0;
        }
    }


    private class LocalIterator implements Iterator<E> {
        Queue<Node<E>> data = new LinkedList<>();
        int countModIter = modCount;

        public LocalIterator(Node<E> root) {
            data.add(root);
        }

        @Override
        public boolean hasNext() {
            return !data.isEmpty();
        }

        /** Было сложно понять, как это всё сделать, т.к. теорию итератора по деревьям
         * пришлось собирать по сути с нуля. После +- 4 дней попыток и поисков теории по инету,
         * сделал, что получилось.
         *
         * В чате помогали, так же, но т.к. не было доступа к компу, пришлось уповать на инет.
         *
         * Суть такая: сначала пихаем корень в date, потом "Разархевируем" его "детей" в конец date
         * и возвращаем корень. След. берём "первого ребёнка" и "Разархевируем" его "детей" в конец date
         * и возвращаем его самого. Далее всё повторяеться.
         * @return E
         */
        @Override
        public E next() {
            Node<E> result;
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            if (countModIter != modCount) {
                throw new NoSuchElementException("Tree is expired");
            }
            result = data.poll();
            for (Node<E> child : result.leaves()) {
                data.offer(child);
            }
            return result.getValue();
        }

    }
}