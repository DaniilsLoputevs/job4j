package ru.job4j.design.srp.report;

import ru.job4j.design.srp.Employee;
import ru.job4j.design.srp.Store;

import java.util.function.Predicate;

/**
 * Factory for making Report about {@code Employee} in different format.
 * <p>
 * Use {@code Enum ReportType} for chose format.
 * <p>
 * Default fabric/factory.
 */
public class ReportFactory {
    private Store store;

    public ReportFactory(Store store) {
        this.store = store;
    }

    public String create(ReportType reportType, Predicate<Employee> filter) {
        String result = "";
        if (ReportType.STRING == reportType) {
            result = new StringReport().makeReport(this.store, filter);
        } else if (ReportType.HTML == reportType) {
            result = new HtmlReport().makeReport(this.store, filter);
        } else if (ReportType.XML == reportType) {
            result = new XmlReport().makeReport(this.store, filter);
        } else if (ReportType.JSON == reportType) {
            result = new JsonReport().makeReport(this.store, filter);
        } else if (ReportType.HR == reportType) {
            result = new HrReport().makeReport(this.store, filter);
        } else if (ReportType.ACCOUNTING == reportType) {
            result = new AccountingReport().makeReport(this.store, filter);
        }
        return result;
    }
}
