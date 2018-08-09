package rpn.operator;

/**
 * The calculation operator
 */
public interface Operator
{
    /**
     * Perform the calculation based on operator.
     *
     * @param firstParameter
     * @param secondParameter
     * @return Double as result
     */
    Double calculate(Double firstParameter, Double secondParameter);

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
