package labtask08.task1;

/**
 * Production implementation of Notifier (simulated email).
 */
public class EmailNotifier implements Notifier {

    @Override
    public void send(String recipient, String message) {
        System.out.println("[EmailNotifier] To: " + recipient + " | Body: " + message);
    }
}
