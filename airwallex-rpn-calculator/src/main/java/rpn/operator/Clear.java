package rpn.operator;

import static rpn.operator.OperatorConstants.CLEAR;

/**
 * Define clear operator.
 */
public class Clear extends GeneralOperator
{
    public Clear()
    {
        super(CLEAR, null, 0);
    }

    @Override
    public Double calculate(Double firstParameter, Double secondParameter)
    {
        throw new ArithmeticException("Invalid operation " + getSymbol() + " for calculation");
    }
}
