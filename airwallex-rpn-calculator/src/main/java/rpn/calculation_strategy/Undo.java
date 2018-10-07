package rpn.calculation_strategy;

public class Undo implements CalculateStrategy {

  @Override
  public Double calculate(Double firstParameter, Double secondParameter) {
    throw new ArithmeticException("Invalid operation for undo");
  }
}
