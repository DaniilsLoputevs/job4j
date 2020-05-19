package ru.job4j.design.srp.report;

import com.google.gson.JsonObject;
import daniils.DateHelper;
import daniils.StringHelper;
import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class JsonReport implements Report {
    @Override
    public String makeReport(Store store, Predicate<Employee> filter) {
        var tempList = new ArrayList<String>();
        for (Employee employee : store.findBy(filter)) {
            var tempHired = DateHelper.dateFormat("yyy-MM-dd  HH:mm:ss", employee.getHired());
            var tempFired = DateHelper.dateFormat("yyy-MM-dd  HH:mm:ss", employee.getFired());

            JsonObject person = new JsonObject();
            person.addProperty("name", employee.getName());
            person.addProperty("hired", tempHired);
            person.addProperty("fired", tempFired);
            person.addProperty("salary", employee.getSalary());

            var temp = person.toString().split(",");
            List<String> loopList = List.of(
                    temp[0],
                    temp[1],
                    temp[2],
                    temp[3]
            );
            tempList.addAll(loopList);
        }
        var temp = StringHelper.separateLines(tempList);
        return StringHelper.mergeToOne(temp);
    }
}
