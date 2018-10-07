package rpn.operator;

import rpn.calculation_strategy.CalculateStrategy;

/**
 * The calculation operator
 */
public interface Operator
{

    /**
     * Return the calculation strategy for the operator
     *
     * @return {@link CalculateStrategy}
     */
    CalculateStrategy getCalculationStrategy();

    /**
     * Return number of parameters
     *
     * @return int
     */
    int getNumberOfParameters();

    /**
     * Opposite operator symbol
     *
     * @return String
     */
    String getReverseOperatorSymbol();

    /**
     * The symbol represents the operator
     *
     * @return String
     */
    String getSymbol();
}
