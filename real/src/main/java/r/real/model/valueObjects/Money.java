package r.real.model.valueObjects;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import r.real.model.base.ValueObject;


import java.math.BigDecimal;

import static org.apache.commons.lang3.Validate.*;

@Embeddable
public class Money implements ValueObject {

    @Enumerated(EnumType.STRING)
    @Column(name = "salary_currency", nullable = false)
    private Currency currency;

    @Column(name = "salary_amount", nullable = false)
    private BigDecimal amount;

    public Money(Currency currency, BigDecimal amount) {
        notNull(currency, "currency must not be null");
        notNull(amount, "amount must not be null");
        isTrue(amount.compareTo(BigDecimal.ZERO) >= 0,
                "amount must be greater than or equal to zero");
        this.currency = currency;
        this.amount = amount;
    }

    protected Money() { }

    public Money add(Money money) {
        notNull(money, "money must not be null");
        isTrue(currency.equals(money.currency),
                "Cannot add two Money objects with different currencies");
        return new Money(currency, amount.add(money.amount));
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getAmount() {
        return amount;
    }
}