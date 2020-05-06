package ru.job4j.design.srp;

import ru.job4j.helpers.StringHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Class for generate report of {@code Employee}.
 */
public class ReportEngine {
    private Store store;

    public ReportEngine(Store store) {
        this.store = store;
    }

    /**
     * Default method for generate report by {@param filter}.
     *
     * @param filter - filter for search from all employees.
     * @return - report, - employees info.
     */
    public String generate(Predicate<Employee> filter) {
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

    /**
     * Generate report of {@code Employee} by {@param filter} as String in HTML format.
     * You can push this String into file.
     *
     * @param filter - filter for search from all employees.
     * @return - report as String in HTML format.
     */
    public String generateHtml(Predicate<Employee> filter) {
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

    /**
     * Generate report of {@code Employee} by {@param filter} as String for HR department.
     * Name and Salary + sorted by Salary.
     *
     * @param filter - filter for search from all employees.
     * @return - report, - employees info.
     */
    public String generateForHr(Predicate<Employee> filter) {
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

    /**
     * Default method for generate report by {@param filter}.
     *
     * @param filter - filter for search from all employees.
     * @return - report, - employees info.
     */
    public String generateForAccounting(Predicate<Employee> filter) {
        var text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            var tempHired = DateHelper.dateFormat("yyy-MM-dd  HH:mm:ss", employee.getHired());
            var tempFired = DateHelper.dateFormat("yyy-MM-dd  HH:mm:ss", employee.getFired());
            text.append(employee.getName()).append(";")
                    .append(tempHired).append(";")
                    .append(tempFired).append(";")
                    .append(Integer.valueOf((int) employee.getSalary())).append(";")
                    .append(System.lineSeparator());
        }
        return text.toString();
    }

}