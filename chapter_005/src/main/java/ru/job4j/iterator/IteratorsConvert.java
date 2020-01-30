package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorsConvert {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> globalIterator) {
        return new Iterator<>() {


            private Iterator<Integer> insideIterator = globalIterator.next();

            /** Для себя в будущем. (ДСВБ)
             * while - меняет внутрений итератор(ВИ) Если ВИ кончился.
             * Именно while т.к. могут быть пустые ВИ после нынешнего ВИ.
             *
             * последовательно возвращае все занчения из всех ВИ. Пропускает пкстые ВИ.
             * @return true/false
             */
            @Override
            public boolean hasNext() {
                while (!insideIterator.hasNext() && globalIterator.hasNext()) {
                    //  globalIterator.hasNext() - здесь нужен т.к. в методе происходит вызов next(), перед ним
                    // Обязательно! должен быть вызов hasNext()
                    insideIterator = globalIterator.next();
                }
                return insideIterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return insideIterator.next();
            }


        };


    }
}