package rpn.operator;

import static java.lang.Math.sqrt;
import static rpn.operator.OperatorConstants.POWER;
import static rpn.operator.OperatorConstants.SQRT;

/**
 * Define sqrt operator with calculation.
 */
public class SquareRoot extends GeneralOperator
{
    public SquareRoot()
    {
        super(SQRT, POWER, 1);
    }

    @Override
    public Double calculate(Double firstParameter, Double secondParameter)
    {
        if (firstParameter < 0) {
            throw new ArithmeticException("Cannot calculate square root for negative number.");
        }

        return sqrt(firstParameter);
    }
}
