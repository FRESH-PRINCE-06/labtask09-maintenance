package labtask08.task1;

/**
 * Smell 4 fix: ReportManager depends on this abstraction (injected through
 * the constructor) instead of the static EmailClient, so tests can pass a fake.
 */
public interface Notifier {

    void send(String recipient, String message);
}
