package labtask08.task2;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankTransferTest {

    @Test
    @DisplayName("A valid transfer is created and exposes its values")
    void testValidTransfer() {
        BankTransfer t = new BankTransfer("PK-001", "PK-002", new BigDecimal("2500.00"));
        assertEquals("PK-001", t.fromAccount());
        assertEquals("PK-002", t.toAccount());
        assertEquals(new BigDecimal("2500.00"), t.amount());
    }

    @Test
    @DisplayName("A negative amount is rejected immediately")
    void testNegativeAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new BankTransfer("PK-001", "PK-002", new BigDecimal("-1")));
    }

    @Test
    @DisplayName("A zero amount is rejected (must be STRICTLY positive)")
    void testZeroAmount() {
        assertThrows(IllegalArgumentException.class,
                () -> new BankTransfer("PK-001", "PK-002", BigDecimal.ZERO));
    }

    @Test
    @DisplayName("A null fromAccount is rejected immediately")
    void testNullFromAccount() {
        assertThrows(NullPointerException.class,
                () -> new BankTransfer(null, "PK-002", BigDecimal.TEN));
    }

    @Test
    @DisplayName("A null toAccount is rejected immediately")
    void testNullToAccount() {
        assertThrows(NullPointerException.class,
                () -> new BankTransfer("PK-001", null, BigDecimal.TEN));
    }

    @Test
    @DisplayName("A null amount is rejected immediately")
    void testNullAmount() {
        assertThrows(NullPointerException.class,
                () -> new BankTransfer("PK-001", "PK-002", null));
    }
}
