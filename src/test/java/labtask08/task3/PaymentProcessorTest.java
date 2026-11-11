package labtask08.task3;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PaymentProcessorTest {

    @Test
    @DisplayName("The trace context is cleared after a successful charge")
    void testContextClearedAfterSuccess() {
        new PaymentProcessor().charge("TX-T1", 100.0);
        assertNull(PaymentProcessor.currentTraceId());
    }

    @Test
    @DisplayName("The trace context is STILL cleared when the charge fails (finally block)")
    void testContextClearedAfterFailure() {
        PaymentProcessor processor = new PaymentProcessor();
        assertThrows(IllegalArgumentException.class, () -> processor.charge("TX-T2", -5.0));
        assertNull(PaymentProcessor.currentTraceId());
    }
}
