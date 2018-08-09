package rpn.operator;

import static rpn.operator.OperatorConstants.DIVIDE;
import static rpn.operator.OperatorConstants.MULTIPLY;

/**
 * Define division operator with calculation.
 */
public class Division extends GeneralOperator
{
    public Division()
    {
        super(DIVIDE, MULTIPLY, 2);
    }

    @Override
    public Double calculate(Double firstParameter, Double secondParameter)
    {
        if (firstParameter == 0)
        {
            throw new ArithmeticException("Divide by 0 error.");
        }
        return secondParameter / firstParameter;
    }
}
