package calculation_strategy;

import org.junit.Before;
import org.junit.Test;
import rpn.calculation_strategy.Clear;
import rpn.operator.ClearOperator;

public class ClearTest
{
    Clear operator;

    @Before
    public void setup()
    {
        operator = new Clear();
    }

    @Test (expected = ArithmeticException.class)
    public void testCalculate() throws Exception
    {
       operator.calculate(0.0, 1.0);
    }
}
