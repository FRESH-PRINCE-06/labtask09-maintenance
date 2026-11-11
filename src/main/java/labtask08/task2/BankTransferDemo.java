package labtask08.task2;

import java.math.BigDecimal;

/**
 * Lab Task 2 demo: one valid transfer, then two invalid ones.
 * Each invalid case fails IMMEDIATELY at construction time and the stack trace is printed.
 */
public class BankTransferDemo {

    public static void main(String[] args) {
        System.out.println("--- Case 1: valid transfer ---");
        BankTransfer ok = new BankTransfer("PK-001", "PK-002", new BigDecimal("2500.00"));
        System.out.println("Created: " + ok);

        System.out.println();
        System.out.println("--- Case 2: NEGATIVE amount ---");
        try {
            new BankTransfer("PK-001", "PK-002", new BigDecimal("-500.00"));
        } catch (RuntimeException e) {
            e.printStackTrace(System.out);
        }

        System.out.println();
        System.out.println("--- Case 3: NULL account ---");
        try {
            new BankTransfer(null, "PK-002", new BigDecimal("100.00"));
        } catch (RuntimeException e) {
            e.printStackTrace(System.out);
        }
    }
}
