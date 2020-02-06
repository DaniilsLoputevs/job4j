package ru.job4j.tree;

import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 * @author Petr Arsentev (parsentev@yandex.ru)
 * @version $Id$
 * @since 0.1
 */
public class SimpleTreeTest {
    SimpleTree<Integer> tree = new SimpleTree<>(1);
    SimpleTree<Integer> biTree = new SimpleTree<>(1);

    @Before
    public void setUp() {
        tree.add(1, 2);
        tree.add(1, 3);
        tree.add(1, 4);
        tree.add(4, 5);
        tree.add(5, 6);

        tree.add(6, 10);
        tree.add(6, 11);
        tree.add(6, 12);

        biTree.add(1, 10);
        biTree.add(1, 20);
        biTree.add(10, 30);
        biTree.add(20, 40);
        biTree.add(30, 50);
        biTree.add(40, 60);
    }

    @Test
    public void when6ElFindLastThen6() {
        assertThat(
                tree.findBy(6).isPresent(),
                is(true)
        );
    }

    @Test
    public void when6ElFindNotExitThenOptionEmpty() {
        SimpleTree<Integer> tree = new SimpleTree<>(1);
        tree.add(1, 2);
        assertThat(
                tree.findBy(7).isPresent(),
                is(false)
        );
    }

    @Test
    public void iterator() {
        var iterator = tree.iterator();

        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(1));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(2));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(3));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(4));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(5));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(6));
//        assertThat(iterator.hasNext(), is(false));
        assertThat(iterator.hasNext(), is(true));

        assertThat(iterator.next(), is(10));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(11));
        assertThat(iterator.hasNext(), is(true));
        assertThat(iterator.next(), is(12));
        assertThat(iterator.hasNext(), is(false));
    }

    @Test
    public void isBinary() {
        assertThat(tree.isBinary(), is(false));
        assertThat(biTree.isBinary(), is(true));
    }

}