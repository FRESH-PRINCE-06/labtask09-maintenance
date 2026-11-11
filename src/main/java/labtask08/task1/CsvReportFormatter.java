package labtask08.task1;

public class CsvReportFormatter implements ReportFormatter {

    @Override
    public String format(String body) {
        return "Writing CSV: " + body;
    }
}
