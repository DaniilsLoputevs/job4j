package ru.job4j.design.srp.report;

import daniils.DateHelper;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Store;

import java.util.function.Predicate;

public class StringReport implements Report {
    @Override
    public String makeReport(Store store, Predicate<Employee> filter) {
        var text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            var tempHired = DateHelper.dateFormat("yyy-MM-dd  HH:mm:ss", employee.getHired());
            var tempFired = DateHelper.dateFormat("yyy-MM-dd  HH:mm:ss", employee.getFired());
            text.append(employee.getName()).append(";")
                    .append(tempHired).append(";")
                    .append(tempFired).append(";")
                    .append(employee.getSalary()).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}
