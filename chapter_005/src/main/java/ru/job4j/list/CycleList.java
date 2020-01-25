package ru.job4j.list;

public class CycleList<T> {
//    T value;
    CycleList<T> next;


    public static boolean hasCycle(CycleList first) {
        CycleList firstLink = first;
        CycleList secondLink = first;
        while (secondLink != null && secondLink.next != null) {
            firstLink = firstLink.next;
            secondLink = secondLink.next.next;
            if (firstLink == secondLink) {
                return true;
            }
        }
        return false;
    }

}