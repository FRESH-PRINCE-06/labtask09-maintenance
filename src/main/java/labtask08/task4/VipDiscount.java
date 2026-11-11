package labtask08.task4;

import java.math.BigDecimal;

/**
 * Discount rule for VIP customers. OrderService depends on this abstraction,
 * so tests can supply a tiny in-memory implementation instead of a database.
 */
public interface VipDiscount {

    /** Returns the subtotal after the VIP discount has been applied. */
    BigDecimal apply(BigDecimal subtotal);
}
