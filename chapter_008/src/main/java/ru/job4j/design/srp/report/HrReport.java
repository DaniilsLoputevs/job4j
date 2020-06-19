package ru.job4j.design.srp.report;

import daniils.StringHelper;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Store;

import java.util.ArrayList;
import java.util.function.Predicate;

public class HrReport implements Report {
    @Override
    public String makeReport(Store store, Predicate<Employee> filter) {
        var result = new ArrayList<String>();
        result.add("Name; Salary;" + System.lineSeparator());
        var temp = store.findBy(filter);
        temp.sort((o1, o2) -> Double.compare(o2.getSalary(), o1.getSalary()));

        for (Employee employee : temp) {
            result.add(employee.getName() + ";");
            result.add(employee.getSalary() + ";");
            result.add(System.lineSeparator());
        }
        return StringHelper.mergeToOne(result);
    }
}
