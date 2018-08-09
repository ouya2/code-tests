package rpn.operator;

import static rpn.operator.OperatorConstants.UNDO;

/**
 * Define undo operator.
 */
public class Undo extends GeneralOperator
{
    public Undo()
    {
        super(UNDO , null, 0);
    }

    @Override
    public Double calculate(Double firstParameter, Double secondParameter)
    {
        throw new ArithmeticException("Invalid operation " + getSymbol() + " for calculation");
    }
}
