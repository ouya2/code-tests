package rpn.calculation_strategy;

/**
 * The calculation strategy interface
 */
public interface CalculateStrategy {

  /**
   * The calculation.
   *
   * @param firstParameter
   * @param secondParameter
   * @return
   */
  Double calculate(Double firstParameter, Double secondParameter);
}
