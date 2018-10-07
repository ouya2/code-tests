package rpn.calculation_strategy;

import static java.lang.Math.sqrt;

public class SquareRoot implements CalculateStrategy {

  @Override
  public Double calculate(Double firstParameter, Double secondParameter) {
    if (firstParameter < 0) {
      throw new ArithmeticException("Cannot calculate square root for negative number.");
    }

    return sqrt(firstParameter);
  }
}
