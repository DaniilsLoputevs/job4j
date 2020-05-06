package ru.job4j.design.srp;

import java.util.List;
import java.util.function.Predicate;

/**
 * Store of {@code Employee}.
 */
public interface Store {

    /**
     * Search Employees by {@param filter}.
     *
     * @param filter - filter for search from all employees.
     * @return {@code List<Employee>} - employees after filter.
     */
    List<Employee> findBy(Predicate<Employee> filter);
}