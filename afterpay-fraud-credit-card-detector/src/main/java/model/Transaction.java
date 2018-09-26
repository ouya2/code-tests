package model;

import lombok.*;
import org.joda.money.Money;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * The model to represent a credit card transaction
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction
{
    @NonNull
    private String        creditCardNumber;
    @NonNull
    private LocalDateTime timestamp;
    @NonNull
    private Money         price;

    /**
     * Get the amount of the price
     *
     * @return BigDecimal
     */
    public BigDecimal getAmount()
    {
        return price.getAmount();
    }
}
