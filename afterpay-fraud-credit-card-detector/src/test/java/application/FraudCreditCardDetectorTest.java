package application;

import application.FraudCreditCardDetector;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class FraudCreditCardDetectorTest
{
    private FraudCreditCardDetector detector;

    @Before
    public void setup()
    {
        detector = new FraudCreditCardDetector();
    }


    @Test
    public void testFraudCreditCardSumLessThanThreshold()
    {
        List<String> transactionsInText = getTransactionsText();
        List<String> fraudCreditCardNumbers = detector.getFraudCreditCards(transactionsInText,
                LocalDate.parse("2014-04-29", DateTimeFormatter.ISO_DATE), Money.of(CurrencyUnit.AUD, 75));
        assertTrue(fraudCreditCardNumbers.isEmpty());
    }

    @Test
    public void testFraudCreditCardTotalEqualToThreshold()
    {
        List<String> transactionsInText = getTransactionsText();
        List<String> fraudCreditCardNumbers = detector.getFraudCreditCards(transactionsInText,
                LocalDate.parse("2014-04-29", DateTimeFormatter.ISO_DATE), Money.of(CurrencyUnit.AUD, 65));
        assertTrue(fraudCreditCardNumbers.isEmpty());
    }

    @Test
    public void testFraudCreditCardSumMoreThanThreshold()
    {
        List<String> transactionsInText = getTransactionsText();
        List<String> fraudCreditCardNumbers = detector.getFraudCreditCards(transactionsInText,
                LocalDate.parse("2014-04-29", DateTimeFormatter.ISO_DATE), Money.of(CurrencyUnit.AUD, 64));
        assertEquals(1, fraudCreditCardNumbers.size());
        assertEquals("10d7ce2f43e35fa57d1bbf8b1e2", fraudCreditCardNumbers.get(0));
    }

    @Test
    public void testFraudCreditCardMoreThanThresholdAtDecimalPlaces()
    {
        List<String> transactionsInText = getTransactionsText();
        List<String> fraudCreditCardNumbers = detector.getFraudCreditCards(transactionsInText,
                LocalDate.parse("2014-02-15", DateTimeFormatter.ISO_DATE), Money.of(CurrencyUnit.AUD, 1585.55));
        assertEquals(1, fraudCreditCardNumbers.size());
        assertEquals("75u65er58o5ier87a5d1bu87kk4", fraudCreditCardNumbers.get(0));
    }

    @Test
    public void testFraudCreditCardMoreThanThresholdMultipleCards()
    {
        List<String> transactionsInText = getTransactionsText();
        List<String> fraudCreditCardNumbers = detector.getFraudCreditCards(transactionsInText,
                LocalDate.parse("2014-04-29", DateTimeFormatter.ISO_DATE), Money.of(CurrencyUnit.AUD, 40));
        assertEquals(2, fraudCreditCardNumbers.size());
        assertEquals("75u65er58o5ier87a5d1bu87kk4", fraudCreditCardNumbers.get(0));
        assertEquals("10d7ce2f43e35fa57d1bbf8b1e2", fraudCreditCardNumbers.get(1));
    }

    @Test
    public void testFraudCreditCardNoDataForTheDate()
    {
        List<String> transactionsInText = getTransactionsText();
        List<String> fraudCreditCardNumbers = detector.getFraudCreditCards(transactionsInText,
                LocalDate.parse("2014-05-29", DateTimeFormatter.ISO_DATE), Money.of(CurrencyUnit.AUD, 50));
        assertTrue(fraudCreditCardNumbers.isEmpty());
    }

    @Test
    public void testTransactionEmptyData()
    {
        List<String> transactionData = Collections.emptyList();
        List<String> result = detector.getFraudCreditCards(transactionData,
                LocalDate.now(), Money.of(CurrencyUnit.AUD, 1000));
        assertTrue(result.isEmpty());
    }

    @Test
    public void testTransactionEmptyValue()
    {
        List<String> testTransactionData = Arrays.asList(
                "10d7ce2f43e35fa57d1bbf8b1e2, , 100.00",
                "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, ",
                ", 2014-04-29T13:15:54, 10.00"
        );

        List<String> result = detector.getFraudCreditCards(testTransactionData,
                LocalDate.parse("2014-04-29", DateTimeFormatter.ISO_DATE), Money.of(CurrencyUnit.AUD, 10));
        assertTrue(result.isEmpty());
    }

    @Test
    public void testTransactionTooManyColumns()
    {
        List<String> testTransactionData = Arrays.asList(
                "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 100.00",
                "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00, test1",
                "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00, test2"
        );
        List<String> result = detector.getFraudCreditCards(testTransactionData,
                LocalDate.parse("2014-04-29", DateTimeFormatter.ISO_DATE), Money.of(CurrencyUnit.AUD, 50));
        assertEquals(1, result.size());
        assertEquals("10d7ce2f43e35fa57d1bbf8b1e2", result.get(0));
    }

    @Test
    public void testTransactionMissingValue()
    {
        List<String> testData = Arrays.asList(
                "12d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 1000.00",
                "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54",
                "67r5c6ef43e35fa57d1byu87kk4, 2014-04-29T13:15:54, 10.00"
        );
        List<String> result = detector.getFraudCreditCards(testData,
                LocalDate.parse("2014-04-29", DateTimeFormatter.ISO_DATE), Money.of(CurrencyUnit.AUD, 500));
        assertEquals(1, result.size());
        assertEquals("12d7ce2f43e35fa57d1bbf8b1e2", result.get(0));
    }

    @Test (expected = DateTimeParseException.class)
    public void testTransactionInvalidDateTime()
    {
        List<String> testData = Arrays.asList(
                "10d7ce2f43e35fa57d1bbf8b1e2, 20140429131554, 10.00",
                "67r5c6ef43e35fa57d1byu87kk4, 2014-04-30T14:15:54, 50.00",
                "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T11:15:54, 15.00",
                "67r5c6ef43e35fa57d1byu87kk4, 2014-04-29T13:15:54, 16.00",
                "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T10:15:54, 40.00"
        );
        detector.getFraudCreditCards(testData,
                LocalDate.now(), Money.of(CurrencyUnit.AUD, 1000));
    }

    @Test (expected = NumberFormatException.class)
    public void testTransactionInvalidPrice()
    {
        List<String> testData = Arrays.asList(
                "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-28T13:15:54, 10.00",
                "67r5c6ef43e35fa57d1byu87kk4, 2014-04-30T14:15:54, 50.00",
                "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T11:15:54, absdf",
                "67r5c6ef43e35fa57d1byu87kk4, 2014-04-27T13:15:54, 16.00",
                "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T10:15:54, 40.00"
        );
        detector.getFraudCreditCards(testData,
                LocalDate.now(), Money.of(CurrencyUnit.AUD, 1000));
    }

    @Test
    public void testUniqueTransactionData()
    {
        List<String> testData = Arrays.asList(
            "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 100.00",
            "67r5c6ef43e35fa57d1byu87kk4, 2014-04-30T14:15:54, 50.00",
            "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 100.00",
            "67r5c6ef43e35fa57d1byu87kk4, 2014-04-29T14:15:54, 50.00",
            "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 100.00"
        );

        List<String> fraudCreditCardNumbers = detector.getFraudCreditCards(testData,
                LocalDate.parse("2014-04-29", DateTimeFormatter.ISO_DATE),
                Money.of(CurrencyUnit.AUD, 100));
        assertTrue(fraudCreditCardNumbers.isEmpty());
    }

    private List<String> getTransactionsText()
    {
        return Arrays.asList(
                "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T13:15:54, 10.00",
                "67r5c6ef43e35fa57d1byu87kk4, 2014-04-30T14:15:54, 50.00",
                "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T11:15:54, 15.00",
                "67r5c6ef43e35fa57d1byu87kk4, 2014-04-30T13:15:54, 16.00",
                "10d7ce2f43e35fa57d1bbf8b1e2, 2014-04-29T10:15:54, 40.00",
                "75u65er58o5ier87a5d1bu87kk4, 2014-02-15T14:15:54, 1585.56",
                "75u65er58o5ier87a5d1bu87kk4, 2014-04-29T11:15:54, 50.55"
        );
    }
}
