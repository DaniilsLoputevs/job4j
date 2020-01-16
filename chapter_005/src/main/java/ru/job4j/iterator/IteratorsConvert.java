package ru.job4j.iterator;

import java.util.Iterator;

public class IteratorsConvert {
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {
        return new Iterator<>() {

            private Iterator<Integer> insideIterator = it.next();

            private boolean condition() {
                return !insideIterator.hasNext();
            }
            @Override
            public boolean hasNext() {
                insideIterator = (condition() && it.hasNext())
                        ? it.next() : insideIterator;
                return insideIterator.hasNext();
            }

            @Override
            public Integer next() {
                insideIterator = (condition()) ? it.next() : insideIterator;
                return insideIterator.hasNext() ? insideIterator.next() : -1;
            }


            // Первая рабочая версия с Малым мульти-ретурном.
//            @Override
//            public boolean hasNext() {
//                var condition = !insideIterator.hasNext();
//                if (!it.hasNext() && condition) {
//                    return false;
//                }
//                insideIterator = (condition) ? it.next() : insideIterator;
//                return insideIterator.hasNext();
//            }


        };
    }


}