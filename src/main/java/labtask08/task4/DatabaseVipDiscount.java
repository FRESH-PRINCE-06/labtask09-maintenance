package labtask08.task4;

import java.math.BigDecimal;

/**
 * Production-style implementation that would read the discount rate from a database.
 * Unit tests must NEVER use this class.
 */
public class DatabaseVipDiscount implements VipDiscount {

    @Override
    public BigDecimal apply(BigDecimal subtotal) {
        System.out.println("[DB] SELECT vip_rate FROM discounts ... (slow, needs a live database)");
        return subtotal.multiply(new BigDecimal("0.85"));
    }
}
