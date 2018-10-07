package rpn.operator;

import static rpn.operator.OperatorConstants.UNDO;

import rpn.calculation_strategy.CalculateStrategy;
import rpn.calculation_strategy.Undo;

/**
 * Define undo operator.
 */
public class UndoOperator extends GeneralOperator
{
    public UndoOperator()
    {
        super(UNDO , null, 0);
    }


    @Override
    public CalculateStrategy getCalculationStrategy() {
        return new Undo();
    }
}
