package labtask08.task1;

/**
 * Smell 3 fix: each output format is a small strategy behind this interface,
 * so adding a new format never requires editing ReportManager.
 */
public interface ReportFormatter {

    /** Turns the already-joined report body into the final text for this format. */
    String format(String body);
}
