package labtask08.task1;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ReportManagerTest {

    /** Hand-written fake: records what it was asked to send (no real email). */
    private static class FakeNotifier implements Notifier {
        final List<String> recipients = new ArrayList<>();
        final List<String> messages = new ArrayList<>();

        @Override
        public void send(String recipient, String message) {
            recipients.add(recipient);
            messages.add(message);
        }
    }

    private final List<String> data = List.of("Alice", "Bob", "Carol");

    @Test
    @DisplayName("Smell 1: data is joined without a trailing comma")
    void testJoinHasNoTrailingComma() {
        ReportManager manager = new ReportManager(new FakeNotifier());
        assertEquals("Writing PDF: Alice,Bob,Carol", manager.generate("PDF", data, false));
    }

    @Test
    @DisplayName("Smell 2: a null type fails fast with IllegalArgumentException, not NPE")
    void testNullTypeIsHandled() {
        ReportManager manager = new ReportManager(new FakeNotifier());
        assertThrows(IllegalArgumentException.class, () -> manager.generate(null, data, false));
    }

    @Test
    @DisplayName("Smell 3: CSV uses its own ReportFormatter")
    void testCsvFormatter() {
        ReportManager manager = new ReportManager(new FakeNotifier());
        assertEquals("Writing CSV: Alice,Bob,Carol", manager.generate("CSV", data, false));
    }

    @Test
    @DisplayName("Smell 3: an unsupported type is rejected instead of silently ignored")
    void testUnsupportedType() {
        ReportManager manager = new ReportManager(new FakeNotifier());
        assertThrows(IllegalArgumentException.class, () -> manager.generate("XML", data, false));
    }

    @Test
    @DisplayName("Smell 3: a brand-new format can be plugged in without editing ReportManager")
    void testNewFormatCanBePluggedIn() {
        ReportFormatter json = body -> "JSON: [" + body + "]";
        ReportManager manager = new ReportManager(java.util.Map.of("JSON", json), new FakeNotifier());
        assertEquals("JSON: [Alice,Bob,Carol]", manager.generate("JSON", data, false));
    }

    @Test
    @DisplayName("Smell 4: the injected Notifier receives the admin email")
    void testNotifierIsInjected() {
        FakeNotifier fake = new FakeNotifier();
        ReportManager manager = new ReportManager(fake);

        manager.generate("PDF", data, true);

        assertEquals(1, fake.messages.size());
        assertEquals("admin@company.com", fake.recipients.get(0));
        assertEquals("Alice,Bob,Carol", fake.messages.get(0));
    }

    @Test
    @DisplayName("Smell 4: no email is sent when sendEmail is false")
    void testNoEmailWhenFlagIsFalse() {
        FakeNotifier fake = new FakeNotifier();
        new ReportManager(fake).generate("CSV", data, false);
        assertTrue(fake.messages.isEmpty());
    }
}
