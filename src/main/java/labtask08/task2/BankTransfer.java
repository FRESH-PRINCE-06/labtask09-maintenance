package labtask08.task2;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Immutable domain object with fail-fast guard clauses.
 * An invalid BankTransfer can never exist: the constructor throws immediately.
 */
public record BankTransfer(String fromAccount, String toAccount, BigDecimal amount) {

    public BankTransfer {
        Objects.requireNonNull(fromAccount, "fromAccount must not be null");
        Objects.requireNonNull(toAccount, "toAccount must not be null");
        Objects.requireNonNull(amount, "amount must not be null");
        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "amount must be strictly positive but was " + amount);
        }
    }
}
