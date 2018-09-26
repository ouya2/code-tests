package application;

import model.Transaction;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.money.Money;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static org.joda.money.CurrencyUnit.AUD;

/**
 * The fraud credit card detector
 */
public class FraudCreditCardDetector {

  /**
   * The value separator for transaction details
   */
  private static final String VALUE_SEPARATOR = ",";

  /**
   * Number of fields expected in transaction data
   */
  private static final int TRANSACTION_PROPERTY_ARRAY_LENGTH = 3;

  /**
   * Get the fraud credit card numbers
   *
   * @param transactionsText List of transaction records
   * @param transactionDate The transaction date to check
   * @param amountThreshold The threshold amount
   * @return List of hashed credit card numbers
   */
  public List<String> getFraudCreditCards(List<String> transactionsText, LocalDate transactionDate,
      Money amountThreshold) {
    List<String> fraudCreditCards = Collections.emptyList();

    if (CollectionUtils.isNotEmpty(transactionsText)) {
      List<String[]> transactionDetails = splitTransactionDetails(transactionsText);

      List<Transaction> allTransactions = convertToTransactions(transactionDetails);

      Map<LocalDate, Map<String, Double>> aggregatedResultSummary = calculateTotalAmountPerCreditCardOnOneDate(
          allTransactions, transactionDate);

      Map<String, Double> creditCardTotalAmountMap = aggregatedResultSummary.get(transactionDate);
      if (creditCardTotalAmountMap != null && !creditCardTotalAmountMap.isEmpty()) {
        fraudCreditCards = creditCardTotalAmountMap.entrySet()
            .stream()
            .filter(entry -> entry.getValue() > amountThreshold.getAmount().doubleValue())
            .map(entry -> entry.getKey())
            .collect(toList());
      }
    }

    return fraudCreditCards;
  }

  private List<String[]> splitTransactionDetails(List<String> transactionsText) {
    return transactionsText.stream()
        .map(eachTransaction -> eachTransaction.split(VALUE_SEPARATOR))
        .filter(details -> details.length == TRANSACTION_PROPERTY_ARRAY_LENGTH &&
            Arrays.stream(details)
                .allMatch(eachProperty -> StringUtils.isNotEmpty(eachProperty) &&
                    StringUtils.isNotBlank(eachProperty)))
        .collect(toList());
  }

  private List<Transaction> convertToTransactions(List<String[]> transactionDetails) {
    return transactionDetails.stream()
        .map(everyDetail -> new Transaction(everyDetail[0],
            LocalDateTime.parse(everyDetail[1].trim(), DateTimeFormatter.ISO_DATE_TIME),
            Money.of(AUD, Double.valueOf(everyDetail[2].trim()))))
        .distinct()
        .collect(toList());
  }

  private Map<LocalDate, Map<String, Double>> calculateTotalAmountPerCreditCardOnOneDate(
      List<Transaction> allTransactions,
      LocalDate transactionDate) {
    return allTransactions.stream()
        .filter(transaction -> transactionDate.isEqual(transaction.getTimestamp().toLocalDate()))
        .collect(Collectors.groupingBy(transaction -> transaction.getTimestamp().toLocalDate(),
            Collectors.groupingBy(transaction -> transaction.getCreditCardNumber(),
                Collectors.summingDouble(
                    transaction -> transaction.getAmount().doubleValue()
                ))));
  }

}
