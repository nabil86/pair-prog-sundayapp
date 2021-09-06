package domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private BigDecimal amount;

    private Money(BigDecimal amount) {
        this.amount = amount;
    }

    public static Money Zero() {
        return new Money(BigDecimal.ZERO);
    }

    public static Money of(BigDecimal amount) {
        return new Money(amount);
    }

    public static Money of(long amount) {
        return of(BigDecimal.valueOf(amount));
    }

    public Money add(Money money) {
        return new Money(amount.add(money.amount));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Money money = (Money) o;
        return Objects.equals(amount, money.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount);
    }

    public Money substract(Money money) {
        return new Money(amount.subtract(money.amount));
    }

    @Override
    public String toString() {
        return amount.toString();
    }

    public boolean isGreaterOrEqualToZero() {
        return this.amount.compareTo(BigDecimal.ZERO) >= 0;
    }
}
