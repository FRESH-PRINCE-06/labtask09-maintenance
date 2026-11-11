package labtask08.task1;

import java.util.List;

/**
 * Lab Task 1 demo: legacy behaviour first, then the refactored version.
 */
public class RefactoredReportDemo {

    public static void main(String[] args) {
        List<String> data = List.of("Alice", "Bob", "Carol");

        System.out.println("=== BEFORE: legacy ReportManager ===");
        new labtask08.legacy.ReportManager().generate("PDF", data, true);

        System.out.println();
        System.out.println("=== AFTER: refactored ReportManager (PDF + email) ===");
        ReportManager manager = new ReportManager(new EmailNotifier());
        manager.generate("PDF", data, true);

        System.out.println();
        System.out.println("=== AFTER: refactored ReportManager (CSV, no email) ===");
        manager.generate("CSV", data, false);

        System.out.println();
        System.out.println("=== AFTER: null type no longer causes a NullPointerException ===");
        try {
            manager.generate(null, data, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Handled cleanly -> " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== AFTER: unsupported type is reported, not silently ignored ===");
        try {
            manager.generate("XML", data, false);
        } catch (IllegalArgumentException e) {
            System.out.println("Handled cleanly -> " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== AFTER: a fake Notifier can be injected (testable) ===");
        Notifier fake = (to, msg) -> System.out.println("[FakeNotifier] captured: " + to + " <- " + msg);
        new ReportManager(fake).generate("CSV", data, true);
    }
}
