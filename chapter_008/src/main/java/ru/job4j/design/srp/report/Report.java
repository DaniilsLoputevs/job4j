package ru.job4j.design.srp.report;

import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Store;

import java.util.function.Predicate;

/**
 * interface: base model of Report
 * * Use for {@code ReportFactory}.
 */
public interface Report {

    /**
     * Make report in "class-name-format" and return it as String.
     * * example: HtmlReport.makeReport() - return report as String in Html formal.
     *
     * @param store - store that about will be report.
     * @param filter - filter for search from all employees.
     * @return - report in format as String.
     */
    String makeReport(Store store, Predicate<Employee> filter);
}
