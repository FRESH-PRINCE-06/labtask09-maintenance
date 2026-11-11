package labtask08.task1;

public class PdfReportFormatter implements ReportFormatter {

    @Override
    public String format(String body) {
        return "Writing PDF: " + body;
    }
}
