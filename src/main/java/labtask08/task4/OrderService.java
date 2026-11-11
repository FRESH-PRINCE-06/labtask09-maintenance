package labtask08.task4;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;

/**
 * Calculates order totals. The VIP discount rule is injected, which is what makes
 * the class unit-testable without touching a database.
 */
public class OrderService {

    private final VipDiscount vipDiscount;

    public OrderService(VipDiscount vipDiscount) {
        this.vipDiscount = Objects.requireNonNull(vipDiscount, "vipDiscount must not be null");
    }

    public BigDecimal calculateTotal(List<BigDecimal> itemPrices, boolean vipCustomer) {
        Objects.requireNonNull(itemPrices, "itemPrices must not be null");

        BigDecimal subtotal = itemPrices.stream()
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal total = vipCustomer ? vipDiscount.apply(subtotal) : subtotal;
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
