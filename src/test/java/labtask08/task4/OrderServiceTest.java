package labtask08.task4;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    @Test
    @DisplayName("VIP discount is applied using an in-memory implementation (no database)")
    void calculateTotal_appliesVipDiscount_withoutTouchingDatabase() {
        // Arrange: concrete, database-free VipDiscount (10% off) and a known basket
        VipDiscount tenPercentOff = new PercentageVipDiscount(new BigDecimal("10"));
        OrderService service = new OrderService(tenPercentOff);
        List<BigDecimal> prices = List.of(new BigDecimal("100.00"), new BigDecimal("50.00"));

        // Act
        BigDecimal total = service.calculateTotal(prices, true);

        // Assert: 150.00 - 10% = 135.00 exactly
        assertEquals(new BigDecimal("135.00"), total);
    }

    @Test
    @DisplayName("A non-VIP customer pays the full subtotal and the discount is never consulted")
    void calculateTotal_nonVip_paysFullPrice() {
        // Arrange: a stub that would blow up if it were ever called
        VipDiscount mustNotBeCalled = subtotal -> {
            throw new AssertionError("VipDiscount must not be used for a non-VIP customer");
        };
        OrderService service = new OrderService(mustNotBeCalled);
        List<BigDecimal> prices = List.of(new BigDecimal("100.00"), new BigDecimal("50.00"));

        // Act
        BigDecimal total = service.calculateTotal(prices, false);

        // Assert
        assertEquals(new BigDecimal("150.00"), total);
    }

    @Test
    @DisplayName("An empty basket totals 0.00")
    void calculateTotal_emptyBasket_isZero() {
        // Arrange
        OrderService service = new OrderService(new PercentageVipDiscount(new BigDecimal("10")));

        // Act
        BigDecimal total = service.calculateTotal(List.of(), true);

        // Assert
        assertEquals(new BigDecimal("0.00"), total);
    }
}
