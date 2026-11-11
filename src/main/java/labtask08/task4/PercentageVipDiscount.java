package labtask08.task4;

import java.math.BigDecimal;

/**
 * Concrete, database-free implementation: a fixed percentage off.
 */
public class PercentageVipDiscount implements VipDiscount {

    private final BigDecimal percent;

    public PercentageVipDiscount(BigDecimal percent) {
        this.percent = percent;
    }

    @Override
    public BigDecimal apply(BigDecimal subtotal) {
        BigDecimal discount = subtotal.multiply(percent).divide(new BigDecimal("100"));
        return subtotal.subtract(discount);
    }
}
