package labtask08.task3;

/**
 * Lab Task 3 demo: successful charges and one failing charge.
 * "Context Cleared" is printed in every case, including the failure.
 */
public class PaymentProcessorDemo {

    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        processor.charge("TX-1001", 2500.00);
        System.out.println();

        processor.charge("TX-1002", 799.99);
        System.out.println();

        try {
            processor.charge("TX-1003", -50.00);
        } catch (IllegalArgumentException e) {
            System.out.println("Payment failed -> " + e.getMessage());
        }
    }
}
