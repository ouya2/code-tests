package rpn.calculation_strategy;

public class Divide implements CalculateStrategy {

  @Override
  public Double calculate(Double firstParameter, Double secondParameter) {
    if (firstParameter == 0)
    {
      throw new ArithmeticException("Divide by 0 error.");
    }
    return secondParameter / firstParameter;
  }
}
