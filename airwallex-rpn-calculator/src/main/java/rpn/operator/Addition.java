package rpn.operator;

import static rpn.operator.OperatorConstants.PLUS;
import static rpn.operator.OperatorConstants.MINUS;

/**
 * Define addition/"+" operator with calculation.
 */
public class Addition extends GeneralOperator
{
    public Addition()
    {
        super(PLUS, MINUS, 2);
    }

    @Override
    public Double calculate(Double firstParameter, Double secondParameter)
    {
        return firstParameter + secondParameter;
    }
}
