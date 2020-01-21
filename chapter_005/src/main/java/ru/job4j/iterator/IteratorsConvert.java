package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorsConvert {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> globalIterator) {
        return new Iterator<>() {


            private Iterator<Integer> insideIterator = globalIterator.next();

            @Override
            public boolean hasNext() {
                boolean result = false;
                if (globalIterator.hasNext() || insideIterator.hasNext()) {
                    while (!insideIterator.hasNext()) {
                        insideIterator = globalIterator.next();
                    }
                    result = insideIterator.hasNext();
                }
                return result;
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