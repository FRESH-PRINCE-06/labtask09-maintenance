package labtask08.task1;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Refactored ReportManager: all four smells removed.
 */
public class ReportManager {

    static final String ADMIN_EMAIL = "admin@company.com";

    private final Map<String, ReportFormatter> formatters;
    private final Notifier notifier;

    /** Convenience constructor: PDF and CSV formatters plus the given Notifier. */
    public ReportManager(Notifier notifier) {
        this(defaultFormatters(), notifier);
    }

    /** Smell 3 + 4: formatters and notifier are injected, not hard-coded. */
    public ReportManager(Map<String, ReportFormatter> formatters, Notifier notifier) {
        this.formatters = Map.copyOf(Objects.requireNonNull(formatters, "formatters must not be null"));
        this.notifier = Objects.requireNonNull(notifier, "notifier must not be null");
    }

    private static Map<String, ReportFormatter> defaultFormatters() {
        return Map.of(
                "PDF", new PdfReportFormatter(),
                "CSV", new CsvReportFormatter());
    }

    public String generate(String type, List<String> data, boolean sendEmail) {
        // Smell 2: fail fast with a clear message instead of a bare NullPointerException
        if (type == null) {
            throw new IllegalArgumentException("Report type must not be null");
        }
        Objects.requireNonNull(data, "data must not be null");

        ReportFormatter formatter = formatters.get(type);
        if (formatter == null) {
            throw new IllegalArgumentException("Unsupported report type: " + type);
        }

        // Smell 1: String.join builds the text in one pass (no loop, no trailing comma)
        String output = String.join(",", data);

        String report = formatter.format(output);   // Smell 3: no if/else on the type
        System.out.println(report);

        if (sendEmail) {
            notifier.send(ADMIN_EMAIL, output);     // Smell 4: injected dependency
        }
        return report;
    }
}
