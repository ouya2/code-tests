package calculation_strategy;

import org.junit.Before;
import org.junit.Test;
import rpn.calculation_strategy.Undo;
import rpn.operator.UndoOperator;

public class UndoTest
{
    Undo operator;
    @Before
    public void setup()
    {
        operator = new Undo();
    }

    @Test (expected = ArithmeticException.class)
    public void testCalculate() throws Exception
    {
       operator.calculate(0.0, 1.0);
    }

}
