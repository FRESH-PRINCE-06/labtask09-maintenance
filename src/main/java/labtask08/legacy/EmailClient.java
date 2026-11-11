package labtask08.legacy;

/**
 * The static, hard-wired email helper used by the legacy code.
 * Because it is static, a caller can never swap it for a fake in a test.
 */
public class EmailClient {

    public static void sendStatic(String to, String body) {
        System.out.println("[EmailClient.sendStatic] To: " + to + " | Body: " + body);
    }
}
