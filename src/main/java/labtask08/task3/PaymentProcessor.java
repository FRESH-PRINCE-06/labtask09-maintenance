package labtask08.task3;

import java.util.Objects;

/**
 * Simulates SLF4J MDC (Mapped Diagnostic Context) with a ThreadLocal.
 * The trace id is put in at the start and ALWAYS removed in a finally block,
 * so it can never leak to the next request that reuses the same pooled thread.
 */
public class PaymentProcessor {

    private static final ThreadLocal<String> TRACE_ID = new ThreadLocal<>();

    public void charge(String txId, double amount) {
        Objects.requireNonNull(txId, "txId must not be null");

        TRACE_ID.set(txId);                                   // like MDC.put("traceId", txId)
        try {
            System.out.println("[TraceID: " + txId + "] Processing payment...");
            if (amount <= 0) {
                throw new IllegalArgumentException("amount must be positive but was " + amount);
            }
            System.out.println("[TraceID: " + txId + "] Charged Rs. " + amount);
            System.out.println("[TraceID: " + txId + "] Payment successful");
        } finally {
            TRACE_ID.remove();                                // like MDC.clear()
            System.out.println("[TraceID: " + txId + "] Context Cleared");
        }
    }

    /** The trace id currently bound to this thread, or null if none (used by tests). */
    static String currentTraceId() {
        return TRACE_ID.get();
    }
}
