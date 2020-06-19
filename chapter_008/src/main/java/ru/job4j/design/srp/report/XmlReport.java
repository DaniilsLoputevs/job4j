package ru.job4j.design.srp.report;

import daniils.DateHelper;
import daniils.StringHelper;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class XmlReport implements Report {
    @Override
    public String makeReport(Store store, Predicate<Employee> filter) {
        var tempList = new ArrayList<>(List.of(
                "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
        ));
        for (Employee employee : store.findBy(filter)) {
            var tempHired = DateHelper.dateFormat("yyy-MM-dd  HH:mm:ss", employee.getHired());
            var tempFired = DateHelper.dateFormat("yyy-MM-dd  HH:mm:ss", employee.getFired());
            List<String> loopList = List.of(
                    "<employee>",
                    "    <name>" + employee.getName() + "</name>",
                    "    <hired>" + tempHired + "</hired>",
                    "    <fired>" + tempFired + "</fired>",
                    "    <salary>" + employee.getSalary() + "</salary>",
                    "</employee>"
            );
            tempList.addAll(loopList);
        }
        var temp = StringHelper.separateLines(tempList);
        return StringHelper.mergeToOne(temp);
    }
}
