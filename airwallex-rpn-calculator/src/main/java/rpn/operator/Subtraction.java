package rpn.operator;

import static rpn.operator.OperatorConstants.PLUS;
import static rpn.operator.OperatorConstants.MINUS;

/**
 * Define subtraction/"-" operator with calculation.
 */
public class Subtraction extends GeneralOperator
{
    public Subtraction()
    {
        super(MINUS, PLUS, 2);
    }

    @Override
    public Double calculate(Double firstParameter, Double secondParameter)
    {
        return secondParameter - firstParameter;
    }
}
