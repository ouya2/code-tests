package model;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Test;

public class TransactionTest
{
    @Test
    public void testTransactionProperties()
    {
        String creditCardNumber = "1234564789548";
        Transaction transaction = new Transaction(creditCardNumber, LocalDateTime.now(), Money.of(CurrencyUnit.AUD, 100d));
        assertEquals(creditCardNumber, transaction.getCreditCardNumber());
        assertEquals(Money.of(CurrencyUnit.AUD, 100d), transaction.getPrice());
        assertNotNull(transaction.getTimestamp());
    }

    @Test
    public void testTransactionPropertiesGetterAndSetter()
    {
        String creditCardNumber = "1234564789548";
        Transaction transaction = new Transaction();
        transaction.setCreditCardNumber(creditCardNumber);
        transaction.setPrice(Money.of(CurrencyUnit.AUD, 100.25));
        transaction.setTimestamp(LocalDateTime.now());
        assertEquals(creditCardNumber, transaction.getCreditCardNumber());
        assertEquals(Money.of(CurrencyUnit.AUD, 100.25), transaction.getPrice());
        assertNotNull(transaction.getTimestamp());
        assertEquals(100.25, transaction.getAmount().doubleValue(), 0.0001);
    }

    @Test
    public void testTwoTransactions()
    {
        Transaction transaction1 = new Transaction("123456789012",
                LocalDateTime.parse("2014-04-29T13:15:54", DateTimeFormatter.ISO_DATE_TIME),
                Money.of(CurrencyUnit.AUD, 100d));
        Transaction transaction2 = new Transaction("123456789012",
                LocalDateTime.parse("2014-04-29T13:15:54", DateTimeFormatter.ISO_DATE_TIME),
                Money.of(CurrencyUnit.AUD, 100d));
        assertEquals(transaction1, transaction2);
        transaction2 = new Transaction("123456789012",
                LocalDateTime.parse("2014-04-29T13:15:55", DateTimeFormatter.ISO_DATE_TIME),
                Money.of(CurrencyUnit.AUD, 100d));
        assertFalse(transaction1.equals(transaction2));
    }

    @Test (expected = NullPointerException.class)
    public void testTransactionNullCreditCard()
    {
        new Transaction(null, LocalDateTime.now(), Money.of(CurrencyUnit.AUD, 100d));
    }

    @Test (expected = NullPointerException.class)
    public void testTransactionNullLocalDateTime()
    {
        new Transaction("1234564789548", null, Money.of(CurrencyUnit.AUD, 100d));
    }

    @Test (expected = NullPointerException.class)
    public void testTransactionNullPrice()
    {
        new Transaction("1234564789548", LocalDateTime.now(), null);
    }
}
