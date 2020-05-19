package ru.job4j.design.srp.report;

import daniils.DateHelper;
import daniils.StringHelper;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class HtmlReport implements Report {
    @Override
    public String makeReport(Store store, Predicate<Employee> filter) {
        List<String> list = List.of(
                "<!DOCTYPE html>",
                "<html>",
                "<table>",
                "<caption>Employee</caption>",
                "    <tr>",
                "        <th>Name</th>",
                "        <th>Hired</th>",
                "        <th>Fired</th>",
                "        <th>Salary</th>",
                "    </tr>"
        );
        var tempList = new ArrayList<>(list);
        for (Employee employee : store.findBy(filter)) {
            var tempHired = DateHelper.dateFormat("yyy-MM-dd  HH:mm:ss", employee.getHired());
            var tempFired = DateHelper.dateFormat("yyy-MM-dd  HH:mm:ss", employee.getFired());
            List<String> loopList = List.of(
                    "    <tr>",
                    "        <td>" + employee.getName() + "</td>",
                    "        <td>" + tempHired + "</td>",
                    "        <td>" + tempFired + "</td>",
                    "        <td>" + employee.getSalary() + "</td>",
                    "    </tr>"
            );
            tempList.addAll(loopList);
        }
        tempList.add("</table>");
        tempList.add("</html>");
        var temp = StringHelper.separateLines(tempList);
        return StringHelper.mergeToOne(temp);
    }
}
