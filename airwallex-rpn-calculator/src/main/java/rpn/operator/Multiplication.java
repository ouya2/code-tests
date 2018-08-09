package rpn.operator;

import static rpn.operator.OperatorConstants.DIVIDE;
import static rpn.operator.OperatorConstants.MULTIPLY;

/**
 * Define multiplication/"*" operator with calculation.
 */
public class Multiplication extends GeneralOperator
{
    public Multiplication()
    {
        super(MULTIPLY, DIVIDE, 2);
    }

    @Override
    public Double calculate(Double firstParameter, Double secondParameter)
    {
        return firstParameter * secondParameter;
    }
}
